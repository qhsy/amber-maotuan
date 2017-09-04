package com.ichsy.hrys.common.view.refreshview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import zz.mk.utilslibrary.LogUtil;

/**
 * 功能： 下拉刷新View的二次封装
 * ＊创建者：赵然 on 16/7/11 10:18
 * ＊
 */
public class RefreshLay extends PtrClassicFrameLayout implements PtrHandler{
    // 是否存在左右滑动事件
    private boolean mDragger;
    // 记录手指按下的位置
    private float mStartY, mStartX;
    // 出发事件的最短距离
    private int mTouchSlop;
    private OnRefreshListener listener;
    MyCustomRefreshHeader  refreshHeader;

    public RefreshLay(Context context) {
        super(context);
        init(context);
    }

    public RefreshLay(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RefreshLay(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    /**
     * 初始化
     */
    private void init(Context context){
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        refreshHeader = new MyCustomRefreshHeader(getContext());
        this.setHeaderView(refreshHeader);
        this.addPtrUIHandler(refreshHeader);
        this.setPtrHandler(this);
    }

    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        if (listener != null){
            listener.onRefresh();
        }
    }

    public void setRefreshListener(OnRefreshListener listener){

        this.listener = listener;

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.zLog().e(LOG_TAG+"onInterceptTouchEvent");
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 记录手指按下的位置
                mStartY = ev.getY();
                mStartX = ev.getX();
                //初始化左右滑动事件为false
                mDragger = false;
                break;
            case MotionEvent.ACTION_MOVE:
                //如果左右滑动事件为true  直接返回false 不拦截事件
                if (mDragger) {
                    LogUtil.zLog().e(LOG_TAG+"传下去--了 "+this.toString());
                    return false;
                }

                // 获取当前手指位置
                float endY = ev.getY();
                float endX = ev.getX();
                //获取X,Y滑动距离的绝对值
                float distanceX = Math.abs(endX - mStartX);
                float distanceY = Math.abs(endY - mStartY);

                // 如果X轴位移大于Y轴距离，那么将事件交给其他控件
                if (distanceX > mTouchSlop && distanceX > distanceY) {
                    LogUtil.zLog().e(LOG_TAG+"传下去--  置状态 "+this.toString());
                    mDragger = true;
                    return false;
                }
                LogUtil.zLog().e(LOG_TAG+"传下去--  走完了 "+this.toString());
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                //初始化左右滑动事件为false
                mDragger = false;
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
    /**
     *
     * 下拉回调监听
     */
    public interface OnRefreshListener{
        void onRefresh();
    }

    public MyCustomRefreshHeader getRefreshHeader() {
        return refreshHeader;
    }
}
