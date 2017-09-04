package com.ichsy.hrys.pay.bean;

import java.io.Serializable;

/**
 * @author ihesen
 *         微信支付相关参数
 */
public class WeChatpaymentResult implements Serializable {

    private static final long serialVersionUID = 6109935663247184197L;
    /**
     * 商户ID
     */
    public String partnerId;
    /**
     * 预支付订单
     */
    public String prepayId;
    /**
     * 应用ID
     */
    public String appId;

    /**
     * 随机串
     */
    public String nonceStr;
    /**
     * 当前时间
     */
    public String timeStamp;
    /**
     * 商家开放平台签名
     */
    public String sign;
    /**
     * 支付标识
     */
    public String packageString;

    @Override
    public String toString() {
        return "WeChatpaymentResult [partnerId=" + partnerId + ", prepayId=" + prepayId + ", appId=" + appId + ", nonceStr=" + nonceStr + ", timeStamp=" + timeStamp + ", sign=" + sign + "]";
    }
}
