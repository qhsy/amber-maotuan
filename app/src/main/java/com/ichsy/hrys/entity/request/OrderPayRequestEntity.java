package com.ichsy.hrys.entity.request;

/**
 * 订单支付接口请求实体
 * author: ihesen on 2016/5/26 10:59
 * email: hesen@ichsy.com
 */
public class OrderPayRequestEntity extends BaseRequest {
    /**
     * 订单编号
     */
    public String orderCode;
    /**
     * 支付类型(支付宝:"1", 微信:"2")
     */
    public String payType;
    /**
     * 手机ip地址
     */
    public String ip;
}
