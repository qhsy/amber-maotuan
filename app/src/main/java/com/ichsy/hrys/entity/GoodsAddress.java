package com.ichsy.hrys.entity;

import java.io.Serializable;

/**
 * 功能：收货地址的
 * ＊创建者：赵然 on 16/5/13 13:54
 * ＊
 */
public class GoodsAddress implements Serializable {

    /**
     * 收货地址编号	收货地址的唯一编号
     */
    public String addressId;
    /**
     * 收货人的姓名
     */
    public String addressName;
    /**
     *收货人手机号
     */
    public String addressMobile;
    /**
     * 邮政编码
     */
    public String addressPostalcode;
    /**
     * 是否是默认地址 0:否  1：是
     */
    public String addressDefault = "0";
    /**
     * 用户编号
     */
    private String userCode;

    /**
     * 地址的省
     */
    public String addressProvince;
    /**
     * 地址的市
     */
    public String addressCity;
    /**
     * 地址的区
     */
    public String addressDistrict;
    /**
     * 地址的详细描述
     */
    public String addressDetail;
    /**
     *地址的区编号	收货地址区的全国区编号
     */
    public String addressDistrictCode;


}
