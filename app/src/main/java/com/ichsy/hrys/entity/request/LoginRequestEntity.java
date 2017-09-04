package com.ichsy.hrys.entity.request;

/**
 * 功能：验证码登录的请求实体
 * ＊创建者：赵然 on 16/5/23 02:38
 * ＊
 */
public class LoginRequestEntity extends  BaseRequest{
    /**
     * {
     " {
     "parentCode": "",
     "phoneNum": "15110233517",
     "verifyCode": "000002",
     "verifyNumber": "tesetkey",
     "zoo": {
     "key": "",
     "token": " "
     }
     }
     */





    /**
     * 推荐人手机号
     */
    public String parentCode;
    public String phoneNum;
    public String verifyCode;
    /**
     * 流水号
     */
    public String verifyNumber;
    public String registChannel = "android";
}
