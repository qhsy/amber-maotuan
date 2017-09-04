/**
 *
 */
package com.ichsy.hrys.um.share;

import android.app.Activity;
import android.text.TextUtils;

import com.ichsy.hrys.config.constants.UMShareConstant;
import com.ichsy.hrys.entity.ArtShareModel;
import com.ichsy.hrys.entity.share.BaseSharePlatform;
import com.ichsy.hrys.entity.share.CopyPlateForm;
import com.ichsy.hrys.entity.share.ShareEntity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import java.util.HashMap;
import java.util.Map;

/**
 * 友盟分享相关 控制器
 * <p>
 * Package: com.ichsy.syxd.share
 * <p>
 * File: UMShareUtils.java
 *
 * @author: 赵然 Date: 2015-3-17
 * <p>
 * Modifier： Modified Date： Modify：
 * <p>
 * Copyright @ 2015 ICHSY
 */
public class UMShareController {

    /**
     * 上下文
     */
    private Activity mActivity;
    private ShareAction shareAction;

    private Map<String, BaseSharePlatform> addPlateForms;
    private ShareEntity shareEntity;

    public UMShareController(Activity activity) {
        mActivity = activity;
        SocializeConstants.APPKEY = UMShareConstant.UMKEY;
        init();
        setPlateform();
    }

    private void init() {

        shareAction = new ShareAction(mActivity);
    }

    /**
     * 设置分享平台，注：有些在配置文件中注册的平台必须调用 removePlatform 才能不显示在分享面板中
     * <p>
     * Modifier： Modified Date： Modify：
     */
    private void setPlateform() {
//        SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]{SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE};
        SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]{SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE};

        shareAction.setDisplayList(displaylist);
    }

    /**
     * ß
     */
    public void addPlateForm(BaseSharePlatform platform) {
        if (addPlateForms == null) {
            addPlateForms = new HashMap<>();
        }
        addPlateForms.put(platform.getPlateName(), platform);
        shareAction.addButton(platform.getShowName(), platform.getPlateName(), platform.getIconOnName(), platform.getIconOffName()).setShareboardclickCallback(new ShareBoardlistener() {
            @Override
            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                if (addPlateForms.containsKey(snsPlatform.mKeyword)) {
                    addPlateForms.get(snsPlatform.mKeyword).share();
                } else {
                    shareAction.setPlatform(share_media).setShareContent(getShareContent(share_media)).
                            share();
                }
                if(analyticeCallback != null){
                    analyticeCallback.addUMAnalyticsCallback(snsPlatform);
                }
            }
        });
    }

    UMAnalyticeCallback analyticeCallback;

    public void setUMAnalyticeCallback(UMAnalyticeCallback analyticeCallback){
        this.analyticeCallback = analyticeCallback;
    }

    /** 统计各分享平台点击埋点统计*/
    public interface UMAnalyticeCallback{
        void addUMAnalyticsCallback(SnsPlatform snsPlatform);

    }

    /**
     * 获取对应平台的分项数据
     *
     * @param share_media
     * @return
     */
    private ShareContent getShareContent(SHARE_MEDIA share_media) {
        ShareContent shareContent = new ShareContent();

        shareContent.mMedia = getUMWeb(shareEntity);

        if (share_media == SHARE_MEDIA.SINA) {
            String sinaDetail = "分享自@毛团儿学概论 ——你看起来很懂生活。想知道达人们的生活方式吗？快来毛团儿吧！";
            shareContent.mText = shareEntity.getShareContent() + sinaDetail + shareEntity.getShareTargetUrl();
        } else {
            shareContent.mText = shareEntity.getShareContent();
        }
        return shareContent;
    }

    /**
     * 打开分享面板
     * <p>
     * Modifier： Modified Date： Modify：
     */
    public void openShare() {
        addCopyLinkPlatform();
        shareAction.open();
    }

    /**
     * 打开分享面板
     *
     * @param mListener 回调监听
     */

    public void openShare(UMShareListener mListener) {
        addCopyLinkPlatform();
        shareAction.setCallback(mListener);
        shareAction.open();
    }

    /**添加复制链接平台*/
    private void addCopyLinkPlatform(){
        CopyPlateForm copyPlateForm = new CopyPlateForm();
        copyPlateForm.setShareContent(shareEntity.getShareTargetUrl());
        addPlateForm(copyPlateForm);
    }

    /**
     * 设置分享内容
     *
     * @param entity
     */
    public void setShareContent(ShareEntity entity) {

        this.shareEntity = entity;
        ShareContent commonContent = new ShareContent();
        UMWeb web = new UMWeb(entity.getShareTargetUrl());
        web.setTitle(entity.getShareTittle());
        web.setDescription(entity.getShareContent());
//        web.setThumb(getUmImage(entity));
        commonContent.mMedia = web;


        String sinaDetail = "分享自@毛团儿学概论 ——你看起来很懂生活。想知道达人们的生活方式吗？快来毛团儿吧！";
        ShareContent sinaContent = new ShareContent();
        UMWeb webSina = new UMWeb(entity.getShareTargetUrl());
        webSina.setTitle(entity.getShareTittle());
        webSina.setDescription(entity.getShareContent() + sinaDetail + entity.getShareTargetUrl());
//        webSina.setThumb(getUmImage(entity));
        sinaContent.mMedia = webSina;

        shareAction.setContentList(commonContent, commonContent, sinaContent, commonContent, commonContent);
    }

    /**
     * 获取分享图片
     *
     * @return
     */
    private UMWeb getUMWeb(ShareEntity entity) {
        UMImage image = null;
        String type = entity.getImageType();
        if ("url".equals(type)) {
            // 判断链接是否为空
            if (!TextUtils.isEmpty(entity.getImageUrl())) {
                image = new UMImage(mActivity, entity.getImageUrl());
            }
        } else if ("bitmap".equals(type)) {
            image = new UMImage(mActivity, entity.getImageBitmap());
        } else if ("id".equals(type)) {
            image = new UMImage(mActivity, entity.getImageID());
        }

        UMWeb web = new UMWeb(entity.getShareTargetUrl());
        web.setTitle(entity.getShareTittle());
        web.setDescription(entity.getShareContent());
        web.setThumb(image);

        return web;
    }

    /** 分享实体转换*/
    public ShareEntity getShareEntity(ArtShareModel shareModel){
        ShareEntity entity = new ShareEntity();
        if(shareModel != null){
            entity.setShareTittle(shareModel.shareTittle);
            entity.setShareContent(shareModel.getShareContent());
            entity.setShareTargetUrl(shareModel.shareUrl);
            entity.setImageUrl(shareModel.sharePicUrl);
        }
        return entity;
    }
}
