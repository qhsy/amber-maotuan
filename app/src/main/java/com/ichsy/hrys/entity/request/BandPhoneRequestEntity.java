package com.ichsy.hrys.entity.request;

/**
 * 功能：绑定手机号的请求实体
 * ＊创建者：赵然 on 16/5/26 15:59
 * ＊
 */
public class BandPhoneRequestEntity extends  BaseRequest{
    /**
     * {
     "openId": "string",
     "phoneNum": "string",
     "verifyCode": "string",
     "verifyNumber": "string",
     "zoo": {
     "key": "tesetkey",
     "token": " "
     }
     }
     */
    public String openId;
    public String phoneNum;
    public String verifyCode;
    public String verifyNumber;
    public String accessToken;
    public String registChannel = "android";

}
