package com.ichsy.hrys.entity;

/**
 * author: zhu on 2017/8/25 15:19
 * email: mackkilled@gmail.com
 */

public class ArtVideoPromotionDetail {
    private String columnCode ;//栏目编号 ,
    private String coverPic ;//封面图片 ,
    private String gotoType; //跳转类型 ,
    private String gotoUrl;//跳转页面 ,
    private int position;//排序 ,
    private String title ;//标题 ,
    private String videoId;// 视频编号

    public String getColumnCode() {
        return columnCode;
    }

    public void setColumnCode(String columnCode) {
        this.columnCode = columnCode;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public String getGotoType() {
        return gotoType;
    }

    public void setGotoType(String gotoType) {
        this.gotoType = gotoType;
    }

    public String getGotoUrl() {
        return gotoUrl;
    }

    public void setGotoUrl(String gotoUrl) {
        this.gotoUrl = gotoUrl;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
