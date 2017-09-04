package com.ichsy.hrys.entity.response;

/**
 * 功能：检查更新的请求返回结果
 * ＊创建者：赵然 on 16/5/29 23:01
 * ＊
 */
public class CheckUpdateResponseEntity  extends BaseResponse{
    private String appUrl;
    private String upgradeContent;
    /**
     * 更新类型 0：不更  1：静默  2：提示 3 强更
     */
    private String upgradeType;
    private String versionNo;


    public String getAppUrl() {
        if (null == appUrl) appUrl = "";
        return appUrl;
    }

    public void setAppUrl(String appUrl) {

        this.appUrl = appUrl;
    }

    public String getUpgradeContent() {
        if (null == upgradeContent) upgradeContent = "";
        return upgradeContent;
    }

    public void setUpgradeContent(String upgradeContent) {
        this.upgradeContent = upgradeContent;
    }

    public String getUpgradeType() {
        if (null == upgradeType) upgradeType = "";
        return upgradeType;
    }

    public void setUpgradeType(String upgradeType) {
        this.upgradeType = upgradeType;
    }

    public String getVersionNo() {
        if (null == versionNo) versionNo = "";
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }
}
