package com.ichsy.hrys.common.utils.download;

import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;


public class DownLoadOperator implements Runnable {

	private static final long REFRESH_INTEVAL_SIZE=100*1024;
	
	private DownLoadManager mDLManager;
	
	private DownLoadTask mDLTask;
	
	private int retryTimes;
	
	private volatile boolean pauseFlag;
	
	private volatile boolean stopFlag;
	
	private String filePath;

	public DownLoadOperator(DownLoadManager manager,DownLoadTask task){
		this.mDLManager=manager;
		this.mDLTask=task;
		this.retryTimes=0;
	}
	
	void pauseDownload(){
		if(pauseFlag){
			return;
		}
		pauseFlag=true;
	}
	
	void resumeDownload(){
		if(!pauseFlag){
			
			return;
		}
		
		pauseFlag=false;
		synchronized (this) {
			notify();
		}
		
	}
	
	void cancelDownload() {
		stopFlag = true;
		resumeDownload();
	}
	
	
	@Override
	public void run() {
		
		do {
			RandomAccessFile randFile=null;
			HttpURLConnection conn=null;
			InputStream inputStream=null;
			
			try {
				randFile=buildDownLoadFile(mDLTask);
				conn=InitConnection();
				conn.connect();
				mDLTask.setDlSavePath(filePath);
				if(mDLTask.getDlTotalSize()==0){
					mDLTask.setDlTotalSize(conn.getContentLength());
				}
				
				if(TextUtils.isEmpty(mDLTask.getMimeType())){
					mDLTask.setMimeType(conn.getContentType());
				}
				
				mDLTask.setStatus(DownLoadTask.STATUS_RUNNING);
				mDLManager.onDownloadStarted(mDLTask);
	
				inputStream=conn.getInputStream();
				byte [] buffer=new byte[8192];
				int count=0;
				long total=mDLTask.getDlFinishSize();
				long prevTime= System.currentTimeMillis();
				long achieveSize=total;
				while(!stopFlag && (count = inputStream.read(buffer)) != -1) {
					while(pauseFlag) {
						mDLManager.onDownloadPaused(mDLTask);
						synchronized (this) {
							try {
								wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
								mDLManager.onDownloadResumed(mDLTask);
							}
						}
					}

					randFile.write(buffer, 0, count);
					total += count;

					long tempSize = total - achieveSize;
					if(tempSize > REFRESH_INTEVAL_SIZE) {
						long tempTime = System.currentTimeMillis() - prevTime;
						long speed = tempSize * 1000 / tempTime;
						achieveSize = total;
						prevTime = System.currentTimeMillis();
						mDLTask.setDlFinishSize(total);
						mDLTask.setDlSpeed(speed);
						mDLManager.updateDownloadTask(mDLTask);
					}
				}
				mDLTask.setDlFinishSize(total);

				if(stopFlag) {
					mDLManager.onDownloadCanceled(mDLTask);
				} else {
					mDLManager.onDownloadSuccessed(mDLTask);
				}
				break;
	
			} catch (IOException e) {
				
				e.printStackTrace();
				if(retryTimes > mDLManager.getConfig().getRetryTimes()) {
					mDLManager.onDownloadFailed(mDLTask);
					break;
				} else {
					retryTimes ++;
					continue;
				}
			}
			
			
		} while (true);
		
	}
	
	private RandomAccessFile buildDownLoadFile(DownLoadTask task) throws IOException {
		String FileName= DownLoadFileUtil.getFileNameByUrl(mDLTask.getUrl());
//		File file=new File(mDLManager.getConfig().getDLSavePath()+task.getDownloadFolderPath(), FileName);
		File file=new File(task.getDlSavePath(), FileName);
		if(!file.getParentFile().isDirectory()&&!file.getParentFile().mkdirs()){
			throw new IOException("cannot create download folder");
		}
		
		if(file.exists()){	
		}
		
		filePath=file.getAbsolutePath();
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		if(mDLTask.getDlFinishSize() != 0) {
			raf.seek(mDLTask.getDlFinishSize());
		}
		
		return raf;
	}
	
	private HttpURLConnection InitConnection()throws IOException {
		HttpURLConnection conn=(HttpURLConnection) new URL(mDLTask.getUrl()).openConnection();
		conn.setConnectTimeout(30000);
		conn.setReadTimeout(30000);
		conn.setUseCaches(true);
		if(mDLTask.getDlFinishSize()!=0){
			conn.setRequestProperty("Range", "bytes=" + mDLTask.getDlFinishSize() + "-");
		}
		
		return conn;
		
	}

}
