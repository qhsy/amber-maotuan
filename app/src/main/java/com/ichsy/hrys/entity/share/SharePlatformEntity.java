package com.ichsy.hrys.entity.share;
/**  
 * 
 * Package: com.ichsy.syxd.bean.share  
 *  
 * File: SharePlatformEntity.java   
 *  
 * @author: 赵然   Date: 2015-3-23  
 *
 * Modifier： Modified Date： Modify： 
 *  
 * Copyright @ 2015 Corpration ICHSY
 * 
 */
public class SharePlatformEntity {

	/**
	 * um中平台对应的名称
	 */
	private String umPlateformName;
	/**
	 * 平台对应的图片ID
	 */
	private int plateFormIconID = 0;
	/**
	 * 平台展示的名字
	 */
	private String plateFormName;

	public String getUmPlateformName() {
		return umPlateformName;
	}
	public void setUmPlateformName(String umPlateformName) {
		this.umPlateformName = umPlateformName;
	}
	public int getPlateFormIconID() {
		return plateFormIconID;
	}
	public void setPlateFormIconID(int plateFormIconID) {
		this.plateFormIconID = plateFormIconID;
	}
	public String getPlateFormName() {
		return plateFormName;
	}
	public void setPlateFormName(String plateFormName) {
		this.plateFormName = plateFormName;
	}





}
