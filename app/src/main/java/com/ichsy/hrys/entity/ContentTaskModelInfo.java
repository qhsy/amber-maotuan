package com.ichsy.hrys.entity;

/**
 * 视频item分类
 */

public class ContentTaskModelInfo {

    /**
     *类型：1:推荐 2:最新 3:最热
     */
    private String type;

    private String title;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
