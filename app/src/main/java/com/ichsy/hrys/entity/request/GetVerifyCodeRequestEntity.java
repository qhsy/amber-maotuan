package com.ichsy.hrys.entity.request;

/**
 * 功能：获取验证码接口 的请求实体
 * ＊创建者：赵然 on 16/5/23 02:19
 * ＊
 */
public class GetVerifyCodeRequestEntity extends BaseRequest{
    /**
     * {
     "phoneNum": "string",
     "type": register(注册,41070001),login(登录,41070002),resetpwd(重置密码,41070003),forgetpwd(忘记密码,41070004),weChatlogin(微信登录),maotuan(毛团儿)
     "zoo": {
     "key": "tesetkey",
     "token": " "
     }
     }
     */
    public String phoneNum;
    public String type;

}
