package com.ichsy.hrys.entity.request;

/**
 * 创建订单支付接口参数实体
 * author: ihesen on 2016/5/25 11:38
 * email: hesen@ichsy.com
 */
public class PayInfoRequestEntity extends BaseRequest {

    /**
     * 商品购买数量
     */
    public int goodsCount;
    /**
     * 商品SKU信息
     */
    public String goodsSKUCode;
    /**
     * 收货地址编号
     */
    public String goodsaddressCode;
    /**
     * 订单金额
     */
    public double orderMoney;
    /**
     * 支付类型(支付宝："1", 微信："2")
     */
    public String payType;
    /**
     * 订单渠道字段，1是APP,2是wap(客服端默认写死为1)
     */
    public String orderChannel = "1";

}
