package com.ichsy.hrys.entity;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分享实体
 * author: ihesen on 2016/5/10 14:06
 * email: hesen@ichsy.com
 */
public class ArtShareModel implements Serializable {

    /**
     * 分享跳转的链接
     */
    public String shareUrl;
    /**
     * 分享的图片链接
     */
    public String sharePicUrl;
    /**
     * 分享的标题
     */
    public String shareTittle;
    /**
     * 分享的内容
     */
    private String shareContent;
    /**
     * 付款码链接
     */
    public String payUrl;

    /**
     * 图片素材
     */
    public List<String> picMaterialList = new ArrayList<>();

    /**
     * 推荐文案
     */
    public String materialContent;

    public String getShareContent() {
        if(TextUtils.isEmpty(shareContent)){
            shareContent = "AMBer是一个聚集明星与达人生活方式的电商平台，来AMBer，创意你的生活！";
        }
        return shareContent;
    }

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
    }
}
