package com.ichsy.hrys.entity;


import com.ichsy.hrys.entity.request.BaseRequest;

/**
 * 功能： 绑定银行卡接口的请求实体
 * ＊创建者：赵然 on 16/9/20 14:16
 * ＊
 */
public class BandBankCardRequestEntity extends BaseRequest {

    /**
     * 卡号
     */
    public String cardNumber;
    /**
     * 卡类型
     */
    public String cardType;
    /**
     * 用户名
     */
    public String userName;
    /**
     * 用户手机号
     */
    public String userPhone;
    /**
     * 卡名称
     */
    public String bankNmae;
    /**
     * 开户行信息
     */
    public String bankAccount;
}
