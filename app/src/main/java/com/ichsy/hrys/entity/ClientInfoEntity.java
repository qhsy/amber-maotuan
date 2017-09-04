/*
   * 文件名 ClientInfoEntity.java
   * 包含类名列表ClientInfoEntity
   * 版本信息，版本号
   * 创建日期2014年12月26日
   * 版权声明
   */
package com.ichsy.hrys.entity;

import java.io.Serializable;

/**
 * 类名：ClientInfoEntity
 * @author 戴小刚<br/>
 * 实现的主要功能:
 * 创建日期：2014年12月26日
 * 修改者，修改日期，修改内容。
 */
public class ClientInfoEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String product;
	private String os;
	private String screen;
	private String model;
	private String op;
	private String uniqid;
	private String mac;
	private String app_vision;
	private String net_type;
	private String from;
	private String os_info;
	private String sqNum;
	private String useID;
	
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getScreen() {
		return screen;
	}
	public void setScreen(String screen) {
		this.screen = screen;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getUniqid() {
		return uniqid;
	}
	public void setUniqid(String uniqid) {
		this.uniqid = uniqid;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getApp_vision() {
		return app_vision;
	}
	public void setApp_vision(String app_vision) {
		this.app_vision = app_vision;
	}
	public String getNet_type() {
		return net_type;
	}
	public void setNet_type(String net_type) {
		this.net_type = net_type;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getOs_info() {
		return os_info;
	}
	public void setOs_info(String os_info) {
		this.os_info = os_info;
	}
	public String getSqNum() {
		return sqNum;
	}
	public void setSqNum(String sqNum) {
		this.sqNum = sqNum;
	}
	public String getUseID() {
		return useID;
	}
	public void setUseID(String useID) {
		this.useID = useID;
	}
	@Override
	public String toString() {
		return "ClientInfoEntity [product=" + product + ", os=" + os
				+ ", screen=" + screen + ", model=" + model + ", op=" + op
				+ ", uniqid=" + uniqid + ", mac=" + mac + ", app_vision="
				+ app_vision + ", net_type=" + net_type + ", from=" + from
				+ ", os_info=" + os_info + ", sqNum=" + sqNum + ", useID="
				+ useID + "]";
	}
}
