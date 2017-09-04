package com.ichsy.hrys.entity.response;


import com.ichsy.hrys.entity.ArtUserInfo;
import com.ichsy.hrys.entity.AuthenticationMessage;
import com.ichsy.hrys.entity.MarsterFieldEntity;
import com.ichsy.hrys.entity.UserImageInfoEntity;
import com.ichsy.hrys.entity.UserSocialMediaEntity;

import java.util.List;

/**
 * 功能：获取个人信息--我的页面 的返回实体
 * ＊创建者：赵然 on 16/5/23 11:03
 * ＊
 */
public class GetUserAccountInfoResponseEntity extends  BaseResponse {
    /**
     * 审核状态 dzsd4029100100320001,待审核，dzsd4029100100320002审核成功，dzsd4029100100320003审核失败
     */
    private String auditStatus;
    /**
     * 用户信息
     */
    public ArtUserInfo userInfo = new ArtUserInfo();
    private String qrCodeFlowUrl;
    /**
     * 形象资料
     */
    private UserImageInfoEntity artPersonalImage;
    /**
     * 平台认证信息 1.1.8 废弃
     */
    @Deprecated
    private AuthenticationMessage artUserAuthentication;
    /**
     * 已认证的社交媒体
     */
    private List<UserSocialMediaEntity> list;
    /**
     * 我的账户余额
     */
    public String accountMoney;
    /**
     * 我收藏的任务数
     */
    public int collectTaskCount;
    /**
     * 我参与的任务数
     */
    public int partTaskCount;
    /**
     * 是否接收推送 0是推送，1是不推送
     */
    public int whetherPush;
    /**
     * 领域标签
     */
    private List<MarsterFieldEntity> artMasterField;

    public String getQrCodeFlowUrl() {
        return qrCodeFlowUrl;
    }

    public void setQrCodeFlowUrl(String qrCodeFlowUrl) {
        this.qrCodeFlowUrl = qrCodeFlowUrl;
    }

    public String getAccountMoney() {
        if (null == accountMoney) {
            accountMoney = "";
        }
        return accountMoney;
    }

    public void setAccountMoney(String accountMoney) {
        this.accountMoney = accountMoney;
    }

    public ArtUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(ArtUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserImageInfoEntity getArtPersonalImage() {
        return artPersonalImage;
    }

    public void setArtPersonalImage(UserImageInfoEntity artPersonalImage) {
        this.artPersonalImage = artPersonalImage;
    }

    public AuthenticationMessage getArtUserAuthentication() {
        return artUserAuthentication;
    }

    public void setArtUserAuthentication(AuthenticationMessage artUserAuthentication) {
        this.artUserAuthentication = artUserAuthentication;
    }

    public int getCollectTaskCount() {
        return collectTaskCount;
    }

    public void setCollectTaskCount(int collectTaskCount) {
        this.collectTaskCount = collectTaskCount;
    }

    public int getPartTaskCount() {
        return partTaskCount;
    }

    public void setPartTaskCount(int partTaskCount) {
        this.partTaskCount = partTaskCount;
    }

    public int getWhetherPush() {
        return whetherPush;
    }

    public void setWhetherPush(int whetherPush) {
        this.whetherPush = whetherPush;
    }

    public void setList(List<UserSocialMediaEntity> list) {
        this.list = list;
    }

    public List<MarsterFieldEntity> getArtMasterField() {
        return artMasterField;
    }

    public void setArtMasterField(List<MarsterFieldEntity> artMasterField) {
        this.artMasterField = artMasterField;
    }

    public List<UserSocialMediaEntity> getList() {
        return list;
    }

    public String getAuditStatus() {
        if (null == auditStatus) auditStatus = "";
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }
}
