package com.ichsy.hrys.model.splash;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.ichsy.hrys.R;

import java.util.ArrayList;
import java.util.List;

import zz.mk.utilslibrary.LogUtil;
import zz.mk.utilslibrary.ScreenUtil;

/**
 *
   * 类名：WelcomeViewPagerContoler
   * @author 赵然
   * 实现的主要功能:
   * 创建日期：2016年06月07日13:10:21
   * 修改者，修改日期，修改内容。
 */
public class WelcomeController {
	private Context mContext;
	private ViewPager mViewPager;
	private LinearLayout mIndicatorGroup;
	private List<View> mViews;
	private ViewPagerAdapter mPagerAdapter;
	private View[] indicators;
	private RelativeLayout viewpager_bg;


	/** 指示器中圆形图片 整个容器的宽 **/
	public int MPARAMS_WIDTH_OVAL = 20;
	/** 指示器中圆形图片 整个容器的高 **/
	public int MPARAMS_HEIGHT_OVAL = 20;
	/** 指示器中圆形图片 距离左边的mgins **/
	public int MMARGINS_LEFT_OVAL = 0;
	/** 指示器中圆形图片 距离上边的mgins **/
	public int MMARGINS_TOP_OVAL = 0;
	/** 指示器中圆形图片 距离右边的mgins **/
	public int MMARGINS_RIGHT_OVAL = 20;
	/** 指示器中圆形图片 距离下边的mgins **/
	public int MMARGINS_BOTTOM_OVAL = 0;


	/** 指示器中矩形图片 整个容器的宽 **/
	public int MPARAMS_WIDTH_RECTANGLE = 40;
	/** 指示器中矩形图片 整个容器的高 **/
	public int MPARAMS_HEIGHT_RECTANGLE = 5;
	/** 指示器中矩形图片 距离左边的mgins **/
	public int MMARGINS_LEFT_RECTANGLE = 0;
	/** 指示器中矩形图片 距离上边的mgins **/
	public int MMARGINS_TOP_RECTANGLE = 0;
	/** 指示器中矩形图片 距离右边的mgins **/
	public int MMARGINS_RIGHT_RECTANGLE = 15;
	/** 指示器中矩形图片 距离下边的mgins **/
	public int MMARGINS_BOTTOM_RECTANGLE = 0;

	private int mWidth = MPARAMS_WIDTH_OVAL;
	private int mHeight = MPARAMS_HEIGHT_OVAL;
	private int mLeft = MMARGINS_LEFT_OVAL;
	private int mTop = MMARGINS_TOP_OVAL;
	private int mRight = MMARGINS_RIGHT_OVAL;
	private int mBottom = MMARGINS_BOTTOM_OVAL;
	/**
	 * 是否左滑进入主界面
	 */
	public boolean isLeftMove = false;

	/**
	 * 上一个位置
	 */

	public WelcomeController(Context context) {
		super();
		this.mContext = context;
		mWidth = ScreenUtil.dip2px(context, 20);
		mHeight = mWidth;
	}

	/**
	 * 初始化
	 * @param imgIds
	 * @param view
	 */
	public void init(int[] imgIds, View view) {
		initLayoutParams(MPARAMS_WIDTH_RECTANGLE, MPARAMS_HEIGHT_RECTANGLE,
				MMARGINS_LEFT_RECTANGLE, MMARGINS_TOP_RECTANGLE,
				MMARGINS_RIGHT_RECTANGLE, MMARGINS_BOTTOM_RECTANGLE);

		mViews = new ArrayList<>();
		for (int i = 0; i < imgIds.length; i++) {
			ImageView iv = new ImageView(mContext);
			iv.setImageResource(imgIds[i]);
			iv.setScaleType(ScaleType.FIT_XY);
			mViews.add(iv);
		}
		mViews.add(view);
		initViewPager();
		initIndicators(0);
	}

	public void init(List<View> views) {
		mViews = views;
		initViewPager();
		initIndicators(0);
	}

	/**
	 * 初始化ViewPager
	 *
	 * @param views
	 *            : 指示器中被选中的点的背景色或图片
	 * @param shapeSelector
	 *            : 指示器中未被选中的点的背景色或图片
	 * **/
	public void init(List<View> views, int shapeSelector) {
		mViews = views;
		initViewPager();
		initIndicators(shapeSelector);
	}

	/**
	 * 初始化ViewPager
	 *
	 * @param views
	 *            指示器中被选中的点的背景色或图片
	 * @param views
	 *            指示器中未被选中的点的背景色或图片
	 * @param viewpager_bg
	 *            viewPager的背景设置
	 * **/
	public void init(List<View> views, int shapeSelector, int viewpager_bg) {
		mViews = views;
		initViewPager();
		initIndicators(shapeSelector);
		this.viewpager_bg = (RelativeLayout) ((Activity) mContext).findViewById(R.id.viewpager_bg);
		this.viewpager_bg.setBackgroundResource(viewpager_bg);
	}

	/** viewPager的背景设置 **/
	public void setViewpager_bg(int viewpager_bg) {
		this.viewpager_bg = (RelativeLayout) ((Activity) mContext).findViewById(R.id.viewpager_bg);
		this.viewpager_bg.setBackgroundResource(viewpager_bg);
	}


	LinearLayout.LayoutParams selectParams = new LinearLayout.LayoutParams(123, 7);
	LinearLayout.LayoutParams unselectParams = new LinearLayout.LayoutParams(54, 7);
	/**
	 * 设置指示器
	 * @param shapeSelector  背景选择器
	 */
	private void initIndicators(int shapeSelector) {
		if (shapeSelector ==0 ) shapeSelector = R.drawable.selecter_welcome_circle;
		mIndicatorGroup = (LinearLayout) ((Activity) mContext).findViewById(R.id.ll_welcome_btnlay);
		indicators = new View[mViews.size()];
		mIndicatorGroup.removeAllViews();
//		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mWidth, mHeight);

		selectParams.setMargins(mLeft, mTop, mRight, mBottom);
		unselectParams.setMargins(mLeft, mTop, mRight, mBottom);
		for (int i = 0; i < indicators.length; i++) {
			indicators[i] = new View(mContext);
			indicators[i].setBackgroundResource(shapeSelector);
			if (i == 0) {
				indicators[i].setSelected(true);
				indicators[i].setLayoutParams(selectParams);
			} else {
				indicators[i].setSelected(false);
				indicators[i].setLayoutParams(unselectParams);
			}
//			indicators[i].setLayoutParams(params);
			mIndicatorGroup.addView(indicators[i]);
		}
	}
	/**
	 *
	 * 设置指示器的布局参数
	 *
	 **/
	public WelcomeController initLayoutParams(int width, int height, int left, int top,
											  int right, int bottom) {
		this.mWidth = width;
		this.mHeight = height;
		this.mLeft = left;
		this.mTop = top;
		this.mRight = right;
		this.mBottom = bottom;
		return this;
	}

	/**
	 *
	 * 设置ViewPager
	 *
	 **/
	private void initViewPager() {
		mViewPager = (ViewPager) ((Activity) mContext).findViewById(R.id.vp_welcome_view);
		mPagerAdapter = new ViewPagerAdapter(mViews);
		mViewPager.setAdapter(mPagerAdapter);
		mViewPager.setOnPageChangeListener(pageChangeListener);
		mViewPager.setOnTouchListener(new View.OnTouchListener() {
			float oldX = 0;
			float newX = 0;
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
					case MotionEvent.ACTION_MOVE:
						newX =  event.getRawX();
						if (newX - oldX <= -2) {
							isLeftMove = true;
						}
						break;
					case MotionEvent.ACTION_DOWN:
						oldX =  event.getRawX();
						isLeftMove = false;
						break;
					case MotionEvent.ACTION_UP:
						oldX = 0;
						isLeftMove = false;
						break;
					default:
						break;
				}
				return false;
			}
		});
	}

	/**
	 * 监听 viewPager 的滚动等事件
	 */
	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int position) {
			for (int i = 0; i < indicators.length; i++) {
				if (i == position) {
					indicators[i].setSelected(true);
					indicators[i].setLayoutParams(selectParams);
				} else {
					indicators[i].setSelected(false);
					indicators[i].setLayoutParams(unselectParams);
				}
			}
			if (pagerScrolledChangeListener != null)
				pagerScrolledChangeListener.onPageSelected(position);
		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
								   int positionOffsetPixels) {
			LogUtil.zLog().d("position:"+position+" positionOffset:"+positionOffset+" positionOffsetPixels:"+positionOffsetPixels);
			if (pagerScrolledChangeListener != null) {
				pagerScrolledChangeListener.onPageScrolled(position,positionOffset, positionOffsetPixels);
			}
		}

		@Override
		public void onPageScrollStateChanged(int state) {
		}
	};

	/**
	 * 回调
	 */
	private OnViewPagerScrolledChangeListener pagerScrolledChangeListener;

	/**
	 * 设置回调
	 * </br>ps:想要实现该回调的类必须实现OnViewPagerScrolledChangeListener接口并且设置调用
	 * </br>setOnViewPagerScrolledChangeListener方法设置
	 * @param pagerScrolledChangeListener
	 */
	public void setOnViewPagerScrolledChangeListener(
			OnViewPagerScrolledChangeListener pagerScrolledChangeListener) {
		this.pagerScrolledChangeListener = pagerScrolledChangeListener;
	}

	/**
	 * 滚动的回调
	 * 类名：OnViewPagerScrolledChangeListener
	 * 实现的主要功能:主要实现对 viewpager 滚动监听的回调
	 * 创建日期：2014-10-27
	 * 修改者，修改日期，修改内容。
	 */
	public interface OnViewPagerScrolledChangeListener {
		void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
		void onPageSelected(int position);
	}
}
