package com.ichsy.hrys.entity.response;


import com.ichsy.hrys.entity.AuthenticationMessage;

/**
 * 功能：申请成为达人的请求返回实体
 * ＊创建者：赵然 on 16/8/18 15:29
 * ＊
 */
public class AppToGeekResponseEntity extends  BaseResponse {
    private AuthenticationMessage artUserAuthentication;

    public AuthenticationMessage getArtUserAuthentication() {
        return artUserAuthentication;
    }

    public void setArtUserAuthentication(AuthenticationMessage artUserAuthentication) {
        this.artUserAuthentication = artUserAuthentication;
    }
}
