package com.ichsy.hrys.common.utils.download;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 下载管理器
 * @author cuilei
 *
 */
public class DownLoadManager  implements  OnDBOperationFinishedCallback{

	private static final String TAG = "DownLoadManger";

	private DownLoadConfig mDownLoadConfig;
	
	private HashMap<DownLoadTask, DownLoadOperator> taskOperators=new HashMap<>();
	
	private HashMap<DownLoadTask, DownLoadListener> taskListeners=new HashMap<>();
	
    private LinkedList<DownLoadObserver> taskObservers = new LinkedList<>();
    
    private DownLoadProvider provider;

    private static Handler handler = new Handler();
    
    private ExecutorService pool;
    
    private DownLoadManager(){
    	
    }

	private static class DownLoadManagerHolder{
		private static final DownLoadManager INSTANCE = new DownLoadManager();
	}
    
	public static DownLoadManager getInstance() {
		return DownLoadManagerHolder.INSTANCE;
	}
	
	public DownLoadManager init(Context context){
		initDefaultDownLoadConfig(context);

		init(context,mDownLoadConfig);
		return this;
	}
	
	public DownLoadManager init(Context context, DownLoadConfig config){
		if(config == null){
			initDefaultDownLoadConfig(context);
			Log.w(TAG,"config is null, set value with defaultconfig");
		}else{
			mDownLoadConfig = config;
		}
		provider=mDownLoadConfig.getProvider();
		pool= Executors.newFixedThreadPool(mDownLoadConfig.getMaxDLThread());
		return this;
	}

	/**
	 * 初始化默认的下载设置
	 */
	private void  initDefaultDownLoadConfig(Context context){
		String path = FileConfig.getRootDir(context);
		SqlLiteDownLoadProvider providerInstance = SqlLiteDownLoadProvider.getInstance(path);
		providerInstance.setmDBcallback(this);

		DownLoadConfig.Builder builder = new DownLoadConfig.Builder();

		mDownLoadConfig = builder
				.setDLSavePath(path)
				.setDLProvider(providerInstance)
		.build();


	}
	
	public DownLoadConfig getConfig(){
		
		return mDownLoadConfig;
	}
	
	public void setConfig(DownLoadConfig config){
		
		this.mDownLoadConfig=config;
	}
	
	/**
	 * @param task
	 * @param listener
	 */
	public  void addDLTask(DownLoadTask task,DownLoadListener listener){
		if(TextUtils.isEmpty(task.getUrl())){
			throw new IllegalArgumentException("task's url cannot be empty");
		}
		if(taskOperators.containsKey(task)){
			return;
		}
		
		DownLoadOperator operator =new DownLoadOperator(this, task);
		taskOperators.put(task, operator);
		
		if(listener!=null){
			taskListeners.put(task, listener);
		}
		task.setStatus(DownLoadTask.STATUS_PENDDING);
		
		DownLoadTask historyTask=provider.findDownloadTaskById(task.getId());
		
		if(historyTask == null){
			task.setId(mDownLoadConfig.getCreator().createId(task));
			provider.saveDownLoadTask(task);
		}else{
			provider.updateDownLoadTask(task);
		}
		
		pool.submit(operator);
		
	}
	/**
	 * @param task
	 * @return
	 */
	
	private DownLoadListener getDownLoadListenerForTask( DownLoadTask task){
		
		if(task==null){
			
			return null;
		}
		return taskListeners.get(task);
	}
	
	/**
	 * 更新下载任务的监听器
	 * @param task
	 * @param listener
	 */

	public void updateDownLoadTaskListener(DownLoadTask task,DownLoadListener listener){
		
		if(task==null || !taskOperators.containsKey(task) ){
			return;
		}
		taskListeners.put(task, listener);

	}
	
	/**
	 * 移除下载任务对应的监听事件
	 * @param task
	 */
	public void removeDownLoadTaskListener(DownLoadTask task){
		if(task == null || !taskListeners.containsKey(task)) {
			return ;
		}
		taskListeners.remove(task);
	}
	
	/**
	 * 暂停下载任务
	 * @param task
	 * @param listener
	 */

	public void pauseDownLoad(DownLoadTask task,DownLoadListener listener){
		Log.i(TAG, "?????????????? task.getName()"+task.getName());
		DownLoadOperator operator=taskOperators.get(task);
		if(operator!=null){
			operator.pauseDownload();
		}else{
			addDLTask(task, listener);
			pauseDownLoad(task,listener);
		}
	}
	
	/**
	 * 恢复下载
	 * @param task
	 * @param listener
	 */

	public void resumeDownload(DownLoadTask task,DownLoadListener listener){
		Log.i(TAG, "恢复下载 task.getName()"+task.getName());
		DownLoadOperator operator=taskOperators.get(task);
		if(operator!=null){
			operator.resumeDownload();
		}else{
			addDLTask(task, listener);
		}
	}	
	
	
	/**
	 * 取消下载
	 * @param task
	 * @param listener
	 */
	public void cancelDownload(final DownLoadTask task,final DownLoadListener listener){
		Log.i(TAG, "取消下载 task.getName()"+task.getName());
		DownLoadOperator operator=taskOperators.get(task);
		if(operator!=null){
			operator.cancelDownload();
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					try {
						File file=new File(task.getDlSavePath());
						if(file.isFile()){	
							file.delete();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}else{
			
			task.setStatus(DownLoadTask.STATUS_CANCELED);
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					provider.deleteDownLoadTask(task);
					if(listener!=null){
						listener.onDownLoadCancel(task);
					}
					try {
						File file = new File(task.getDlSavePath());
						if(file.isFile()){
							file.delete();
						}
					} catch (Exception e) {
						Log.i(TAG, "取消下载.."+e.toString());
					}
					
				}
			});
			
		}
		
	}
	
	
	/**
	 *
	 * @param id
	 * @return
	 */
	private DownLoadTask findDownloadTaskById(String id){
		
		Iterator<DownLoadTask> iterator=taskOperators.keySet().iterator();
		
		while (iterator.hasNext()) {
			DownLoadTask task=iterator.next();
			if(task.getId().equals(id)){
				
				return task;
			}
		}
		
		Log.i(TAG, "查找下载任务");
		
		return provider.findDownloadTaskById(id);
	}
	
	/**
	 * @return
	 */
	private List<DownLoadTask> getAllDownLoadTask(){
		
		return provider.getAllDownloadTask();
	}
	
	/**
	 *
	 * @param observer
	 */
	private void registerDownloadObserver(DownLoadObserver observer){
		if(observer==null){
			return;
		}
		taskObservers.add(observer);
	}
	
	/**
	 * @param observer
	 */

	private void unregisterDownloadObserver(DownLoadObserver observer) {
		if(observer == null) {
			return ;
		}
		taskObservers.remove(observer);
	}
	
	
	/**
	 * 关闭下载
	 */

	private void close() {
		pool.shutdownNow();
	}
	

	/**
	 * @param task
	 */

	public void updateDownloadTask(final DownLoadTask task){
		task.setStatus(DownLoadTask.STATUS_RUNNING);

		final DownLoadListener listener=taskListeners.get(task);
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				provider.updateDownLoadTask(task);
				if(listener!=null){
					listener.onDownLoadUpdate(task);
				}
				
			}
		});
		
	}
	
	/**
	 * @param task
	 */
	public void onDownloadStarted(final DownLoadTask task){
		task.setStatus(DownLoadTask.STATUS_RUNNING);
		final DownLoadListener listener=taskListeners.get(task);
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				provider.updateDownLoadTask(task);
				if(listener!=null){
					listener.onDownLoadStart(task);
				}
			}
		});
	}
	
	/**
	 * @param task
	 */
	public  void onDownloadPaused(final DownLoadTask task){
		task.setStatus(DownLoadTask.STATUS_PAUSED);
		final DownLoadListener listener=taskListeners.get(task);
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				provider.updateDownLoadTask(task);
				if(listener!=null){
					listener.onDownLoadPause(task);
				}
				
			}
		});
		
	}

	/**
	 * ????????????
	 * @param task
	 */

	public void onDownloadResumed(final DownLoadTask task){
		task.setStatus(DownLoadTask.STATUS_RUNNING);
		final DownLoadListener listener=taskListeners.get(task);
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				provider.updateDownLoadTask(task);
				if(listener!=null){
					listener.onDownLoadResume(task);
				}
			}
		});
		
	}
	
	/**
	 * ???????????
	 * @param task
	 */

	public void onDownloadCanceled(final DownLoadTask task){
		task.setStatus(DownLoadTask.STATUS_CANCELED);
		final DownLoadListener listener=taskListeners.get(task);
		removeTask(task);
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				provider.deleteDownLoadTask(task);
				if(listener!=null){
					listener.onDownLoadCancel(task);
				}
				
			}
		});
		
		
	}
	
	/**
	 * ???????? ???
	 * @param task
	 */

	public void onDownloadSuccessed(final DownLoadTask task){
		task.setStatus(DownLoadTask.STATUS_FINISHED);
		final DownLoadListener listener=taskListeners.get(task);
		removeTask(task);
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				provider.updateDownLoadTask(task);
				if(listener!=null){
					listener.onDownLoadSuccess(task);
				}
				
			}
		});
		
		
	}
	
	/**
	 * ???????????
	 * @param task
	 */

	public void onDownloadFailed(final DownLoadTask task){
		task.setStatus(DownLoadTask.STATUS_ERROR);
		final DownLoadListener listener=taskListeners.get(task);
		removeTask(task);
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				provider.updateDownLoadTask(task);
				if(listener!=null){
					listener.onDownLoadFaile(task);
				}
			}
		});
	}


	private void onDownloadRetry(final DownLoadTask task){
		final DownLoadListener listener=taskListeners.get(task);
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				if(listener!=null){
					listener.onDownLoadRetry(task);
				}
				
			}
		});
		
	}
	
	
	/**
	 * ???????
	 * @param task
	 */
	private void removeTask(DownLoadTask task) {
		taskOperators.remove(task);
		taskListeners.remove(task);
	}

	@Override
	public void onDBOperationFinishedCallback(final DownLoadTask task) {
		handler.post(new Runnable() {

			@Override
			public void run() {

				for(DownLoadObserver observer : taskObservers){

					observer.onDownloadTaskStatusChanged(task);
				}
			}
		});
	}

	@Override
	public void onDBOperationonFailure(String errorDetail) {
		Log.e(TAG,"failure:"+errorDetail);
	}
}
