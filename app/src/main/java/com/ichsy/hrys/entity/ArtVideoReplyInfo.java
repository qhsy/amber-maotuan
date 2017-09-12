package com.ichsy.hrys.entity;

/**
 * author: zhu on 2017/9/8 12:17
 * email: mackkilled@gmail.com
 */

public class ArtVideoReplyInfo {
    private String commentId;// 关联评论Id ,
    private String replyContent;//  回复的内容 ,
    private String replyId;//  回复Id ,
    private ArtVideoUserInfo replyReceiverUserInfo;//  回复接受者信息 ,
    private ArtVideoUserInfo replySenderUserInfo;//  回复发送者信息 ,
    private long replyTime;//  回复的时间

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
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

    public long getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(long replyTime) {
        this.replyTime = replyTime;
    }
}
