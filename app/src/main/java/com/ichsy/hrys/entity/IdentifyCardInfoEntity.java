package com.ichsy.hrys.entity;

import java.io.Serializable;

/**
 * 功能：证件信息实体
 * ＊创建者：赵然 on 16/9/19 10:54
 * ＊
 */
public class IdentifyCardInfoEntity implements Serializable {
/**
 * "documentName": "string",
 "documentNumber": "string",
 "documentType": "string",
 "userCode": "string"
 */
    /**
     * 证件名称
     */
    private String documentName;
    /**
     * 证件号
     */
    private String documentNumber;
    /**
     * 证件类型
     */
    private String documentType;


    public String getDocumentName() {
        if (null == documentName) documentName = "";
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentNumber() {
        if (null == documentNumber) documentNumber = "";
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentType() {
        if (null == documentType) documentType = "";
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

}
