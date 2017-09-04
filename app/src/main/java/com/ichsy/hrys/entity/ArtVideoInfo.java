package com.ichsy.hrys.entity;

import java.io.Serializable;

/**
 * author: zhu on 2017/8/25 15:14
 * email: mackkilled@gmail.com
 */

public class ArtVideoInfo implements Serializable {

    private String videoCaption;// 视频标题 ,
    private int videoCollectionAmount;
    private int videoCommentAmount;
    private int videoShareAmount;
    private String videoCover; //视频封面 ,
    private String videoDescription;  // 视频描述 ,
    private String videoLong;//视频时长 ,
    private String videoNumber;//视频编号 ,
    private int videoPlayCount;//视频播放量 ,
    private String videoUrl; // 视频url ,
    private ArtVideoUserInfo videoUserInfo; //视频发布人

    public String getVideoCaption() {
        return videoCaption == null ? "" : videoCaption;
    }

    public void setVideoCaption(String videoCaption) {
        this.videoCaption = videoCaption;
    }

    public String getVideoCover() {
        return videoCover == null ? "" : videoCover;
    }

    public void setVideoCover(String videoCover) {
        this.videoCover = videoCover;
    }

    public String getVideoDescription() {
        return videoDescription == null ? "" : videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public String getVideoNumber() {
        return videoNumber == null ? "" : videoNumber;
    }

    public void setVideoNumber(String videoNumber) {
        this.videoNumber = videoNumber;
    }

    public int getVideoPlayCount() {
        return videoPlayCount;
    }

    public void setVideoPlayCount(int videoPlayCount) {
        this.videoPlayCount = videoPlayCount;
    }

    public String getVideoLong() {
        return videoLong;
    }

    public void setVideoLong(String videoLong) {
        this.videoLong = videoLong;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getVideoCollectionAmount() {
        return videoCollectionAmount;
    }

    public void setVideoCollectionAmount(int videoCollectionAmount) {
        this.videoCollectionAmount = videoCollectionAmount;
    }

    public int getVideoCommentAmount() {
        return videoCommentAmount;
    }

    public void setVideoCommentAmount(int videoCommentAmount) {
        this.videoCommentAmount = videoCommentAmount;
    }

    public int getVideoShareAmount() {
        return videoShareAmount;
    }

    public void setVideoShareAmount(int videoShareAmount) {
        this.videoShareAmount = videoShareAmount;
    }

    public ArtVideoUserInfo getVideoUserInfo() {
        return videoUserInfo;
    }

    public void setVideoUserInfo(ArtVideoUserInfo videoUserInfo) {
        this.videoUserInfo = videoUserInfo;
    }
}
