package com.ichsy.hrys.entity;


import com.ichsy.hrys.config.constants.StringConstant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息的实体
 * author: ihesen on 2016/5/17 18:02
 * email: hesen@ichsy.com
 */
public class ArtUserInfo implements Serializable {
    /**

     divideRate (number, optional),
     domainLabel (string, optional),
     sLoginCode (string, optional),

     */
    private int attentionNum = 0;
    private int fansNum = 0;
    private int postNum = 0;
    private String qrCodeUrl;
    /**
     * 是否已完善个人资料
     */

    public boolean state;
    /**
     *"男：dzsd4029100100030001，女：dzsd4029100100030002，未知：dzsd4029100100030003"
     */
    private String sex = "dzsd4029100100030003";
    private String userCode;
    private String userIconurl;
    //个人简介
    private String userIntroduction;
    //个人介绍
    private String personalIntroduction;
    private String userName = "未登录/立即登录";
    private String userToken;
    /**
     * 表示：当前用户是普通用户还是达人  暂时用不到 后期给出添加相关注释
     */
    private String userType;
    private String registTime;
    private String starts;
    private String qrCodeFlowUrl;

    private String userPhone;
    /**
     * 背景图链接
     */
    private String bjIconurl;

    /**
     * 头像缩略图
     * @return
     */
    private String userIconThumburl;
    /**
     * 额外添加字段： 当前是否是登录状态
     */
    public boolean  isLogin;

    /**
     * 额外添加字段： 已选择的领域标签
     */
    public List<MarsterFieldEntity> artMasterField;

    /** v1.2.0 个人资料领域标签*/
    public List<String> domainLabelList = new ArrayList<>();


    public String getQrCodeFlowUrl() {
        if (null == qrCodeFlowUrl )qrCodeFlowUrl = "";
        return qrCodeFlowUrl;
    }

    public void setQrCodeFlowUrl(String qrCodeFlowUrl) {
        this.qrCodeFlowUrl = qrCodeFlowUrl;
    }

    public int getAttentionNum() {
        return attentionNum;
    }

    public void setAttentionNum(int attentionNum) {
        this.attentionNum = attentionNum;
    }

    public int getFansNum() {
        return fansNum;
    }

    public void setFansNum(int fansNum) {
        this.fansNum = fansNum;
    }

    public int getPostNum() {
        return postNum;
    }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }

    public String getQrCodeUrl() {
        if (null == qrCodeUrl){
            qrCodeUrl = "";
        }
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public String getSex() {
        if (null == sex){
            sex = StringConstant.SEX_UNKNOW;
        }
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserCode() {
        if (null == userCode){
            userCode = "";
        }
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserIconurl() {
        if (null == userIconurl){
            userIconurl = "";
        }
        return userIconurl;
    }

    public void setUserIconurl(String userIconurl) {
        this.userIconurl = userIconurl;
    }

    public String getUserIntroduction() {
        if (null == userIntroduction){
            userIntroduction = "";
        }
        return userIntroduction;
    }

    public void setUserIntroduction(String userIntroduction) {
        this.userIntroduction = userIntroduction;
    }

    public String getPersonalIntroduction() {
        return personalIntroduction == null ? "" : personalIntroduction;
    }

    public void setPersonalIntroduction(String personalIntroduction) {
        this.personalIntroduction = personalIntroduction;
    }

    public String getUserName() {
        if (null == userName || "未登录/立即登录".equals(userName)){
            userName = "";
        }
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserToken() {
        if (null == userToken){
            userToken = "";
        }
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUserType() {
        if (null == userType){
            userType = "";
        }
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getRegistTime() {
        if (null == registTime){
            registTime = "";
        }
        return registTime;
    }

    public void setRegistTime(String registTime) {
        this.registTime = registTime;
    }

    public String getStarts() {
        if (null == starts){
            starts = "";
        }
        return starts;
    }

    public void setStarts(String starts) {
        this.starts = starts;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }


    public String getBjIconurl() {
        if (null == bjIconurl){
            bjIconurl = "";
        }
        return bjIconurl;
    }

    public void setBjIconurl(String bjIconurl) {
        this.bjIconurl = bjIconurl;
    }

    public String getUserIconThumburl() {
        return userIconThumburl;
    }

    public void setUserIconThumburl(String userIconThumburl) {
        this.userIconThumburl = userIconThumburl;
    }

}
