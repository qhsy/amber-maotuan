package com.ichsy.hrys.entity.request;

/**
 * 功能： 获取银行信息接口的请求实体
 * ＊创建者：赵然 on 16/9/20 13:49
 * ＊
 */
public class GetBankCardMsgRequestEntity extends  BaseRequest {

    /**
     * 绑定的银行卡的卡号
     */
    public String bankCardNumber;
}
