package com.ichsy.hrys.common.utils.download;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class FileConfig {

	/**
	 * 文件下载后保存的路径
	 * @param context
	 * @return
     */
	public  static String getRootDir(Context context){
		return Environment.getExternalStorageDirectory().getPath() + File.separator +context.getPackageName() + File.separator + "download";
	}
}
