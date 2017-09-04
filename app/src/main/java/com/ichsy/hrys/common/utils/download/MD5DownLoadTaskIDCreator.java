package com.ichsy.hrys.common.utils.download;

/**
 * 此处可以做扩展  修改createID的方法即可
 */
public class MD5DownLoadTaskIDCreator implements DownLoadTaskIDCreator{

	@Override
	public String createId(DownLoadTask task) {
		return task.getId();
	}

}
