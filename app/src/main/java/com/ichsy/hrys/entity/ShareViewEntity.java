package com.ichsy.hrys.entity;

import com.ichsy.hrys.entity.share.ShareEntity;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * 功能： 分享VIew对应的适配器实体
 * ＊创建者：赵然 on 2017/1/6 11:22
 * ＊
 */

public class ShareViewEntity {
    public ShareViewEntity(String plateName , int plateIcon , SHARE_MEDIA plate, boolean  isDealSelf){
        this.plate = plate;
        this.plateIcon = plateIcon;
        this.plateName = plateName;
        this.isDealSelf = isDealSelf;
    }
    public String plateName;
    public int  plateIcon;
    public SHARE_MEDIA plate;
    /**
     * 是否需要自己处理平台分享
     */
    public  boolean isDealSelf;

    private ShareEntity shareEntity;

    public ShareEntity getShareEntity() {
        return shareEntity;
    }

    public void setShareEntity(ShareEntity shareEntity) {
        this.shareEntity = shareEntity;
    }
}
