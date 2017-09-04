package com.ichsy.hrys.entity;

/**
 * author: zhu on 2017/8/25 15:46
 * email: mackkilled@gmail.com
 */

public class ArtVideoCommentReply {
   private String commentId; //关联评论Id ,
   private String replyContent;//回复的内容 ,
   private String replyId;// 回复Id ,
   private String replyReceiverImgUrl;//回复接收者的头像地址 ,
   private String replyReceiverTittle;//回复接收者的的昵称 ,
   private String replySenderImgUrl;//回复发布者的头像地址 ,
   private String replySenderTittle;//回复发布者的的昵称 ,
   private String replyTime;//回复的时间 ,
   private String userCodeReceiver;//回复接收者编号 ,
   private String userCodeSender;//回复发送者编号

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

   public String getReplyReceiverImgUrl() {
      return replyReceiverImgUrl;
   }

   public void setReplyReceiverImgUrl(String replyReceiverImgUrl) {
      this.replyReceiverImgUrl = replyReceiverImgUrl;
   }

   public String getReplyReceiverTittle() {
      return replyReceiverTittle;
   }

   public void setReplyReceiverTittle(String replyReceiverTittle) {
      this.replyReceiverTittle = replyReceiverTittle;
   }

   public String getReplySenderImgUrl() {
      return replySenderImgUrl;
   }

   public void setReplySenderImgUrl(String replySenderImgUrl) {
      this.replySenderImgUrl = replySenderImgUrl;
   }

   public String getReplySenderTittle() {
      return replySenderTittle;
   }

   public void setReplySenderTittle(String replySenderTittle) {
      this.replySenderTittle = replySenderTittle;
   }

   public String getReplyTime() {
      return replyTime;
   }

   public void setReplyTime(String replyTime) {
      this.replyTime = replyTime;
   }

   public String getUserCodeReceiver() {
      return userCodeReceiver;
   }

   public void setUserCodeReceiver(String userCodeReceiver) {
      this.userCodeReceiver = userCodeReceiver;
   }

   public String getUserCodeSender() {
      return userCodeSender;
   }

   public void setUserCodeSender(String userCodeSender) {
      this.userCodeSender = userCodeSender;
   }
}
