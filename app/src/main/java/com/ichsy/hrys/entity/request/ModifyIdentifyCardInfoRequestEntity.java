package com.ichsy.hrys.entity.request;

/**
 * 功能：修改证件信息的请求实体
 * ＊创建者：赵然 on 16/9/19 11:38
 * ＊
 */
public class ModifyIdentifyCardInfoRequestEntity extends BaseRequest {
    public String documentName;
    public String documentNumber;
    public String documentType;
}
