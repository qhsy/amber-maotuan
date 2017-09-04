package com.ichsy.hrys.model.setting;

import android.widget.ImageView;
import android.widget.TextView;

import com.ichsy.hrys.R;
import com.ichsy.hrys.model.base.BaseActivity;

import butterknife.BindView;
import zz.mk.utilslibrary.system.AppUtils;


/**
 * 关于页面
 */
public class AboutActivity extends BaseActivity {

    @BindView(R.id.tv_aboutactivity_version)
    TextView versionTV;
    @BindView(R.id.iv_aboutactivity_logo)
    ImageView logo;
    @Override
    public void loadLayout() {
        setContentView(R.layout.activity_about);
    }

    @Override
    public void logic() {
        showDefaultTittle("关于我们");
        setBackgroundColor(getResources().getColor(R.color.color_blue));
        getCenterTittleView().setTextColor(getResources().getColor(R.color.color_white));
        setLeftDrawable(R.drawable.icon_back_white);
        String version ="V" + AppUtils.getVerName(getContext());
        versionTV.setText(version+" For Android");
//        ImageLoaderUtils.loadViewImage(getContext(),logo,R.mipmap.ic_launcher, ImageStyleType.RoundedCorners);
    }

    @Override
    public void loadListener() {

    }

    @Override
    public void request() {

    }


    @Override
    public void onViewClick(int viewId) {

    }
}
