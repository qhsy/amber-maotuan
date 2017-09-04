package com.ichsy.hrys.entity;

/**
 * 功能：授权人信息
 * ＊创建者：赵然 on 16/8/18 15:32
 * ＊
 */
public class AuthenticationMessage {

    private String authenticationHead;

    private String authenticationId;
    /**
     * dzsd4029100100190001	新浪微博
     * dzsd4029100100190002	微信公众平台
     * dzsd4029100100190003	优酷
     */
    private String authenticationPlatform;
    /**
     * dzsd4029100100240001	未审核
     dzsd4029100100240003	审核失败
     dzsd4029100100240002	审核成功
     */

    private String authenticationStats;
    private String authenticationTime;
    private String certificationFailureReason;
    private int fansNum;
    private int followNum;
    private String nickName;
    private String personalHomepage;
    private String refreshToken;
    private String userCode;
    private String userPhone;
    private String userToken;

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

    public String getAuthenticationStats() {
        return authenticationStats;
    }

    public void setAuthenticationStats(String authenticationStats) {
        this.authenticationStats = authenticationStats;
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

    public int getFansNum() {
        return fansNum;
    }

    public void setFansNum(int fansNum) {
        this.fansNum = fansNum;
    }

    public int getFollowNum() {
        return followNum;
    }

    public void setFollowNum(int followNum) {
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

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
