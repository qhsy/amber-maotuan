package com.ichsy.hrys.entity;

/**
 * 帖子简单实体
 * author: ihesen on 2016/5/9 14:23
 * email: hesen@ichsy.com
 */
public class ArtSimplePost {
    /**
     * 帖子ID
     */
    public String postID;
    /**
     * 图片
     */
    public String imageUrl;
    /**
     * 标题
     */
    public String title;
    /**
     * 发送者头像地址
     */
    public String posterAvator;
    /**
     * dzsd4029100100060001图文
     * dzsd4029100100060002视频
     */
    public String type;
    /**
     * 评论数	（个人主页，列表）
     */
    public int commentNum;
    /**
     * 分享次数
     */
    public int shareCount;
    /**
     * 收藏数	（个人主页，列表）
     */
    public int saveNum;
    /**
     * 发送人信息
     */
    public ArtUserInfo senderInfo = new ArtUserInfo();
    /**
     * 帖子标签类型
     */
    public ArtPostTypeInfo postType = new ArtPostTypeInfo();
    /**
     * 帖子描述
     */
    public String postInstroduce;

    /**
     * 发帖时间
     */
    public long postTime;
    /**
     * 图片文字字段
     */
    public String imageDescription;

}
