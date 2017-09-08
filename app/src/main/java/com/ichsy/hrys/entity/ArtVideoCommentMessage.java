package com.ichsy.hrys.entity;

/**
 * author: zhu on 2017/8/25 15:46
 * email: mackkilled@gmail.com
 */

public class ArtVideoCommentMessage {
   private String commentContent;// 评论内容 ,
   private String commentId;// 评论Id ,
   private String commentTime;//评论发布的时间 ,
   private ArtVideoUserInfo commentUserInfo;//评论人信息 ,
   private String videoId; //评论关联的视频Id

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

   public String getCommentTime() {
      return commentTime;
   }

   public void setCommentTime(String commentTime) {
      this.commentTime = commentTime;
   }

   public ArtVideoUserInfo getCommentUserInfo() {
      return commentUserInfo;
   }

   public void setCommentUserInfo(ArtVideoUserInfo commentUserInfo) {
      this.commentUserInfo = commentUserInfo;
   }

   public String getVideoId() {
      return videoId;
   }

   public void setVideoId(String videoId) {
      this.videoId = videoId;
   }
}
