package com.ichsy.hrys.common.view.refreshview;

import android.view.View;

/**
 * 下拉刷新功能的接口
 *
 */
public interface PtrHandler {

    /**
     * Check can do refresh or not. For example the content is empty or the first child is in view.
     * <p/>
     * 是否可以下拉刷新
     */
     boolean checkCanDoRefresh(final PtrFrameLayout frame, final View content, final View header);

    /**
     * When refresh begin
     * @param frame
     * 刷新功能的实现，处理业务数据刷新
     */
     void onRefreshBegin(final PtrFrameLayout frame);
}