package com.ichsy.hrys.entity.response;


import com.ichsy.hrys.entity.AuthenticationMessage;
import com.ichsy.hrys.entity.MarsterFieldEntity;

import java.util.List;

/**
 * 功能：
 * ＊创建者：赵然 on 16/8/18 16:46
 * ＊
 */
public class GetGeekInfoResponseEntity extends BaseResponse {

    private List<AuthenticationMessage> artUserAuthentication;
    private List<MarsterFieldEntity> artMasterField;
    /**
     * 是否所属经纪公司
     */

    private boolean companyFlag;
    /**
     * dzsd4029100100020002 是达人
     */
//    private String whetherPeople;

    public List<AuthenticationMessage> getArtUserAuthentication() {
        return artUserAuthentication;
    }

    public void setArtUserAuthentication(List<AuthenticationMessage> artUserAuthentication) {
        this.artUserAuthentication = artUserAuthentication;
    }

    public List<MarsterFieldEntity> getArtMasterField() {
        return artMasterField;
    }

    public void setArtMasterField(List<MarsterFieldEntity> artMasterField) {
        this.artMasterField = artMasterField;
    }

//    public String getWhetherPeople() {
//        if (whetherPeople == null) {
//
//            whetherPeople = LoginUtils.isExpertUser(AppApplication.mContext)? StringConstant.EXPERT_USER_TYPE:"";
//        }
//        return whetherPeople;
//    }
//
//    public void setWhetherPeople(String whetherPeople) {
//        this.whetherPeople = whetherPeople;
//    }

    public boolean isCompanyFlag() {
        return companyFlag;
    }

    public void setCompanyFlag(boolean companyFlag) {
        this.companyFlag = companyFlag;
    }
}
