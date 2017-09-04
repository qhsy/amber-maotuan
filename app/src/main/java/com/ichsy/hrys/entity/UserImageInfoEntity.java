package com.ichsy.hrys.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：用户形象实体
 * ＊创建者：赵然 on 2016/12/1 15:32
 * ＊
 */

public class UserImageInfoEntity {


    /**
     *审核失败原因
     */
    private String auditFailure;
    private String state;
    /**
     *形象照
     */
    private String imageAccording;
    public List<ArtPic> imageAccordingList = new ArrayList<>();
    /**
     * 胸围
     */
    private String userBust;
    /**
     *身高
     */
    private String userHeight;
    /**
     * 臀围
     */
    private String userHipline;
    /**
     *腰围
     */
    private String userTheWaist;
    /**
     *体重
     */
    private String userWeight;



    public String getImageAccording() {
        return imageAccording;
    }

    public void setImageAccording(String imageAccording) {
        this.imageAccording = imageAccording;
    }

    public String getUserBust() {
        if (null== userBust) userBust = "";
        return userBust;
    }

    public void setUserBust(String userBust) {
        this.userBust = userBust;
    }

    public String getUserHeight() {
        if (null == userHeight) userHeight = "";
        return userHeight;
    }

    public void setUserHeight(String userHeight) {
        this.userHeight = userHeight;
    }

    public String getUserHipline() {
        if (null == userHipline) userHipline = "";
        return userHipline;
    }

    public void setUserHipline(String userHipline) {
        this.userHipline = userHipline;
    }

    public String getUserTheWaist() {
        if (null == userTheWaist) userTheWaist = "";
        return userTheWaist;
    }

    public void setUserTheWaist(String userTheWaist) {
        this.userTheWaist = userTheWaist;
    }

    public String getUserWeight() {
        if (null == userWeight) userWeight = "";
        return userWeight;
    }

    public void setUserWeight(String userWeight) {
        this.userWeight = userWeight;
    }

    public String getAuditFailure() {
        if (null == auditFailure) auditFailure = "";
        return auditFailure;
    }

    public void setAuditFailure(String auditFailure) {
        this.auditFailure = auditFailure;
    }

    public String getState() {
        if (null == state) state = "";
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
