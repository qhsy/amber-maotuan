package com.ichsy.hrys.entity;

import java.io.Serializable;

/**
 * 功能：百度推送传递来数据
 * ＊创建者：赵然 on 2016/11/11 14:31
 * ＊
 */

public class PushEntity implements Serializable {
    private String type;
    private String params;
    private String userId;
    private String channleId;

    public String getType() {
        if (null == type) type = "";
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParams() {
        if (null == params) params = "";
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChannleId() {
        return channleId;
    }

    public void setChannleId(String channleId) {
        this.channleId = channleId;
    }

    @Override
    public String toString() {
        return "PushEntity{" +
                "type='" + type + '\'' +
                ", params='" + params + '\'' +
                ", userId='" + userId + '\'' +
                ", channleId='" + channleId + '\'' +
                '}';
    }
}
