package com.ichsy.hrys.entity.response;

import com.ichsy.hrys.pay.bean.WeChatpaymentResult;

/**
 * 创建订单支付接口返回数据实体(订单支付接口也可以使用)
 * author: ihesen on 2016/5/25 13:42
 * email: hesen@ichsy.com
 */
public class PayInfoResponse extends BaseResponse {
    /**
     * 支付链接
     */
    public String alipayUrl = "";
    /**
     * 订单编号
     */
    public String orderCode;
    /**
     * 微信支付相关参数
     **/
    public WeChatpaymentResult weChatPayAppParams =  new WeChatpaymentResult();
}
