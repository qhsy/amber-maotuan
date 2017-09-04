package com.ichsy.hrys.entity;

import java.io.Serializable;

/**
 * author: zhu on 2017/8/25 15:11
 * email: mackkilled@gmail.com
 */

public class ArtVideoUserInfo implements Serializable {
    private String userCode; // 用户编号
    private String userIconThumburl; //头像压缩图
    private String userIconurl; //头像
    private String userIntroduction; // 个人简介 ,
    private String userName; //用户姓名

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserIconThumburl() {
        return userIconThumburl;
    }

    public void setUserIconThumburl(String userIconThumburl) {
        this.userIconThumburl = userIconThumburl;
    }

    public String getUserIconurl() {
        return userIconurl;
    }

    public void setUserIconurl(String userIconurl) {
        this.userIconurl = userIconurl;
    }

    public String getUserIntroduction() {
        return userIntroduction;
    }

    public void setUserIntroduction(String userIntroduction) {
        this.userIntroduction = userIntroduction;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
