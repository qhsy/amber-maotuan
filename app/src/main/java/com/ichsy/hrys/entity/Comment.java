package com.ichsy.hrys.entity;


import com.ichsy.hrys.config.constants.StringConstant;

/**
 * author: ihesen on 2016/5/11 15:48
 * email: hesen@ichsy.com
 */
public class Comment {

    /**
     * 发送评论的用户信息
     */
    public ArtUserInfo senderInfo = new ArtUserInfo();
    /**
     * 接收评论的用户信息
     */
    public ArtUserInfo receiverInfo = new ArtUserInfo();
    /**
     * 回复内容
     */
    public String commentContent;
    /**
     * 是否是评论帖子(默认是帖子回复)
     * dzsd4029100100110001帖子的评论
     * dzsd4029100100110002评论的评论
     */
    public String isCommentPost = StringConstant.COMMENT_POST;

    public long commentTime;
}
