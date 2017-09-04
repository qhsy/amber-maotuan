package com.ichsy.hrys.common.view.convenientbanner.holder;

import android.content.Context;
import android.view.View;

/**
 * author: zhu on 2017/4/19 10:40
 * email: mackkilled@gmail.com
 */

public interface Holder<T>{
    View createView(Context context);
    void UpdateUI(Context context, int position, T data);
}