package com.ichsy.hrys.model.main.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ichsy.hrys.R;
import com.ichsy.hrys.common.utils.imageloadutils.ImageLoaderUtils;
import com.ichsy.hrys.common.view.convenientbanner.holder.Holder;
import com.ichsy.hrys.entity.ArtVideoPromotionDetail;

/**
 * author: zhu on 2017/4/19 11:05
 * email: mackkilled@gmail.com
 */

public class BannerImageHolderView implements Holder<ArtVideoPromotionDetail> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        //找到布局填充器
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //找到整个xml布局
        RelativeLayout main = (RelativeLayout) inflater.inflate(R.layout.home_headview, null);
        //通过找到xml布局来找控件
        imageView = (ImageView) main.findViewById(R.id.iv_banner);
        return main;
    }

    @Override
    public void UpdateUI(Context context, int position, ArtVideoPromotionDetail data) {
        ImageLoaderUtils.loadViewImage(context, imageView, data.getCoverPic(), R.drawable.list_placeholder);
    }
}
