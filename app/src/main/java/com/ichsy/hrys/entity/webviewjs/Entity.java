package com.ichsy.hrys.entity.webviewjs;


import java.io.Serializable;

/**
 * 所有JS互调返回的实体
 * 
 * Package: com.ichsy.minsns.entity
 *  
 * @author: 赵然   Date: 2015-2-11  
 *
 * Modifier： Modified Date： Modify： 
 *  
 * Copyright @ 2015 ICHSY 
 *
 */
public class Entity implements Serializable {

	/**
	 * 执行动作类型
	 */
	private String type = "";
	/**
	 * 订单编号
	 */
	private String orderId = "";

	/**
	 * 退款进度查询码
	 */
	private String returnCode = "";

	/**
	 * 退款金额
	 */
	private String orderMoney = "";

	/**
	 * 订单状态
	 */
	private String orderStatus = "";

	/**
	 * 执行动作所需的数据
	 */
	private ObjEntity obj;
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ObjEntity getObj() {
		return obj;
	}

	public void setObj(ObjEntity obj) {
		this.obj = obj;
	}

	public String getReturnCode() {
		return returnCode == null ? "" : returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getOrderMoney() {
		return orderMoney == null ? "" : orderMoney;
	}

	public void setOrderMoney(String orderMoney) {
		this.orderMoney = orderMoney;
	}

	public String getOrderStatus() {
		return orderStatus == null ? "" : orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderId() {
		return orderId == null ? "" : orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
