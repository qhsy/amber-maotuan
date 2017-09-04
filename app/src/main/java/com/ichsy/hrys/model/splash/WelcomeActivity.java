package com.ichsy.hrys.model.splash;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.ichsy.hrys.MainActivity;
import com.ichsy.hrys.R;
import com.ichsy.hrys.model.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import zz.mk.utilslibrary.ScreenUtil;


/**
 * 引导页
 */
public class WelcomeActivity extends BaseActivity implements WelcomeController.OnViewPagerScrolledChangeListener {


    private WelcomeController controller;
    private List<View> views;
    private int[] viewDrawableId = new int[] { R.drawable.guide_page1,
            R.drawable.guide_page2,R.drawable.guide_page3,R.drawable.guide_page4
    };

    @Override
    public void loadLayout() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    public void logic() {
        hiddenTitlebar();

        controller = new WelcomeController(WelcomeActivity.this);
        getViews(viewDrawableId);

        controller.initLayoutParams(
                ScreenUtil.dip2px(WelcomeActivity.this,7)
                ,ScreenUtil.dip2px(WelcomeActivity.this,7)
                ,0
                ,0
                ,ScreenUtil.dip2px(WelcomeActivity.this,12)
                ,0).init(views, R.drawable.selecter_welcome_circle);
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

    /**
     * 获取viewpager中需要填充的view
     *
     * @param viewDrawableId
     *            每个view对应的背景图
     */
    private void getViews(int[] viewDrawableId) {
        if (views == null) {

            views = new ArrayList<>();
        }
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < viewDrawableId.length; i++) {
            View viewItem = inflater.inflate(R.layout.activity_welcome_item,
                    null);
            viewItem.setBackgroundResource(viewDrawableId[i]);
            if (i == viewDrawableId.length-1) {
                TextView mInappBtn = (TextView) viewItem.findViewById(R.id.iv_welcome_inapp);
                mInappBtn.setVisibility(View.VISIBLE);
                mInappBtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(),MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
            views.add(viewItem);
        }
        controller.setOnViewPagerScrolledChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {
//        if (views != null && position == (views.size() - 1) && positionOffset == 0 && ontroller.isLeftMove) {
//            SplashCntroller.loadMainUi(getContext());
//        }
    }
    @Override
    public void onPageSelected(int position) {
    }
}
