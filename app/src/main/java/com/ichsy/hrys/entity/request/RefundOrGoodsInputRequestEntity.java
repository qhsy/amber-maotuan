package com.ichsy.hrys.entity.request;

import java.util.List;

/**
 * 退款或退货请求参数
 * author: zhu on 2017/6/1 17:07
 * email: mackkill@gmail.com
 */

public class RefundOrGoodsInputRequestEntity extends BaseRequest {
    //退款参数
    public String orderCode; // 订单编号 ,
    public String orderStatus; // 订单状态 ,
    public String returnExplain; // 退款说明 ,
    public double returnMoney;  //退款金额 ,
    public String returnReason;  //退款原因 ,
    public String returnGoodsCode; //退款货单编号

    //退货参数
    public List<String> picUrls;         // 图片url数组
}
