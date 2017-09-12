package com.ichsy.hrys.common.view;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.ichsy.hrys.R;

/**
 * Created by BlingBling on 2016/10/11.
 */

public class CustomLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.custom_view_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
