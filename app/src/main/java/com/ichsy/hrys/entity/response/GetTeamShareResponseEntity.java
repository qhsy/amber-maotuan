package com.ichsy.hrys.entity.response;


import com.ichsy.hrys.entity.ArtShareModel;

/**
 * 功能：获取 团队分享内容的请求返回实体
 * ＊创建者：赵然 on 2017/1/6 10:06
 * ＊
 */

public class GetTeamShareResponseEntity extends  BaseResponse {
    private String explainUrl;
    private String requestTeamQrCode;
    private ArtShareModel shareModel;

    public String getExplainUrl() {
        return explainUrl;
    }

    public void setExplainUrl(String explainUrl) {
        this.explainUrl = explainUrl;
    }

    public String getRequestTeamQrCode() {
        return requestTeamQrCode;
    }

    public void setRequestTeamQrCode(String requestTeamQrCode) {
        this.requestTeamQrCode = requestTeamQrCode;
    }

    public ArtShareModel getShareModel() {
        return shareModel;
    }

    public void setShareModel(ArtShareModel shareModel) {
        this.shareModel = shareModel;
    }
}
