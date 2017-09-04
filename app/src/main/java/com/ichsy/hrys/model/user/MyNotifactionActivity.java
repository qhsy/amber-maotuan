package com.ichsy.hrys.model.user;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ichsy.hrys.R;
import com.ichsy.hrys.common.interfaces.OnReceiveOttoEventInterface;
import com.ichsy.hrys.common.utils.RequestUtils;
import com.ichsy.hrys.common.utils.http.HttpContext;
import com.ichsy.hrys.common.utils.otto.OttoController;
import com.ichsy.hrys.common.utils.otto.OttoEventEntity;
import com.ichsy.hrys.common.utils.otto.OttoEventType;
import com.ichsy.hrys.common.view.ScrollingPauseLoadImageRecyclerView;
import com.ichsy.hrys.common.view.refreshview.RefreshLay;
import com.ichsy.hrys.entity.request.OnlyPageRequestEntity;
import com.ichsy.hrys.entity.response.ArtGetVideoNotifactionMessagResult;
import com.ichsy.hrys.model.base.BaseActivity;
import com.ichsy.hrys.model.main.adapters.HomeAdapter;
import com.ichsy.hrys.model.main.controller.TaskController;
import com.squareup.otto.Subscribe;

import butterknife.BindView;
import zz.mk.utilslibrary.ScreenUtil;
import zz.mk.utilslibrary.ToastUtils;

import static com.ichsy.hrys.R.id.refresh;

/**
 * author: zhu on 2017/8/25 16:04
 * email: mackkilled@gmail.com
 */

public class MyNotifactionActivity extends BaseActivity implements RefreshLay.OnRefreshListener, OnReceiveOttoEventInterface, BaseQuickAdapter.RequestLoadMoreListener{
    @BindView(R.id.main_item_list)
    ScrollingPauseLoadImageRecyclerView mRecyclerView;
    @BindView(refresh)
    RefreshLay refreshLay;

    // TODO: 2017/8/25 clean

    private OnlyPageRequestEntity mRequestParams;

    HomeAdapter mAdapter;
    @Override
    public void loadLayout() {
        setContentView(R.layout.activity_mycollection);
    }

    @Override
    public void logic() {

        OttoController.register(this);
        showDefaultTittle("我的消息");

        setBackgroundColor(getResources().getColor(R.color.color_blue));
        getCenterTittleView().setTextColor(getResources().getColor(R.color.color_white));
        setLeftDrawable(R.drawable.icon_back_white);

        mRequestParams = new OnlyPageRequestEntity();
        mRequestParams.pageOption.itemCount = 10;
        mRequestParams.pageOption.pageNum = 0;

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new HomeAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);

        refreshLay.setRefreshListener(this);
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
//        homeAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setEnableLoadMore(false);

        addEmptyView();
    }

    @Override
    public void loadListener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TaskController.openVideoDetail(context, mAdapter.getData().get(position));
            }
        });
    }

    @Override
    public void request() {
        if (checkNet()) {
            RequestUtils.getNotifactionList(getRequestUnicode(), mRequestParams, this);
        } else {
            refreshLay.refreshComplete();
        }
    }

    @Override
    public void onViewClick(int viewId) {

    }



    @Override
    public void onHttpRequestBegin(String url) {
        super.onHttpRequestBegin(url);
        if (mRequestParams.pageOption.pageNum == 0) {
            showLoadingDialog(true);
        }
    }

    @Override
    public void onHttpRequestSuccess(String url, HttpContext httpContext) {
        super.onHttpRequestSuccess(url, httpContext);
        ArtGetVideoNotifactionMessagResult result = httpContext.getResponseObject();
        if (result.status == 1) {
            if (mRequestParams.pageOption.pageNum == 0) {
                mAdapter.getData().clear();
//                boolean videoList = result.collecteVideoList != null && result.collecteVideoList.size() > 0;
//                if (videoList) {
//                    mAdapter.setNewData(result.collecteVideoList);
//                }
            } else {
//                if (result.collecteVideoList != null) {
//                    if (result.collecteVideoList.size() < mRequestParams.pageOption.itemCount) {
//                        mAdapter.loadMoreEnd();
//                    } else {
//                        mAdapter.loadMoreComplete();
//                    }
//                    mAdapter.getData().addAll(result.collecteVideoList);
//                }
            }
        } else {
            ToastUtils.showShortToast(result.getError());
        }
    }

    @Override
    public void onHttpRequestFailed(String url, HttpContext httpContext, Throwable e) {
        super.onHttpRequestFailed(url, httpContext, e);
        refreshLay.refreshComplete();
        if (mRequestParams.pageOption.pageNum > 0) {
            mAdapter.loadMoreFail();
        }
    }

    @Override
    public void onHttpRequestComplete(String url, HttpContext httpContext) {
        super.onHttpRequestComplete(url, httpContext);
        hiddenLoadingDialog();
        refreshLay.refreshComplete();
    }

    private void addEmptyView() {
        View view = View.inflate(getContext(), R.layout.item_my_price_empty, null);
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.nodata_layout);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) layout.getLayoutParams();
        lp.setMargins(0, ScreenUtil.dip2px(getContext(), 150), 0, 0);
        layout.setLayoutParams(lp);
        ImageView imageView = (ImageView) view.findViewById(R.id.item_nodata_iv);
        imageView.setImageResource(R.drawable.nodata_message);
        mAdapter.setEmptyView(view);
    }

    @Override
    public void onRefresh() {
        mRequestParams.pageOption.pageNum = 0;
        request();
    }

    @Override
    public void onLoadMoreRequested() {
        mRequestParams.pageOption.pageNum++;
        request();
    }

    @Subscribe
    @Override
    public void OnReceiveEvent(OttoEventEntity eventEntity) {
        if (OttoEventType.CANCLE_COLLECTION == eventEntity.getType()) {
        }
    }
}
