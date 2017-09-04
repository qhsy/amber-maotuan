package com.ichsy.hrys.entity;

/**
 * 功能： 获取团队成员的信息实体
 * ＊创建者：赵然 on 2017/1/6 10:19
 * ＊
 */

public class TeamUserInfo {

    /**
     * 贡献金额
     */
    private String offerMoney;
    /**
     * 用户编号
     */
    private String userCode;
    /**
     * 用户头像
     */
    private String userIconurl;
    /**
     * 用户姓名
     */
    private String userName;

    public String getOfferMoney() {
        return offerMoney;
    }

    public void setOfferMoney(String offerMoney) {
        this.offerMoney = offerMoney;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserIconurl() {
        return userIconurl;
    }

    public void setUserIconurl(String userIconurl) {
        this.userIconurl = userIconurl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
