package com.ichsy.hrys.entity;

import java.util.List;

/**
 * author: zhu on 2017/8/25 15:55
 * email: mackkilled@gmail.com
 */

public class ArtVideoNotifactionMessage {
    private String commentContent;//评论内容 ,
    private String commentId;//评论Id ,
    private String commentSenderImgUrl;//评论发布者的头像地址 ,
    private String commentSenderTittle;//评论发布者的的昵称 ,
    private String commentTime;//评论发布的时间 ,
    private String userCodeSender;//评论发布者Code ,
    private String videoId;// 评论关联的视频Id

    private List<ArtVideoCommentReply> replyMessageList;//回复信息 ,

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentSenderImgUrl() {
        return commentSenderImgUrl;
    }

    public void setCommentSenderImgUrl(String commentSenderImgUrl) {
        this.commentSenderImgUrl = commentSenderImgUrl;
    }

    public String getCommentSenderTittle() {
        return commentSenderTittle;
    }

    public void setCommentSenderTittle(String commentSenderTittle) {
        this.commentSenderTittle = commentSenderTittle;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getUserCodeSender() {
        return userCodeSender;
    }

    public void setUserCodeSender(String userCodeSender) {
        this.userCodeSender = userCodeSender;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public List<ArtVideoCommentReply> getReplyMessageList() {
        return replyMessageList;
    }

    public void setReplyMessageList(List<ArtVideoCommentReply> replyMessageList) {
        this.replyMessageList = replyMessageList;
    }
}
