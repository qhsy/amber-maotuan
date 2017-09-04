package com.ichsy.hrys.pay;

import com.ichsy.hrys.pay.factory.AliPayImpl;
import com.ichsy.hrys.pay.factory.PayInterface;
import com.ichsy.hrys.pay.factory.WeiChatImpl;

/**
 * author: ihesen on 2016/6/14 16:46
 * email: hesen@ichsy.com
 */
public class PayUtils {

    public static PayInterface create(PayType payType){
        switch (payType){
            case ALIPAY_TYPE:
                return new AliPayImpl();
            case WXPAY_TYPE:
                return new WeiChatImpl();
            default:
                return new AliPayImpl();
        }
    }

    public enum PayType {
        /** 支付宝支付类型 **/
        WXPAY_TYPE,
        /** 微信支付类型 **/
        ALIPAY_TYPE
    }
}
