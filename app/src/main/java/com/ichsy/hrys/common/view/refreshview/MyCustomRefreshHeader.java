package com.ichsy.hrys.common.view.refreshview;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ichsy.hrys.R;
import com.ichsy.hrys.common.view.refreshview.indicator.PtrIndicator;

import zz.mk.utilslibrary.LogUtil;
import zz.mk.utilslibrary.ScreenUtil;


/**
 * 功能：APP下拉刷新 -- 头
 * ＊创建者：赵然 on 16/7/8 14:58
 * ＊
 */
public class MyCustomRefreshHeader extends RelativeLayout implements PtrUIHandler {

    private final  int  DEFAULT_WIDTH_DIP = 60;
    private final int DEFAULT_MARGIN = 5;
    private ImageView imageView;
    private AnimationDrawable animationDrawable;
    public MyCustomRefreshHeader(Context context) {
        super(context);
        init();
    }

    public MyCustomRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public MyCustomRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化操作
     */
    private void init(){
        imageView = new ImageView(getContext());
        RelativeLayout.LayoutParams params =  new LayoutParams(
                ScreenUtil.dip2px(getContext(),DEFAULT_WIDTH_DIP)
        ,ScreenUtil.dip2px(getContext(),DEFAULT_WIDTH_DIP));
        params.addRule(CENTER_IN_PARENT);
        params.topMargin =ScreenUtil.dip2px(getContext(),DEFAULT_MARGIN);
        params.bottomMargin = params.topMargin;
        imageView.setImageResource(R.drawable.loading_1);
        addView(imageView,params);
    }
    /**
     * 重置 View，隐藏忙碌进度条，隐藏箭头 View，更新最后刷新时间
     */
    @Override
    public void onUIReset(PtrFrameLayout frame) {
        LogUtil.zLog().e("onUIReset");
//        ImageLoaderUtils.loadViewImage(getContext(),imageView,R.drawable.refreshloadingface);
        if (animationDrawable != null){

            animationDrawable.stop();
            animationDrawable = null;
        }
        imageView.setImageResource(R.drawable.loading_1);
    }
    /**
     * 准备刷新，隐藏忙碌进度条，显示箭头 View，显示文字，如果是下拉刷新，显示“下拉刷新”，如果是释放刷新，显示“下拉”。
     */
    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        imageView.setImageResource(R.drawable.loading_1);
        LogUtil.zLog().e("onUIRefreshPrepare");

    }
    /**
     * 开始刷新，隐藏箭头 View，显示忙碌进度条，显示文字，显示“加载中...”，更新最后刷新时间
     */
    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        imageView.setImageResource(R.drawable.anim_pullrefresh);
        animationDrawable = (AnimationDrawable)imageView.getDrawable();
        animationDrawable.start();
        LogUtil.zLog().e("onUIRefreshBegin");

    }
    /**
     * 刷新结束，隐藏箭头 View，隐藏忙碌进度条，显示文字，显示“更新完成”，写入最后刷新时间
     */
    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        LogUtil.zLog().e("onUIRefreshComplete");
    }
    /**
     * 下拉过程中位置的变化
     * 拖动情况下：下拉距离从 小于刷新高度到大于刷新高度时，箭头由下变成上，同时改变文字显示
     *             下拉距离从 大于刷新高度到小于刷新高度时，箭头由上变成下，同时改变文字显示
     */
    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
    }
}
