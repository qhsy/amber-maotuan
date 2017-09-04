package com.ichsy.hrys.entity.share;

import com.umeng.socialize.media.UMImage;

import java.io.File;
import java.util.List;

/**
 * 分享实体 内部使用的
 * 
 * Package: com.ichsy.syxd.bean.share  
 *  
 * File: ShareEntity.java   
 *  
 * @author: 赵然   Date: 2015-3-17  
 *
 * Modifier： Modified Date： Modify： 
 *  
 * Copyright @ 2015 ICHSY 
 *
 */
public class UMShareEntity {
	/**
	 * 分享的标题
	 */
	private String mTitle = "";
	/**
	 * 分享的链接地址
	 */
	private String mContentUrl = "";
	/**
	 * 分享的内容
	 */
	private String mContent = "";
	/**
	 * 分享的图片
	 */
	private UMImage mImage;
	/**
	 * 短信分享的内容
	 */
	private String mContentSms = "";
	/**
	 * 复制分享的内容
	 */
	private String mContentCopy = "";
	
	private List<File> files;
	
	private List<String> imageUrlList;

	public String getContentSms() {
		return mContentSms;
	}

	public void setContentSms(String contentSms) {
		this.mContentSms = contentSms;
	}

	public String getContentCopy() {
		return mContentCopy;
	}

	public void setContentCopy(String contentCopy) {
		this.mContentCopy = contentCopy;
	}

	public void setContent(String content) {
		this.mContent = content;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		this.mTitle = title;
	}

	public String getContentUrl() {
		return mContentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.mContentUrl = contentUrl;
	}

	public String getContent() {
		return mContent;
	}

	public UMImage getImage() {
		return mImage;
	}

	public void setImage(UMImage image) {
		this.mImage = image;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public List<String> getImageUrlList() {
		return imageUrlList;
	}

	public void setImageUrlList(List<String> imageUrlList) {
		this.imageUrlList = imageUrlList;
	}
	

}
