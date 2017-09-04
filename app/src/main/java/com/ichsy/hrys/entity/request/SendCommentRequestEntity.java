package com.ichsy.hrys.entity.request;


import com.ichsy.hrys.config.constants.StringConstant;

/**
 * author: ihesen on 2016/5/18 16:43
 * email: hesen@ichsy.com
 */
public class SendCommentRequestEntity extends BaseRequest {

    public String postID;
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

    /**
     * 接收评论的用户信息
     */
    public String receiverCode;

}
