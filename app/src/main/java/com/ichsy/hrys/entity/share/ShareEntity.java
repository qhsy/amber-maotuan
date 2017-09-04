package com.ichsy.hrys.entity.share;

import android.graphics.Bitmap;

import java.io.Serializable;

/**  
 * 暴露出来的分享实体
 * 
 * Package: com.ichsy.syxd.bean  
 *  
 * File: ShareEntity.java   
 *  
 * @author: 赵然   Date: 2015-3-17  
 *
 * Modifier： Modified Date： Modify： 
 *  
 * Copyright @ 2015 Corpration ICHSY
 * 
 */
public class ShareEntity implements Serializable{

	/**
	 * 分享标题
	 */
	private String shareTittle = "" ;
	/**
	 * 分享的内容
	 */
	private String shareContent = "" ;
	/**
	 * 分享的跳转链接
	 */
	private String shareTargetUrl = "" ;
	/**
	 * 分享网络的图片
	 */
	private String imageUrl = "" ;
	/**
	 * 分享本地bitmap
	 */
	private Bitmap imageBitmap;
	/**
	 * 图片ID
	 */
	private int imageID;
	/**
	 * 图片类型
	 */
	private String imageType = "";
	public String getShareTittle() {
		return shareTittle;
	}
	public void setShareTittle(String shareTittle) {
		this.shareTittle = shareTittle;
	}
	public String getShareContent() {
		return shareContent;
	}
	public void setShareContent(String shareContent) {
		this.shareContent = shareContent;
	}
	public String getShareTargetUrl() {
		return shareTargetUrl;
	}
	public void setShareTargetUrl(String shareTargetUrl) {
		this.shareTargetUrl = shareTargetUrl;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
		setImageType("url");
	}
	public Bitmap getImageBitmap() {
		return imageBitmap;
	}
	public void setIamgeBitmap(Bitmap imageBitmap) {
		this.imageBitmap = imageBitmap;
		setImageType("bitmap");
	}
	public int getImageID() {
		return imageID;
	}
	public void setImageID(int imageID) {
		this.imageID = imageID;
		setImageType("id");
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public void setImageBitmap(Bitmap imageBitmap) {
		this.imageBitmap = imageBitmap;
	}
	 
	
}
