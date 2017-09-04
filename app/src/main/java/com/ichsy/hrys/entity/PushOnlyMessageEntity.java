package com.ichsy.hrys.entity;

/**
 * 功能：百度透传消息所需实体
 * ＊创建者：赵然 on 2016/11/17 10:05
 * ＊
 */

public class PushOnlyMessageEntity {
//    {
//        "title" : "hello" ,
//            "description": "hello world" //必选
//        "notification_builder_id": 0, //可选
//            "notification_basic_style": 7, //可选
//            "open_type":0, //可选
//            "url": "http://developer.baidu.com", //可选
//            "pkg_content":"", //可选
//            "custom_content":{"key":"value"},
//    }
    private String title;
    private String description;
    private int notification_builder_id;
    private int notification_basic_style;
    private int open_type;
    private String url;
    private String pkg_content;
    private PushEntity custom_content;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNotification_builder_id() {
        return notification_builder_id;
    }

    public void setNotification_builder_id(int notification_builder_id) {
        this.notification_builder_id = notification_builder_id;
    }

    public int getNotification_basic_style() {
        return notification_basic_style;
    }

    public void setNotification_basic_style(int notification_basic_style) {
        this.notification_basic_style = notification_basic_style;
    }

    public int getOpen_type() {
        return open_type;
    }

    public void setOpen_type(int open_type) {
        this.open_type = open_type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPkg_content() {
        return pkg_content;
    }

    public void setPkg_content(String pkg_content) {
        this.pkg_content = pkg_content;
    }

    public PushEntity getCustom_content() {
        return custom_content;
    }

    public void setCustom_content(PushEntity custom_content) {
        this.custom_content = custom_content;
    }
}
