package com.ichsy.hrys.entity;

import java.util.List;

/**
 * Created by zhu on 2017/8/26.
 */

public class ArtVideoCommentInfo {
    public ArtPageResults pageResults = new ArtPageResults(); //分页
    private String commentContent; //评论内容 ,
    private long commentTime; //评论时间 ,
    private String commentId; //评论的Id ,
    private ArtVideoUserInfo senderInfo; //发送评论的用户信息
    private int thumbsUp; //点赞数
    private List<ArtVideoReplyInfo> commentReplyList; //回复列表

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

    public ArtPageResults getPageResults() {
        return pageResults;
    }

    public void setPageResults(ArtPageResults pageResults) {
        this.pageResults = pageResults;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public int getThumbsUp() {
        return thumbsUp;
    }

    public void setThumbsUp(int thumbsUp) {
        this.thumbsUp = thumbsUp;
    }

    public List<ArtVideoReplyInfo> getCommentReplyList() {
        return commentReplyList;
    }

    public void setCommentReplyList(List<ArtVideoReplyInfo> commentReplyList) {
        this.commentReplyList = commentReplyList;
    }
}
