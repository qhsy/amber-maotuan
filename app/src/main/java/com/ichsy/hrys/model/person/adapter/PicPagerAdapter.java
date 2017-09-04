package com.ichsy.hrys.model.person.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ichsy.hrys.R;
import com.ichsy.hrys.common.utils.CommonDialogUtil;
import com.ichsy.hrys.common.utils.imageloadutils.ImageLoaderUtils;
import com.ichsy.hrys.entity.ArtPic;

import java.util.List;

import zz.mk.utilslibrary.ScreenUtil;

/**
 * author: ihesen on 2016/12/6 11:21
 * email: hesen@ichsy.com
 */

public class PicPagerAdapter extends PagerAdapter {

    private List<ArtPic> mPicList;
    private Activity mActivity;

    /** 暂时只用作点击图片单机操作*/
    private View.OnClickListener itemClickListener;

    public PicPagerAdapter(Activity activity, List<ArtPic> pics) {
        this.mPicList = pics;
        this.mActivity = activity;
    }

    @Override
    public int getCount() {
        return mPicList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final View rootView = View.inflate(mActivity, R.layout.item_material_big_pic, null);
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.iv_material_big_pic);

        int windowWidth = ScreenUtil.getScreenWidth(mActivity);
        int windowHeight = ScreenUtil.getScreenHeight(mActivity);
        ImageLoaderUtils.loadViewImageWithWH(mActivity, imageView, mPicList.get(position).picUrl, windowWidth, windowHeight);

        container.addView(rootView);
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CommonDialogUtil dialogUtil = new CommonDialogUtil();
                dialogUtil.showSaveImgDialog(imageView);
                return false;
            }
        });
        if(itemClickListener != null){
            rootView.setOnClickListener(itemClickListener);
            imageView.setOnClickListener(itemClickListener);
        }
        return rootView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setOnClickListener(View.OnClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}