package com.ichsy.hrys.entity.request;

/**
 * Created by zhu on 2017/8/26.
 */

public class ArtSendVideoCommentInput extends BaseRequest {
    public String commentContent; //评论内容 ,
    public String videoId; //视频ID ,
    public String publishType; //发布类型 0:评论,1:回复 ,


    public String commentId; //评论Id(回复时传)
    public String receiverCode; //接收评论的用户编号(回复时传) ,
}
