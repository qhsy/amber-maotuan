package com.ichsy.hrys.entity.request;


import com.ichsy.hrys.entity.AuthorizationInformation;

/**
 * 功能： 申请成为达人的请求实体
 * ＊创建者：赵然 on 16/8/18 14:06
 * ＊
 */
public class ApplyToGeekRequestEntity extends BaseRequest {

    /**
     * 认证信息
     */
    public AuthorizationInformation artAuthinfo =  new AuthorizationInformation();

}
