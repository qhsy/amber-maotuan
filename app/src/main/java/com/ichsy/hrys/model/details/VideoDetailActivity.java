package com.ichsy.hrys.model.details;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ichsy.centerbus.CenterEventBus;
import com.ichsy.hrys.R;
import com.ichsy.hrys.common.utils.LoginUtils;
import com.ichsy.hrys.common.utils.RequestUtils;
import com.ichsy.hrys.common.utils.UMAnalyticsUtils;
import com.ichsy.hrys.common.utils.http.HttpContext;
import com.ichsy.hrys.common.utils.imageloadutils.ImageLoaderUtils;
import com.ichsy.hrys.common.view.CommentView;
import com.ichsy.hrys.common.view.ScrollingPauseLoadImageRecyclerView;
import com.ichsy.hrys.common.view.refreshview.RefreshLay;
import com.ichsy.hrys.common.view.video.PictureGSYVideoPlayer;
import com.ichsy.hrys.config.constants.StringConstant;
import com.ichsy.hrys.entity.ArtShareModel;
import com.ichsy.hrys.entity.ArtVideoCommentInfo;
import com.ichsy.hrys.entity.ArtVideoCommentInfoMultiItemEntity;
import com.ichsy.hrys.entity.ArtVideoInfo;
import com.ichsy.hrys.entity.request.ArtCensusVideoPlayInput;
import com.ichsy.hrys.entity.request.ArtGetVideoInfoInput;
import com.ichsy.hrys.entity.request.ArtVideoShareInput;
import com.ichsy.hrys.entity.response.ArtGetVideoInfoResult;
import com.ichsy.hrys.model.base.BaseActivity;
import com.ichsy.hrys.model.details.adapter.CommentAdapter;
import com.ichsy.hrys.model.login.LoginEvent;
import com.ichsy.hrys.model.login.LoginParams;
import com.ichsy.hrys.um.share.UMShareController;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import zz.mk.utilslibrary.LogUtil;
import zz.mk.utilslibrary.ScreenUtil;
import zz.mk.utilslibrary.ToastUtils;
import zz.mk.utilslibrary.net.NetUtil;
import zz.mk.utilslibrary.system.InputMethodUtils;

import static com.ichsy.hrys.entity.ArtVideoCommentInfoMultiItemEntity.COMMENT_LIST;
import static com.ichsy.hrys.entity.ArtVideoCommentInfoMultiItemEntity.COMMENT_NUM;
import static com.ichsy.hrys.entity.ArtVideoCommentInfoMultiItemEntity.NO_DATA;
import static com.ichsy.hrys.entity.ArtVideoCommentInfoMultiItemEntity.PUBLISH_INFO;
import static zz.mk.utilslibrary.system.InputMethodUtils.hideInputMethod;
import static zz.mk.utilslibrary.system.InputMethodUtils.isShouldHideInput;

/**
 * 视频详细页
 * author: zhu on 2017/8/24 13:58
 * email: mackkilled@gmail.com
 */

public class VideoDetailActivity extends BaseActivity implements RefreshLay.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.back_iv)
    ImageView backIv;
    @BindView(R.id.detail_video)
    PictureGSYVideoPlayer videoPlayer;
    @BindView(R.id.share_iv)
    ImageView mShareIv;
    @BindView(R.id.detail_list)
    ScrollingPauseLoadImageRecyclerView mRecyclerView;
    @BindView(R.id.refresh)
    RefreshLay refreshLay;

    @BindView(R.id.cv_comment_layer)
    public CommentView cvCommentLayer;

    private OrientationUtils orientationUtils;

    ArtGetVideoInfoInput mRequestParams;
    ArtVideoInfo mArtTask;
    String userCode;
    CommentAdapter mAdapter;

    //是否是下拉刷新才刷新
    public boolean isPullDownRefresh = false;

    private ArtShareModel mShareModel;

    //权限申请
    RxPermissions rxPermissions;

    private List<ArtVideoCommentInfoMultiItemEntity> mData = new ArrayList<>();

    @Override
    public void loadLayout() {
        setContentView(R.layout.activity_video_detail);
    }

    @Override
    public void logic() {
        rxPermissions = new RxPermissions(getContext());
        hiddenTitlebar();
        mRequestParams = new ArtGetVideoInfoInput();
        mArtTask = (ArtVideoInfo) getIntent().getSerializableExtra(StringConstant.TASK_OBJ);
        if (mArtTask == null) {
            mArtTask = new ArtVideoInfo();
        }
        mRequestParams.videoNumber = mArtTask.getVideoNumber();
        mRequestParams.pageOption.pageNum = 0;
        mRequestParams.pageOption.itemCount = 5;
        setCommentAdapter();
        request();
    }

    private void setVideo(ArtVideoInfo artVideoInfo) {
        if (TextUtils.isEmpty(artVideoInfo.getVideoUrl())) {
            return;
        }
        orientationUtils = new OrientationUtils(this, videoPlayer);
        videoPlayer.setUp(artVideoInfo.getVideoUrl(), true, "");
        ImageView imageView = new ImageView(this);
        ImageLoaderUtils.loadViewImage(getContext(), imageView, artVideoInfo.getVideoCover());
        videoPlayer.setThumbImageView(imageView);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);
        videoPlayer.setRotateViewAuto(false);
        videoPlayer.setLockLand(false);
//        videoPlayer.setDismissControlTime(10000);
        if (NetUtil.isWifi(context)) {
            videoPlayer.startPlayLogic();
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle("提示").setMessage("当前未连接Wifi,确定继续播放吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    videoPlayer.startPlayLogic();
                }
            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        }

        // 统计视频播放次数
        ArtCensusVideoPlayInput mParam = new ArtCensusVideoPlayInput();
        mParam.channelId = "android";
        mParam.userCode = userCode;
        mParam.videoId = mArtTask.getVideoNumber();
        RequestUtils.getVideoPlayCount(getRequestUnicode(), mParam, null);
    }

    private void setCommentAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new CommentAdapter(getContext(), mData);
        mRecyclerView.setAdapter(mAdapter);
        refreshLay.setRefreshListener(this);
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
//        homeAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setEnableLoadMore(false);
        mAdapter.setRequestUnicode(getRequestUnicode());
        addEmptyView();
    }

    @Override
    public void loadListener() {
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoPlayer.startWindowFullscreen(VideoDetailActivity.this, true, true);
            }
        });
//        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                String userName = mAdapter.getData().get(position).videoCommentInfo.getSenderInfo().getUserName();
//                cvCommentLayer.getEditText().setHint("回复:" + userName);
//                showKeyBoard(cvCommentLayer.getEditText());
//            }
//        });
        cvCommentLayer.setOnSendCommentListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginUtils.isLogin(context)) {
                    if (TextUtils.isEmpty(cvCommentLayer.getCommentText())) {
                        ToastUtils.showShortToast("回复内容不能为空!");
                        return;
                    }
                    if (!checkNet()) return;
                    mAdapter.sendComment(cvCommentLayer.getCommentText(), mArtTask.getVideoNumber());
                    cvCommentLayer.clearContent();
                    InputMethodUtils.closeSoftKeyboard(getContext());
                } else {
                    LoginParams params = new LoginParams(context, LoginEvent.LOGIN);
                    CenterEventBus.getInstance().postTask(params);
                }
            }
        });
    }

    @Override
    public void request() {
        if (checkNet()) {
            RequestUtils.getVideoInfo(getRequestUnicode(), mRequestParams, this);
        } else {
            isPullDownRefresh = false;
            refreshLay.refreshComplete();
        }
    }

    @Override
    public void onHttpRequestBegin(String url) {
        super.onHttpRequestBegin(url);
        if (mRequestParams.pageOption.pageNum == 0 && !isPullDownRefresh) {
            showLoadingDialog(true);
        }
    }

    @Override
    public void onHttpRequestSuccess(String url, HttpContext httpContext) {
        super.onHttpRequestSuccess(url, httpContext);
        ArtGetVideoInfoResult result = httpContext.getResponseObject();
        if (result.status == 1) {
            if (mRequestParams.pageOption.pageNum == 0) {
                mData.clear();
                //视频发布人详情
                mData.add(getVideoInfo(result));
                //视频评论数目
                if (result.commentCount > 0) {
                    mData.add(getCommentNoItemEntity(result.commentCount));
                }
                //评论列表
                if (result.videoCommentList != null && result.videoCommentList.size() > 0) {
                    mData.addAll(getTodayRedPersonItemEntity(result.videoCommentList));
                } else {
                    mAdapter.loadMoreEnd(true);
                    mData.add(new ArtVideoCommentInfoMultiItemEntity(NO_DATA));
                }

                mAdapter.setNewData(mData);

                if (isPullDownRefresh) {
                    return;
                }

                //分享
                if (result.shareModel != null) {
//                    setViewClick(mShareIv, true);
                    mShareModel = result.shareModel;
                }

                //视频信息初始化
                if (result.videoInfo != null) {
                    ArtVideoInfo artVideoInfo = result.videoInfo;
                    mArtTask = artVideoInfo;
                    setVideo(artVideoInfo);
                }
            } else {
                if (result.videoCommentList != null && result.videoCommentList.size() > 0) {
                    mData.addAll(getTodayRedPersonItemEntity(result.videoCommentList));
                    if (result.videoCommentList.size() >= mRequestParams.pageOption.itemCount) {
                        mAdapter.loadMoreComplete();
                        return;
                    }
                }
                mAdapter.loadMoreEnd(true);
            }
        } else {
//            setViewClick(mShareIv, false);
        }
    }

    /**
     * 评论列表
     */
    private List<ArtVideoCommentInfoMultiItemEntity> getTodayRedPersonItemEntity(List<ArtVideoCommentInfo> videoCommentInfoList) {
        List<ArtVideoCommentInfoMultiItemEntity> list = new ArrayList<>();
        for (ArtVideoCommentInfo commentList : videoCommentInfoList) {
            ArtVideoCommentInfoMultiItemEntity entity = new ArtVideoCommentInfoMultiItemEntity(COMMENT_LIST);
            entity.videoCommentInfo = commentList;
            list.add(entity);
        }
        return list;
    }

    //评论数目
    private ArtVideoCommentInfoMultiItemEntity getCommentNoItemEntity(int count) {
        ArtVideoCommentInfoMultiItemEntity commentNum = new ArtVideoCommentInfoMultiItemEntity(COMMENT_NUM);
        commentNum.commentCount = count;
        return commentNum;
    }

    //视频信息
    private ArtVideoCommentInfoMultiItemEntity getVideoInfo(ArtGetVideoInfoResult result) {
        ArtVideoCommentInfoMultiItemEntity data = new ArtVideoCommentInfoMultiItemEntity(PUBLISH_INFO);
        data.collected = result.collected;
        if (result.videoInfo != null) {
            data.videoInfo = result.videoInfo;
        }
        return data;
    }

    @Override
    public void onHttpRequestFailed(String url, HttpContext httpContext, Throwable e) {
        super.onHttpRequestFailed(url, httpContext, e);
        showCommonExceptionLay(R.drawable.icon_nonet, getResources().getString(R.string.string_netconnect_timeout), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPullDownRefresh = false;
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

    private void addEmptyView() {
        View view = View.inflate(getContext(), R.layout.item_my_price_empty, null);
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.nodata_layout);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) layout.getLayoutParams();
        lp.setMargins(0, ScreenUtil.dip2px(getContext(), 75), 0, 0);
        layout.setLayoutParams(lp);
        ImageView imageView = (ImageView) view.findViewById(R.id.item_nodata_iv);
        imageView.setImageResource(R.drawable.nodata_comment);
        mAdapter.setEmptyView(view);
    }

    @Override
    public void onViewClick(int viewId) {

    }

    @OnClick({R.id.back_iv, R.id.share_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                HiddenKeyBoard();
                finish();
                break;
            case R.id.share_iv:
//                String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
//                rxPermissions.request(mPermissionList).subscribe(new Action1<Boolean>() {
//                    @Override
//                    public void call(Boolean granted) {
//                        if (granted) {
                UMAnalyticsUtils.onEvent(getContext(), "1170009");
                UMShareController controller = new UMShareController(getContext());
                controller.setShareContent(controller.getShareEntity(mShareModel));
                controller.openShare(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        ToastUtils.showShortToast("分享成功");
                        ArtVideoShareInput mRequest = new ArtVideoShareInput();
                        mRequest.videoNumber = mArtTask.getVideoNumber();
                        RequestUtils.getVideoShareCount(getRequestUnicode(), mRequest, null);
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                        ToastUtils.showShortToast("分享失败");
                        LogUtil.zLog().i("*****************************share: "+throwable.getMessage());
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {

                    }
                });
//                        } else {
//                            ToastUtils.showShortToast("请在设置中进行授权！");
//                        }
//                    }
//                });
                break;
        }
    }

    @Override
    public void onRefresh() {
        mRequestParams.pageOption.pageNum = 0;
        isPullDownRefresh = true;
        request();
    }

    @Override
    public void onLoadMoreRequested() {
        mRequestParams.pageOption.pageNum++;
        request();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            View v = getCurrentFocus();
            View v = cvCommentLayer;
            if (isShouldHideInput(v, ev)) {
                if(hideInputMethod(this, v)) {
                    return true; //隐藏键盘时，其他控件不响应点击事件==》注释则不拦截点击事件
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.onVideoPause();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
        GSYVideoPlayer.releaseAllVideos();
        if (orientationUtils != null) orientationUtils.releaseListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
