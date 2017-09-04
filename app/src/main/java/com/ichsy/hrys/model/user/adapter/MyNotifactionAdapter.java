package com.ichsy.hrys.model.user.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ichsy.hrys.R;
import com.ichsy.hrys.entity.ArtVideoNotifactionMessage;

/**
 * author: zhu on 2017/8/25 16:15
 * email: mackkilled@gmail.com
 */

public class MyNotifactionAdapter extends BaseQuickAdapter<ArtVideoNotifactionMessage, BaseViewHolder> {

    public MyNotifactionAdapter(Context context) {
        super(R.layout.item_message);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArtVideoNotifactionMessage  item) {

    }

}