package com.ichsy.hrys.entity;

/**
 * 功能： 授权信息
 * ＊创建者：赵然 on 16/8/18 14:07
 * ＊
 */
public class AuthorizationInformation {
    private String platformType;
    private String refreshToken;
    private String uid;
    private String accessToken;
    private String extraParams;

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExtraParams() {
        return extraParams;
    }

    public void setExtraParams(String extraParams) {
        this.extraParams = extraParams;
    }
}
