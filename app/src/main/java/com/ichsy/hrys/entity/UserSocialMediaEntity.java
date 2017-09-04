package com.ichsy.hrys.entity;


/**
 * 功能：认证平台对应的信息
 * ＊创建者：赵然 on 2016/12/1 17:10
 * ＊
 */

public class UserSocialMediaEntity {
    
    /**
     *社交平台编号
     */
    private String platformCode;
    /**
     *社交平台编号
     */
    private String platformId;
    /**
     *社交平台名称
     */
    private String platformName;
    /**
     *用户编号
     */
    private String userCode;
    /**
     * 用户头像
     */
    private String authenticationHead;
    private String authenticationId;
    private String authenticationPlatform;
    /**
     * 审核状态
     */
    private String state;
    private String authenticationTime;
    private String certificationFailureReason;
    private String fansNum;
    private String followNum;
    private String nickName;
    private String personalHomepage;
    private String userToken;
    private String userPhone;


    /**v1.2.0 个人资料 添加添加粉丝数*/
    private String fansCount;

    private  int drawId;

    public String getPlatformCode() {
        return platformCode;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }
    

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    // TODO: 2017/8/22 shareEntity 

    public void setDrawId(int drawId) {
        this.drawId = drawId;
    }

    public String getFansCount() {
        if(fansCount == null){
            fansCount = "0";
        }
        return fansCount;
    }

    public void setFansCount(String fansCount) {
        this.fansCount = fansCount;
    }


    public String getAuthenticationHead() {
        return authenticationHead;
    }

    public void setAuthenticationHead(String authenticationHead) {
        this.authenticationHead = authenticationHead;
    }

    public String getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    public String getAuthenticationPlatform() {
        return authenticationPlatform;
    }

    public void setAuthenticationPlatform(String authenticationPlatform) {
        this.authenticationPlatform = authenticationPlatform;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAuthenticationTime() {
        return authenticationTime;
    }

    public void setAuthenticationTime(String authenticationTime) {
        this.authenticationTime = authenticationTime;
    }

    public String getCertificationFailureReason() {
        return certificationFailureReason;
    }

    public void setCertificationFailureReason(String certificationFailureReason) {
        this.certificationFailureReason = certificationFailureReason;
    }

    public String getFansNum() {
        return fansNum;
    }

    public void setFansNum(String fansNum) {
        this.fansNum = fansNum;
    }

    public String getFollowNum() {
        return followNum;
    }

    public void setFollowNum(String followNum) {
        this.followNum = followNum;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPersonalHomepage() {
        return personalHomepage;
    }

    public void setPersonalHomepage(String personalHomepage) {
        this.personalHomepage = personalHomepage;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

}
