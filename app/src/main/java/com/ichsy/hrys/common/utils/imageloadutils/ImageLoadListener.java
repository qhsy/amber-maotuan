package com.ichsy.hrys.common.utils.imageloadutils;

import android.graphics.drawable.Drawable;

/**
 * 功能：
 * ＊创建者：赵然 on 16/6/15 14:20
 * ＊
 */
public interface ImageLoadListener {
   void  onException(Exception e);
    void onSuccess(Object sourceId, Drawable drawable);


}
