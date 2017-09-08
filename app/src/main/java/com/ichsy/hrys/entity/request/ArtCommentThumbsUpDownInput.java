package com.ichsy.hrys.entity.request;

/**
 * author: zhu on 2017/9/8 17:31
 * email: mackkilled@gmail.com
 */

public class ArtCommentThumbsUpDownInput extends BaseRequest{
    public String commentId; //评论Id ,
    public String thumbsType; //点赞类型 1：点赞 0：撤销赞 ,
}
