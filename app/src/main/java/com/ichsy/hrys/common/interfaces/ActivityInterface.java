package com.ichsy.hrys.common.interfaces;

/**
 * author: zhu on 2017/4/12 14:09
 * email: mackkilled@gmail.com
 */

public interface ActivityInterface {
    /**
     * 初始化控件
     */
    void loadLayout();
    /**
     * 逻辑代码
     */

    void logic();

    /**
     * 监听事件
     */
    void loadListener();

    /**
     * 请求代码
     */

    void request();

    /**
     * 点击事件
     */

    void onViewClick(int viewId);
}
