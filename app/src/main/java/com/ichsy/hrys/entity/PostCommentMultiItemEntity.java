package com.ichsy.hrys.entity;


import com.ichsy.hrys.entity.baserecyclerview.MultiItemEntity;

/**
 * 帖子评论实体
 * author: ihesen on 2016/5/11 14:08
 * email: hesen@ichsy.com
 */
public class PostCommentMultiItemEntity extends MultiItemEntity {

    /**
     * 回复评论（回复帖子）
     */
    public static final int COMMENT_POST_TYPE = 5;
    /**
     * 回复评论（回复人）
     */
    public static final int COMMENT_USER_TYPE = 6;

    public Comment comment = new Comment();

    public PostCommentMultiItemEntity(int itemType) {
        super(itemType);
    }
}
