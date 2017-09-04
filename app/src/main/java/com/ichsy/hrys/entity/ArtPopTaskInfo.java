package com.ichsy.hrys.entity;

/**
 * 首页 - 任务列表 轮播 数据实体 /  注：v1.2.0 红人馆banner 也公用这个实体
 * author: ihesen on 2016/10/10 17:35
 * email: hesen@ichsy.com
 */

public class ArtPopTaskInfo {
    /**
     * 跳转类型 0:任务详情 1:Web页 / 红人馆：0:跳转URL,1:个人主页
     */
    public int gotoType;

    /**
     * 封面图片
     */
    private String imageUrl;
    /**
     * 跳转参数
     */
    public String param;

    public String getImageUrl() {
        if (imageUrl == null) {
            imageUrl = "";
        }
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
