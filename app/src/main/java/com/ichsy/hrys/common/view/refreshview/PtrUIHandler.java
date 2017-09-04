package com.ichsy.hrys.common.view.refreshview;


import com.ichsy.hrys.common.view.refreshview.indicator.PtrIndicator;

/**
 * 下拉刷新UI接口
 */
public interface PtrUIHandler {

    /**
     * When the content view has reached top and refresh has been completed, view will be reset.
     *
     * @param frame
     * content重新回到顶部，header消失，整个下拉shuaxi9n过程全部结束之后，重置View
     */
    public void onUIReset(PtrFrameLayout frame);

    /**
     * prepare for loading
     *
     * @param frame
     * 准备刷新，Header将要出现时调用
     */
    public void onUIRefreshPrepare(PtrFrameLayout frame);

    /**
     * perform refreshing UI
     * 开始刷新，header进入刷新状态之前调用
     */
    public void onUIRefreshBegin(PtrFrameLayout frame);

    /**
     * perform UI after refresh
     * 刷新结束，Header开始向上移动之前调用
     */
    public void onUIRefreshComplete(PtrFrameLayout frame);

    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator);
}
