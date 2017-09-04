package com.ichsy.hrys.entity.request;

import com.ichsy.hrys.entity.PageOption;

/**
 * 任务订单列表实体
 * author: ihesen on 2016/11/3 15:25
 * email: hesen@ichsy.com
 */

public class ArtSaleTaskOrderRequestEntity extends BaseRequest {

    /**
     * 订单类型 已收货(ocsd439310011004),已付款(ocsd439310011002),未付款(ocsd439310011001),已发货(ocsd439310011003)
     */
    public String orderType = "";
    /**
     * 任务编号
     */
    public String taskCode = "";

    /** 分页*/
    public PageOption artPageOption = new PageOption();
}
