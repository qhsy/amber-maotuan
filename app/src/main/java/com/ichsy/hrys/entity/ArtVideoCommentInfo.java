package com.ichsy.hrys.entity;

/**
 * Created by zhu on 2017/8/26.
 */

public class ArtVideoCommentInfo {
    private String commentContent; //评论内容 ,
    private long commentTime; //评论时间 ,
    private ArtVideoUserInfo senderInfo; //发送评论的用户信息

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public long getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(long commentTime) {
        this.commentTime = commentTime;
    }

    public ArtVideoUserInfo getSenderInfo() {
        return senderInfo;
    }

    public void setSenderInfo(ArtVideoUserInfo senderInfo) {
        this.senderInfo = senderInfo;
    }
}
