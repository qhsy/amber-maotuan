package com.ichsy.hrys.common.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.ichsy.hrys.R;
import com.ichsy.hrys.common.utils.UMAnalyticsUtils;
import com.ichsy.hrys.common.utils.imageloadutils.ImageLoaderUtils;
import com.ichsy.hrys.common.utils.imageloadutils.ImageStyleType;
import com.ichsy.hrys.entity.ArtVideoUserInfo;
import com.ichsy.hrys.model.main.controller.TaskController;

/**
 * 用户头像使用（内部维护了头像的点击事件统一跳转到PersonActivity页面,出入一个UserInfo实体data数据）
 * author: ihesen on 2016/5/12 11:10
 * email: hesen@ichsy.com
 */
public class AvatorView extends AppCompatImageView implements View.OnClickListener {

    private ArtVideoUserInfo mUserInfo = null;
    /**
     * 点击头像标识头像点击事件埋点使用
     */
    private String eventID;

    public AvatorView(Context context) {
        super(context);

    }

    public AvatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AvatorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 为了统一跳转用户个人资料页面方便，并且设置用户头像方面后期统一替换默认头像图
     * 默认圆形
     */
    public void setUserInfo(ArtVideoUserInfo userInfo, boolean isCanClick) {
        this.mUserInfo = userInfo;
        if (isCanClick) {
            setOnClickListener(this);
        }
        ImageLoaderUtils.loadViewImage(getContext(), this,userInfo.getUserIconThumburl(),R.drawable.head_placeholder, R.drawable.icon_wode, ImageStyleType.CropCircle);
    }

    /**
     * 设置用户默认头像
     * 无样式，矩形
     */
    public void setUserInfo(ArtVideoUserInfo userInfo, int avatorDefault) {
        this.mUserInfo = userInfo;
    }

    @Override
    public void onClick(View v) {
        if (mUserInfo != null) {
            if(!TextUtils.isEmpty(eventID)){
                UMAnalyticsUtils.onEvent(getContext(), eventID);
            }

            TaskController.openPersionInfo((Activity) v.getContext(), mUserInfo.getUserCode());
        }
    }

    /**
     * 设置头像点击事件id
     */
    public void setEventID(String eventID){
        this.eventID = eventID;
    }
}
