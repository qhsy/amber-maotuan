package com.ichsy.hrys.common.utils.download;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SqlLiteDownLoadProvider implements DownLoadProvider{


	private static String DOWNLOAD_TABLE="tb_download";


	private static SQLiteDatabase db;
	//文件存储路径
	private static OnDBOperationFinishedCallback mDBcallback;


	private SqlLiteDownLoadProvider(){}

	private static class SqlLiteDownLoadProviderHolder{
		public static final SqlLiteDownLoadProvider INSTANCE = new SqlLiteDownLoadProvider();
	}
	
	public static SqlLiteDownLoadProvider getInstance(String dbSavePath){
		createDB(dbSavePath);
		return SqlLiteDownLoadProviderHolder.INSTANCE;
	}

	/**
	 * 创建数据库
	 * @param dbSavePath
     */
	private static void  createDB(String dbSavePath){

		try {
			File dbFile=new File(dbSavePath + File.separator + "db", "download.db");
			if(dbFile.exists()){
				db= SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
			}else{
				if(!dbFile.getParentFile().isDirectory()){
					dbFile.getParentFile().mkdirs();
				}

				try {

					dbFile.createNewFile();
					if(dbFile.exists()){

						db= SQLiteDatabase.openOrCreateDatabase(dbFile, null);

					}else{
						Log.e("DB","没成功");
						throw new IllegalAccessError("create  db failure..");
					}

				} catch (IOException e) {

					e.printStackTrace();
					operationFailure(e.toString());

				}catch (IllegalAccessError e){
					operationFailure(e.toString());

					throw new IllegalAccessError("cannot create database file of path: " + dbFile.getAbsolutePath());

				}

			}

			if (db != null)
				createTables();
		} catch (IllegalAccessError e) {
			operationFailure(e.toString());
			e.printStackTrace();
		}
	}
	private static void operationFailure(String errorDetail){
			if (mDBcallback != null){
				mDBcallback.onDBOperationonFailure(errorDetail);
			}
	}
	private static void createTables(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("CREATE TABLE IF NOT EXISTS ").append(DOWNLOAD_TABLE);
		buffer.append("(");
		buffer.append("`").append(DownLoadTask.ID).append("` VARCHAR PRIMARY KEY,");
		buffer.append("`").append(DownLoadTask.URL).append("` VARCHAR,");
		buffer.append("`").append(DownLoadTask.MIMETYPE).append("` VARCHAR,");
		buffer.append("`").append(DownLoadTask.SAVEPATH).append("` VARCHAR,");
		buffer.append("`").append(DownLoadTask.NAME).append("` VARCHAR,");
		buffer.append("`").append(DownLoadTask.FINISHEDSIZE).append("` LONG,");
		buffer.append("`").append(DownLoadTask.TOTALSIZE).append("` LONG,");
		buffer.append("`").append(DownLoadTask.STATUS).append("` int");
		buffer.append(")");
		db.execSQL(buffer.toString());
	}
	
	
	
	
	public void saveDownLoadTask(DownLoadTask task) {
		ContentValues values=CreatDLTaskValues(task);
		db.insert(DOWNLOAD_TABLE, null, values);
		notifyDownloadStatusChanged(task);
		
	}


	public void updateDownLoadTask(DownLoadTask task) {
		ContentValues values=CreatDLTaskValues(task);
		db.update(DOWNLOAD_TABLE, values, DownLoadTask.ID + "=?", new String[]{task.getId()});
		notifyDownloadStatusChanged(task);
		
	}


	public void deleteDownLoadTask(DownLoadTask task) {
		db.delete(DOWNLOAD_TABLE, DownLoadTask.ID + "=?", new String[]{task.getId()});
		notifyDownloadStatusChanged(task);
		
	}

	
	public DownLoadTask findDownloadTaskById(String id) {
		if(TextUtils.isEmpty(id)){
			return null;
		}
		DownLoadTask task=null;
		Cursor cursor = db.query(DOWNLOAD_TABLE, null, DownLoadTask.ID + "=?", new String[]{id}, null, null, null);
		if(cursor.moveToNext()){
			task = restoreDownloadTaskFromCursor(cursor);
		}
		cursor.close();
		return task;
	}


	public DownLoadTask findDownloadTask(String[] columns, String selection,
                                         String[] selectionArgs, String groupBy, String having,
                                         String orderBy) {
		DownLoadTask task = null;
		Cursor cursor = db.query(DOWNLOAD_TABLE, columns, selection, selectionArgs, groupBy, having, orderBy);
		if(cursor.moveToNext()) {
			task = restoreDownloadTaskFromCursor(cursor);
		}
		cursor.close();
		
		return task;
	}


	public List<DownLoadTask> getAllDownloadTask() {
		List<DownLoadTask> list = new ArrayList<DownLoadTask>();
		DownLoadTask task = null;
		Cursor cursor = db.query(DOWNLOAD_TABLE, null, null, null, null, null, DownLoadTask.STATUS);
		while(cursor.moveToNext()) {
			task = restoreDownloadTaskFromCursor(cursor);
			list.add(task);
		}
		cursor.close();
		return list;
	}


	public void notifyDownloadStatusChanged(DownLoadTask task) {
		if (mDBcallback != null)
			mDBcallback.onDBOperationFinishedCallback(task);

	}
	
	private ContentValues CreatDLTaskValues(DownLoadTask task){
		ContentValues values = new ContentValues();
		values.put(DownLoadTask.ID, task.getId());
		values.put(DownLoadTask.URL, task.getUrl());
		values.put(DownLoadTask.MIMETYPE, task.getMimeType());
		values.put(DownLoadTask.SAVEPATH, task.getDlSavePath());
		values.put(DownLoadTask.FINISHEDSIZE, task.getDlFinishSize());
		values.put(DownLoadTask.TOTALSIZE, task.getDlTotalSize());
		values.put(DownLoadTask.NAME, task.getName());
		values.put(DownLoadTask.STATUS, task.getStatus());
		return  values;	
	}
	
	private DownLoadTask restoreDownloadTaskFromCursor(Cursor cursor) {
		DownLoadTask task = new DownLoadTask();
		task.setId(cursor.getString(cursor.getColumnIndex(DownLoadTask.ID)));
		task.setName(cursor.getString(cursor.getColumnIndex(DownLoadTask.NAME)));
		task.setUrl(cursor.getString(cursor.getColumnIndex(DownLoadTask.URL)));
		task.setMimeType(cursor.getString(cursor.getColumnIndex(DownLoadTask.MIMETYPE)));
		task.setDlSavePath(cursor.getString(cursor.getColumnIndex(DownLoadTask.SAVEPATH)));
		task.setDlFinishSize(cursor.getLong(cursor.getColumnIndex(DownLoadTask.FINISHEDSIZE)));
		task.setDlTotalSize(cursor.getLong(cursor.getColumnIndex(DownLoadTask.TOTALSIZE)));
		task.setStatus(cursor.getInt(cursor.getColumnIndex(DownLoadTask.STATUS)));
		return task;
	}

	public OnDBOperationFinishedCallback getmDBcallback() {
		return mDBcallback;
	}

	public void setmDBcallback(OnDBOperationFinishedCallback mDBcallback) {
		SqlLiteDownLoadProvider.mDBcallback = mDBcallback;
	}
}
