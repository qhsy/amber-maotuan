package com.ichsy.hrys.entity;

/**
 * author: zhu on 2017/8/25 15:55
 * email: mackkilled@gmail.com
 */

public class ArtVideoNotifactionMessage {
    private ArtVideoCommentMessage commentMessage;// 关联原评论内容 ,
    private String replyContent;//回复的内容 ,
    private String replyId;// 回复Id ,
    private ArtVideoUserInfo replyReceiverUserInfo;// 回复接受者信息 ,
    private ArtVideoUserInfo replySenderUserInfo;// 回复发送者信息 ,
    private String replyTime;// 回复的时间

    public ArtVideoCommentMessage getCommentMessage() {
        return commentMessage;
    }

    public void setCommentMessage(ArtVideoCommentMessage commentMessage) {
        this.commentMessage = commentMessage;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    public ArtVideoUserInfo getReplyReceiverUserInfo() {
        return replyReceiverUserInfo;
    }

    public void setReplyReceiverUserInfo(ArtVideoUserInfo replyReceiverUserInfo) {
        this.replyReceiverUserInfo = replyReceiverUserInfo;
    }

    public ArtVideoUserInfo getReplySenderUserInfo() {
        return replySenderUserInfo;
    }

    public void setReplySenderUserInfo(ArtVideoUserInfo replySenderUserInfo) {
        this.replySenderUserInfo = replySenderUserInfo;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }
}
