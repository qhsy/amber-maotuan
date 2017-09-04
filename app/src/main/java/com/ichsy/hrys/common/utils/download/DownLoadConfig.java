package com.ichsy.hrys.common.utils.download;

/**
 * 文件下载的配置信息
 */
public class DownLoadConfig {
	/**
	 * 文件保存路径
	 */
	private String DLSavePath;
	/**
	 * 最大下载线程
	 */
	private int maxDLThread = 2;
	/**
	 * 重试次数
	 */
	private int retryTimes = 2;
	/**
	 * 本地存储 所需的数据库控制器
	 */
	private SqlLiteDownLoadProvider mDownLoadProvider;
	
	private DownLoadTaskIDCreator mCreator = new MD5DownLoadTaskIDCreator();


	public String getDLSavePath() {
		return DLSavePath;
	}

	public int getMaxDLThread() {
		return maxDLThread;
	}

	public int getRetryTimes() {
		return retryTimes;
	}
	
	
	public DownLoadTaskIDCreator getCreator() {
		return mCreator;
	}
	
	private DownLoadConfig(){

	}


	public DownLoadProvider getProvider(){
		return mDownLoadProvider;
	}
	
	

	public static class Builder{
		private  DownLoadConfig config;
		
		public  Builder(){

			config=new DownLoadConfig();
		}
		
		public  DownLoadConfig build(){
			
			return config;
		}
		
		public Builder setDLSavePath(String dlSavePath){
			config.DLSavePath=dlSavePath;
			return this;
		}
		
		public Builder setDLMaxThread(int maxThread){
			config.maxDLThread=maxThread > 0 ?maxThread: 2;
			return this;
		}
		
		public Builder setReTryTimes(int retryTimes){
			config.retryTimes=retryTimes > 0 ? retryTimes : 2;
			return this;
		}
		
		public Builder setDLProvider(SqlLiteDownLoadProvider provider){
			config.mDownLoadProvider=provider;
			return this;
		}
		
		public Builder setDLTaskIDCreator(DownLoadTaskIDCreator idcreator){
			config.mCreator=idcreator;
			return this;
		}
		
	}
	
	
}
