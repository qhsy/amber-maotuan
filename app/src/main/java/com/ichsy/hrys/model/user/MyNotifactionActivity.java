package com.ichsy.hrys.model.user;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
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
import com.ichsy.hrys.common.interfaces.OnReceiveOttoEventInterface;
import com.ichsy.hrys.common.utils.DialogUtils;
import com.ichsy.hrys.common.utils.LoginUtils;
import com.ichsy.hrys.common.utils.RequestUtils;
import com.ichsy.hrys.common.utils.http.HttpContext;
import com.ichsy.hrys.common.utils.otto.OttoController;
import com.ichsy.hrys.common.utils.otto.OttoEventEntity;
import com.ichsy.hrys.common.utils.otto.OttoEventType;
import com.ichsy.hrys.common.view.CommentView;
import com.ichsy.hrys.common.view.ScrollingPauseLoadImageRecyclerView;
import com.ichsy.hrys.common.view.dialog.SimpleDialogViewMessage;
import com.ichsy.hrys.common.view.refreshview.RefreshLay;
import com.ichsy.hrys.entity.ArtVideoInfo;
import com.ichsy.hrys.entity.ArtVideoNotifactionMessage;
import com.ichsy.hrys.entity.request.ArtSendVideoCommentInput;
import com.ichsy.hrys.entity.request.OnlyPageRequestEntity;
import com.ichsy.hrys.entity.response.ArtGetVideoNotifactionMessagResult;
import com.ichsy.hrys.model.base.BaseActivity;
import com.ichsy.hrys.model.login.LoginEvent;
import com.ichsy.hrys.model.login.LoginParams;
import com.ichsy.hrys.model.main.controller.TaskController;
import com.ichsy.hrys.model.user.adapter.MyNotifactionAdapter;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import zz.mk.utilslibrary.ScreenUtil;
import zz.mk.utilslibrary.ToastUtils;
import zz.mk.utilslibrary.system.InputMethodUtils;

import static com.ichsy.hrys.R.id.refresh;
import static com.ichsy.hrys.config.constants.StringConstant.COMMENT_COMMENT;
import static com.ichsy.hrys.config.constants.StringConstant.COMMENT_REPLY;
import static zz.mk.utilslibrary.system.InputMethodUtils.hideInputMethod;
import static zz.mk.utilslibrary.system.InputMethodUtils.isShouldHideInput;

/**
 * 我的消息
 * author: zhu on 2017/8/25 16:04
 * email: mackkilled@gmail.com
 */

public class MyNotifactionActivity extends BaseActivity implements RefreshLay.OnRefreshListener, OnReceiveOttoEventInterface, BaseQuickAdapter.RequestLoadMoreListener, View.OnLayoutChangeListener{
    @BindView(R.id.rl_rootview)
    View mRootView;
    @BindView(R.id.main_item_list)
    ScrollingPauseLoadImageRecyclerView mRecyclerView;
    @BindView(refresh)
    RefreshLay refreshLay;
    @BindView(R.id.cv_comment_layer)
    public CommentView cvCommentLayer;

    private OnlyPageRequestEntity mRequestParams;

    List<ArtVideoNotifactionMessage> mData = new ArrayList<>();

    MyNotifactionAdapter mAdapter;

    //发送评论
    ArtSendVideoCommentInput commentEntity;

    private Dialog commentDialog;
    @Override
    public void loadLayout() {
        setContentView(R.layout.activity_message_list);
    }

    @Override
    public void logic() {
        OttoController.register(this);
        showDefaultTittle("我的消息");

        setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_blue));
        getCenterTittleView().setTextColor(ContextCompat.getColor(getContext(), R.color.color_white));
        setLeftDrawable(R.drawable.icon_back_white);

        setParams();
        setCommentAdapter();
    }

    private void setParams() {
        mRequestParams = new OnlyPageRequestEntity();
        commentEntity = new ArtSendVideoCommentInput();
        mRequestParams.pageOption.itemCount = 10;
        mRequestParams.pageOption.pageNum = 0;
        //默认评论人
        commentEntity.publishType = COMMENT_REPLY;
    }

    private void setCommentAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MyNotifactionAdapter(getContext(),  mData);
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
        mRootView.addOnLayoutChangeListener(this);

        cvCommentLayer.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (!LoginUtils.isLogin(context)) {
                        LoginParams params = new LoginParams(context, LoginEvent.LOGIN);
                        CenterEventBus.getInstance().postTask(params);
                        return;
                    }
                }
            }
        });

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (!LoginUtils.isLogin(context)) {
                    LoginParams params = new LoginParams(context, LoginEvent.LOGIN);
                    CenterEventBus.getInstance().postTask(params);
                    return;
                }
                if (commentDialog != null) {
                    commentDialog = null;
                }
                commentDialog = getCommentDialog(getContext(), position);
                commentDialog.show();
            }
        });
        cvCommentLayer.setOnSendCommentListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!LoginUtils.isLogin(context)) {
                    LoginParams params = new LoginParams(context, LoginEvent.LOGIN);
                    CenterEventBus.getInstance().postTask(params);
                    return;
                }

                if (TextUtils.isEmpty(cvCommentLayer.getCommentText())) {
                    ToastUtils.showShortToast("回复内容不能为空!");
                    return;
                }
                if (!checkNet()) return;
                //发送添加评论请求
                commentEntity.commentContent = cvCommentLayer.getCommentText();

                mAdapter.sendComment(commentEntity);
                cvCommentLayer.clearContent();
                InputMethodUtils.closeSoftKeyboard(getContext());
            }
        });
    }

    /**
     * 获取评论弹窗
     *
     * @param context
     * @return
     */
    public Dialog getCommentDialog(final Context context, final int position) {
        SimpleDialogViewMessage view = new SimpleDialogViewMessage(context);
        view.getTopTV().setText("回复");
        view.getCenterTV().setText("查看");
        view.getBottomTV().setText("取消");

        final Dialog headerViewDialog = DialogUtils.getBottomDialog(context, view, true);
        view.getTopTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cvCommentLayer.setVisibility(View.VISIBLE);
                headerViewDialog.dismiss();
                String userName = mAdapter.getData().get(position).getReplySenderUserInfo().getUserName();
                commentEntity.publishType = COMMENT_REPLY;
                commentEntity.videoId = mAdapter.getData().get(position).getCommentMessage().getVideoId();
                commentEntity.commentId = mAdapter.getData().get(position).getCommentMessage().getCommentId();
                commentEntity.receiverCode = mAdapter.getData().get(position).getReplySenderUserInfo().getUserCode();
                cvCommentLayer.getEditText().setHint("回复:" + userName);
                showKeyBoard(cvCommentLayer.getEditText());
                headerViewDialog.dismiss();
            }
        });
        view.getCenterTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerViewDialog.dismiss();
                ArtVideoInfo data = new ArtVideoInfo();
                data.setVideoNumber(mAdapter.getData().get(position).getCommentMessage().getVideoId());
                TaskController.openVideoDetail(getContext(),data);
            }
        });
        view.getBottomTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerViewDialog.dismiss();
            }
        });

        return headerViewDialog;
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
        if (checkResponse(httpContext)) {
            if (mRequestParams.pageOption.pageNum == 0) {
                mData.clear();
                if (result.videoNotifactionMessageList != null && result.videoNotifactionMessageList.size() > 0) {
                    mData.addAll(result.videoNotifactionMessageList);
                }
                mAdapter.setNewData(mData);
            } else {
                if (result.pageResults.isMore) {
                    if (result.videoNotifactionMessageList != null && result.videoNotifactionMessageList.size() > 0) {
                        mAdapter.addData(result.videoNotifactionMessageList);
                    }
                    mAdapter.loadMoreComplete();
                } else {
                    mAdapter.loadMoreEnd();
                }
            }
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

    //触碰其他区域关闭键盘
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            View v = getCurrentFocus();
            View v = cvCommentLayer;
            if (isShouldHideInput(v, ev)) {
                if (hideInputMethod(this, v)) {
                    return true; //隐藏键盘时，其他控件不响应点击事件==》注释则不拦截点击事件
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > 0)) {

        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > 0)) {
            cvCommentLayer.clearContent();
            cvCommentLayer.resetHintContent();
            commentEntity.publishType = COMMENT_COMMENT;
            cvCommentLayer.setVisibility(View.GONE);
        }
    }
}
