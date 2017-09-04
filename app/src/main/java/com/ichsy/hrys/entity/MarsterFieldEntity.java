package com.ichsy.hrys.entity;

import java.io.Serializable;

/**
 * 功能：擅长领域标签的实体
 * ＊创建者：赵然 on 16/8/18 16:40
 * ＊
 */
public class MarsterFieldEntity implements Serializable {
    private String fieldCode;
    private String fieldName ;
    public int position  ;
    private String showFlag  ;

    /**
     * 当前标签是否已选中
     */
    private boolean isChecked;


    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }


    public String getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(String showFlag) {
        this.showFlag = showFlag;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

}
