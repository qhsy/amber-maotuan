package com.ichsy.hrys.model.main;

import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ichsy.hrys.R;
import com.ichsy.hrys.common.interfaces.OnReceiveOttoEventInterface;
import com.ichsy.hrys.common.utils.RequestUtils;
import com.ichsy.hrys.common.utils.http.HttpContext;
import com.ichsy.hrys.common.utils.otto.OttoController;
import com.ichsy.hrys.common.utils.otto.OttoEventEntity;
import com.ichsy.hrys.common.utils.otto.OttoEventType;
import com.ichsy.hrys.common.view.CustomLoadMoreView;
import com.ichsy.hrys.common.view.DividerItemDecoration;
import com.ichsy.hrys.common.view.ScrollingPauseLoadImageRecyclerView;
import com.ichsy.hrys.common.view.convenientbanner.ConvenientBanner;
import com.ichsy.hrys.common.view.convenientbanner.holder.CBViewHolderCreator;
import com.ichsy.hrys.common.view.convenientbanner.listener.OnItemClickListener;
import com.ichsy.hrys.common.view.refreshview.RefreshLay;
import com.ichsy.hrys.common.view.video.HomeGSYVideoPlayer;
import com.ichsy.hrys.config.constants.StringConstant;
import com.ichsy.hrys.entity.ArtVideoPromotionDetail;
import com.ichsy.hrys.entity.request.ArtGetVideoListInputEntity;
import com.ichsy.hrys.entity.response.ArtGetVideoListResult;
import com.ichsy.hrys.model.base.BaseFragment;
import com.ichsy.hrys.model.main.adapters.BannerImageHolderView;
import com.ichsy.hrys.model.main.adapters.HomeAdapter;
import com.ichsy.hrys.model.main.controller.TaskController;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import zz.mk.utilslibrary.ScreenUtil;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_SETTLING;
import static com.ichsy.hrys.R.id.refresh;


/**
 * author: zhu on 2017/8/23 13:38
 * email: mackkilled@gmail.com
 */

public class ItemContentTaskFragment extends BaseFragment implements RefreshLay.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, OnReceiveOttoEventInterface {
    @BindView(R.id.main_item_list)
    ScrollingPauseLoadImageRecyclerView mRecyclerView;
    @BindView(refresh)
    RefreshLay refreshLay;

    ArtGetVideoListInputEntity mRequestParams;

    HomeAdapter homeAdapter;
    LinearLayoutManager layoutManager;

    /**
     * 用于标识 第一次从NewContentTaskFragment进入后，第一个tab无需请求数据
     */
    private boolean isNeedRequest = true;

    private ConvenientBanner mTopBanner;
    private BannerImageHolderView localImageHolderView;

    @Override
    public void loadLayout() {
        setContentView(R.layout.fragment_main_item);
    }

    @Override
    public void logic() {
        OttoController.register(this);
        mRequestParams = new ArtGetVideoListInputEntity();
        if (getArguments() != null) {
            mRequestParams.type = getArguments().getString(StringConstant.ITEM_TYPE, "");
            isNeedRequest = getArguments().getBoolean(StringConstant.NEED_REQUEST, true);
        }
        mRequestParams.pageOption.pageNum = 0;
        mRequestParams.pageOption.itemCount = 5;

        layoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        homeAdapter = new HomeAdapter(getContext());
        mRecyclerView.setAdapter(homeAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST, hasHeadView()));

        homeAdapter.setLoadMoreView(new CustomLoadMoreView());
        refreshLay.getRefreshHeader().setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_blue));

        refreshLay.setRefreshListener(this);
        homeAdapter.setOnLoadMoreListener(this, mRecyclerView);
        initBanner(bannerinfoList);
        addEmptyView();
    }

    public int firstVisibleItem, lastVisibleItem, visibleCount;

    @Override
    public void loadListener() {
        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TaskController.openVideoDetail(context, homeAdapter.getData().get(position));
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            boolean scrollState = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case SCROLL_STATE_IDLE: //滚动停止
                        scrollState = false;
//                        autoPlayVideo(recyclerView);
                        break;
                    case SCROLL_STATE_DRAGGING: //手指拖动
                        scrollState = true;
                        break;
                    case SCROLL_STATE_SETTLING: //惯性滚动
                        scrollState = true;
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                visibleCount = lastVisibleItem - firstVisibleItem;

                //大于0说明有播放
                if (GSYVideoManager.instance().getPlayPosition() >= 0) {
                    //当前播放的位置
                    int position = GSYVideoManager.instance().getPlayPosition();
                    //对应的播放列表TAG
                    if (GSYVideoManager.instance().getPlayTag().equals(HomeAdapter.TAG) && (position < firstVisibleItem || position > lastVisibleItem)) {
                        homeAdapter.getData().get(position).isTime = true;
                        homeAdapter.notifyItemChanged(position);
                        GSYVideoManager.onPause();
                    }
                }
            }


        });
    }

    private void autoPlayVideo(RecyclerView view) {
        for (int i = 0; i < visibleCount; i++) {
            if (view != null && view.getChildAt(i) != null && view.getChildAt(i).findViewById(R.id.video_item_player) != null) {
                HomeGSYVideoPlayer homeGSYVideoPlayer = (HomeGSYVideoPlayer) view.getChildAt(i).findViewById(R.id.video_item_player);
                Rect rect = new Rect();
                homeGSYVideoPlayer.getLocalVisibleRect(rect);
                int videoheight3 = homeGSYVideoPlayer.getHeight();
                Log.e("videoTest", "i=" + i + "===" + "videoheight3:" + videoheight3 + "===" + "rect.top:" + rect.top + "===" + "rect.bottom:" + rect.bottom);
                if (rect.top == 0 && rect.bottom == videoheight3) {
                    if (homeGSYVideoPlayer.getCurrentState() == homeGSYVideoPlayer.CURRENT_STATE_NORMAL || homeGSYVideoPlayer.getCurrentState() == homeGSYVideoPlayer.CURRENT_STATE_ERROR) {
                        homeGSYVideoPlayer.getStartButton().performClick();
                    }
                    return;
                }

            }
        }
        GSYVideoPlayer.releaseAllVideos();
    }

    @Override
    public void request() {
        if (checkNet()) {
            RequestUtils.getHomeItemPage(getRequestUnicode(), mRequestParams, this);
        } else {
            refreshLay.refreshComplete();
        }
        isNeedRequest = true;
    }

    @Override
    public void onViewClick(int viewId) {

    }


    @Override
    protected void lazyLoad() {

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

    @Override
    public void onHttpRequestBegin(String url) {
        super.onHttpRequestBegin(url);
        showLoadingDialog(true);
    }

    @Override
    public void onHttpRequestSuccess(String url, HttpContext httpContext) {
        super.onHttpRequestSuccess(url, httpContext);
        if (!isAdded()) {
            return;
        }
        ArtGetVideoListResult result = httpContext.getResponseObject();
        if (result.status == 1) {
            if (mRequestParams.pageOption.pageNum == 0) {
                homeAdapter.getData().clear();
                bindBannerData(result);

                if (result.videoList != null && result.videoList.size() > 0) {
                    homeAdapter.setNewData(result.videoList);
                }
            } else {
                if (result.videoList != null && result.videoList.size() > 0) {
                    homeAdapter.addData(result.videoList);
                }
                if (result.pageResults.isMore) {
                    homeAdapter.loadMoreComplete();
                } else {
                    homeAdapter.loadMoreEnd(true);
                }
            }
        }
    }

    /**
     * 控制banner高度
     *
     * @param result
     */
    private void bindBannerData(ArtGetVideoListResult result) {
        if (bannerMainLayout != null) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) bannerMainLayout.getLayoutParams();
            if (result.promotionPhotoList != null) {
                bannerinfoList.clear();
                if (result.promotionPhotoList.size() > 0) {
                    lp.height = ScreenUtil.dip2px(getContext(), 192);
                    bannerinfoList.addAll(result.promotionPhotoList);
                    mTopBanner.notifyDataSetChanged();
                } else {
                    lp.height = ScreenUtil.dip2px(getContext(), 0.5f);
                }
            } else {
                lp.height = ScreenUtil.dip2px(getContext(), 0.5f);
            }
            bannerMainLayout.setLayoutParams(lp);
        }
    }

    private void addEmptyView() {
        View view = View.inflate(getContext(), R.layout.item_my_price_empty, null);
        TextView textView = (TextView) view.findViewById(R.id.item_tv_nodata);
        textView.setText("暂无数据~");
        homeAdapter.setEmptyView(view);
    }

    @Override
    public void onHttpRequestFailed(String url, HttpContext httpContext, Throwable e) {
        super.onHttpRequestFailed(url, httpContext, e);
        showCommonExceptionLay(R.drawable.icon_nonet, getResources().getString(R.string.string_netconnect_timeout), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
                hiddenCommonException();
            }
        });
    }

    @Override
    public void onHttpRequestComplete(String url, HttpContext httpContext) {
        super.onHttpRequestComplete(url, httpContext);
        hiddenLoadingDialog();
        refreshLay.refreshComplete();
    }

    private void initBanner(List<ArtVideoPromotionDetail> bannerinfoList) {
        if (hasHeadView()) {
            homeAdapter.addHeaderView(getHeadView(bannerinfoList));
        }
    }

    /**
     * 是否添加headview
     *
     * @return
     */
    private boolean hasHeadView() {
        return mRequestParams.type.equals("1") || mRequestParams.type.equals("3");
    }

    /**
     * 轮播图
     */
    List<ArtVideoPromotionDetail> bannerinfoList = new ArrayList<>();
    RelativeLayout bannerMainLayout;

    private View getHeadView(final List<ArtVideoPromotionDetail> bannerinfoList) {
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.home_headview, (ViewGroup) mRecyclerView.getParent(), false);
        mTopBanner = (ConvenientBanner) headView.findViewById(R.id.banner);
        bannerMainLayout = (RelativeLayout) headView.findViewById(R.id.main_content);
        localImageHolderView = new BannerImageHolderView();

        mTopBanner.setPages(new CBViewHolderCreator<BannerImageHolderView>() {
            @Override
            public BannerImageHolderView createHolder() {
                return localImageHolderView;
            }
        }, bannerinfoList).setPageIndicator(new int[]{R.drawable.point_normal, R.drawable.point_read}).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
        mTopBanner.startTurning(5000);

        mTopBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                TaskController.forword(getActivity(), bannerinfoList.get(position));
            }
        });
        return headView;
    }

    @Override
    public void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
        if (mTopBanner != null) mTopBanner.startTurning(5000);
    }

    @Override
    public void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
        if (mTopBanner != null) mTopBanner.stopTurning();
    }

    @Subscribe
    @Override
    public void OnReceiveEvent(OttoEventEntity eventEntity) {
        if (eventEntity.getType() == OttoEventType.TASK_COLLECT_STATUS) {
//            onRefresh();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GSYVideoPlayer.releaseAllVideos();
        OttoController.unregister(this);
    }

}
