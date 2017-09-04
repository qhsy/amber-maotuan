package com.ichsy.hrys.entity.response;


import com.ichsy.hrys.entity.ArtUserInfo;

/**
 * 功能：登陆成功返回实体
 * ＊创建者：赵然 on 16/5/23 02:51
 * ＊
 */
public class LoginResponseEntity extends BaseResponse {
    /**
     *{
     "error": "ok",
     "status": "1",
     "userInfo": {
     "attentionNum": 0,
     "fansNum": 0,
     "postNum": 0,
     "qrCodeUrl": "string",
     "registTime": "string",
     "sex": "男：dzsd4029100100030001，女：dzsd4029100100030002，未知：dzsd4029100100030003",
     "starts": "string",
     "userCode": "string",
     "userIconurl": "string",
     "userIntroduction": "string",
     "userName": "string",
     "userType": "正常：dzsd4029100100020001，达人：dzsd4029100100020002"
     },
     "userToken": "string"
     }





     */
    /**
     * 用户token
     */
    private String userToken;
    /**
     * 标记用户是否绑定手机号  1 没 0 绑过
     */
    public int flag = 1;
    /**
     * 用户信息的实体
     */
    public ArtUserInfo userInfo = new ArtUserInfo();

    public String getUserToken() {
        if (null == userToken) userToken = "";
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
