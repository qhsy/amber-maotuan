package com.ichsy.hrys.model.main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ichsy.hrys.R;
import com.ichsy.hrys.entity.ContentTaskModelInfo;
import com.ichsy.hrys.model.base.BaseFragment;
import com.ichsy.hrys.model.main.adapters.ContentPagerAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import zz.mk.utilslibrary.ScreenUtil;

/**
 * author: zhu on 2017/8/23 11:37
 * email: mackkilled@gmail.com
 */

public class MainFragment extends BaseFragment {
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.usercenter_iv)
    ImageView usercenterIv;
    @BindView(R.id.contentViewPager)
    ViewPager contentViewPager;

    public List<ContentTaskModelInfo> taskModelList = new ArrayList<>();

    private ContentPagerAdapter pagerAdapter;

    String[] title = {"推荐","最热","最新"};
    String[] type = {"1", "2", "3"};

    @Override
    public void loadLayout() {
        setContentView(R.layout.fragment_main);
    }

    @Override
    public void logic() {
        for (int i = 0, N = title.length; i < N; i++) {
            ContentTaskModelInfo tab = new ContentTaskModelInfo();
            tab.setTitle(title[i]);
            tab.setType(type[i]);
            taskModelList.add(tab);
        }
        setContentViewPager(taskModelList);
        setTabLayout();
    }

    @Override
    public void loadListener() {
        usercenterIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onImageViewClick != null) {
                    onImageViewClick.OnClick(v);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
//        ArtUserInfo mUserInfo = SharedPreferencesUtils.getUserInfo(getContext());
//        ImageLoaderUtils.loadCircleWhite(getContext(), usercenterIv, mUserInfo.getUserIconThumburl());
    }

    @Override
    public void request() {

    }

    @Override
    public void onViewClick(int viewId) {

    }


    @Override
    protected void lazyLoad() {

    }

    private void setTabLayout() {
        mTabLayout.setupWithViewPager(contentViewPager);
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    Field mTabStripField = mTabLayout.getClass().getDeclaredField("mTabStrip");
                    mTabStripField.setAccessible(true);

                    LinearLayout mTabStrip = (LinearLayout) mTabStripField.get(mTabLayout);

                    int dp20 = ScreenUtil.dip2px(getContext(), 20);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width ;
                        params.leftMargin = dp20;
                        params.rightMargin = dp20;
                        tabView.setLayoutParams(params);
                        tabView.invalidate();
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                final float defaultSize = getResources().getDimensionPixelSize(R.dimen.textsize_15);
                final float selected = getResources().getDimensionPixelSize(R.dimen.textsize_20);

                for (int i = 0; i < mTabLayout.getTabCount(); i++) {
                    TextView title = (TextView)(((LinearLayout) ((LinearLayout) mTabLayout.getChildAt(0)).getChildAt(i)).getChildAt(1));
                    title.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultSize);
                }
                TextView title = (TextView)(((LinearLayout) ((LinearLayout) mTabLayout.getChildAt(0)).getChildAt(0)).getChildAt(1));
                title.setTextSize(TypedValue.COMPLEX_UNIT_PX, selected);

                mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        TextView title = (TextView)(((LinearLayout) ((LinearLayout) mTabLayout.getChildAt(0)).getChildAt(tab.getPosition())).getChildAt(1));
                        title.setTextSize(TypedValue.COMPLEX_UNIT_PX, selected);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        TextView title = (TextView)(((LinearLayout) ((LinearLayout) mTabLayout.getChildAt(0)).getChildAt(tab.getPosition())).getChildAt(1));
                        title.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultSize);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
            }
        });

    }

    private void setContentViewPager(List<ContentTaskModelInfo> taskModelList) {
        pagerAdapter = new ContentPagerAdapter(getChildFragmentManager(), taskModelList);
        pagerAdapter.setNoRequestPosition(0);
        contentViewPager.setAdapter(pagerAdapter);
    }

    onImageViewClick onImageViewClick;

    public interface onImageViewClick {
        void OnClick(View view);
    }

    public void setOnImageViewClick(onImageViewClick onImageViewClick) {
        this.onImageViewClick = onImageViewClick;
    }
}
