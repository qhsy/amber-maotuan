package com.ichsy.hrys.pay.bean;

/**
 * 支付参数
 *
 * @author ihesen
 */
public class PayParams {

    /**
     * 支付宝相关支付参数
     **/
    public String alipayUrl;
    public String orderCode;

    /**
     * 微信支付相关参数
     **/
    public WeChatpaymentResult weiChatPayReq;
}