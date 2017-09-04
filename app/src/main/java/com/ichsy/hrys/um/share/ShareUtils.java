package com.ichsy.hrys.um.share;

import android.app.Activity;
import android.text.TextUtils;

import com.ichsy.hrys.entity.share.ShareEntity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

/**
 * 功能：分享相关的工具类
 * ＊创建者：赵然 on 2017/1/6 14:15
 * ＊
 */

public class ShareUtils {
    public static void  share(Activity activity, SHARE_MEDIA plate, ShareEntity shareEntity, UMShareListener listener){
        ShareAction shareAction = new ShareAction(activity).setPlatform(plate);
        UMWeb web = new UMWeb(shareEntity.getShareTargetUrl());
        web.setTitle(shareEntity.getShareTittle());

        shareAction.withText(shareEntity.getShareContent());
        if (!TextUtils.isEmpty(shareEntity.getImageUrl())){

            shareAction.withMedia(new UMImage(activity,shareEntity.getImageUrl()));
            shareAction.withMedia(web);
        }
        if ( listener != null){

            shareAction.setCallback(listener);
        }

        shareAction.share();
    }
}
