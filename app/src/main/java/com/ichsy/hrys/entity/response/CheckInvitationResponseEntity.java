package com.ichsy.hrys.entity.response;

/**
 * 功能：邀请码 成为达人 请求返回实体
 * ＊创建者：赵然 on 2016/10/11 16:09
 * ＊
 */

public class CheckInvitationResponseEntity extends BaseResponse{
//    /**
//     * 用户类型 是否是达人
//     */
//    private String userType;
    /**
     * 用户达人类型  是否是经济公司达人
     */
    private boolean  companyFlag;
//
//    public String getUserType() {
//        if(null == userType){
//            userType ="";
//        }
//        return userType;
//    }

//    public void setUserType(String userType) {
//        this.userType = userType;
//    }

    public boolean isCompanyFlag() {
        return companyFlag;
    }

    public void setCompanyFlag(boolean companyFlag) {
        this.companyFlag = companyFlag;
    }
}
