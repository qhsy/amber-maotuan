package com.ichsy.hrys.model.user.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ichsy.hrys.R;
import com.ichsy.hrys.model.user.bean.UserCenterItemBean;

/**
 * author: zhu on 2017/8/24 17:57
 * email: mackkilled@gmail.com
 */

public class UserCenterGridAdapter extends BaseQuickAdapter<UserCenterItemBean, BaseViewHolder> {
    private Context context;

    public UserCenterGridAdapter(Context context) {
        super(R.layout.item_usercenter);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, UserCenterItemBean item) {
        ((ImageView) helper.getView(R.id.usercenter_item_icon)).setImageResource(item.getIcon());
        ((TextView) helper.getView(R.id.usercenter_item_txt)).setText(item.getText());
    }
}
