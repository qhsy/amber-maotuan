package com.ichsy.hrys.model.details;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ichsy.centerbus.CenterEventBus;
import com.ichsy.hrys.R;
import com.ichsy.hrys.common.utils.DialogUtils;
import com.ichsy.hrys.common.utils.LoginUtils;
import com.ichsy.hrys.common.utils.RequestUtils;
import com.ichsy.hrys.common.utils.SharedPreferencesUtils;
import com.ichsy.hrys.common.utils.http.HttpContext;
import com.ichsy.hrys.common.utils.http.SimpleRequestListener;
import com.ichsy.hrys.common.view.CommentView;
import com.ichsy.hrys.common.view.ScrollingPauseLoadImageRecyclerView;
import com.ichsy.hrys.common.view.dialog.CommonDialog;
import com.ichsy.hrys.common.view.dialog.SimpleDialogViewThree;
import com.ichsy.hrys.common.view.refreshview.RefreshLay;
import com.ichsy.hrys.config.constants.StringConstant;
import com.ichsy.hrys.entity.ArtVideoCommentInfo;
import com.ichsy.hrys.entity.ArtVideoCommentInfoMultiItemEntity;
import com.ichsy.hrys.entity.ArtVideoReplyInfo;
import com.ichsy.hrys.entity.request.ArtDeleteVideoCommentInput;
import com.ichsy.hrys.entity.request.ArtGetVideoCommentInfoInput;
import com.ichsy.hrys.entity.request.ArtSendVideoCommentInput;
import com.ichsy.hrys.entity.response.ArtGetVideoCommentInfoResult;
import com.ichsy.hrys.entity.response.BaseResponse;
import com.ichsy.hrys.model.base.BaseActivity;
import com.ichsy.hrys.model.details.adapter.CommentListAdapter;
import com.ichsy.hrys.model.login.LoginEvent;
import com.ichsy.hrys.model.login.LoginParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import zz.mk.utilslibrary.ClipUtil;
import zz.mk.utilslibrary.ToastUtils;
import zz.mk.utilslibrary.system.InputMethodUtils;

import static com.ichsy.hrys.R.id.refresh;
import static com.ichsy.hrys.config.constants.StringConstant.COMMENT_REPLY;
import static com.ichsy.hrys.entity.ArtVideoCommentInfoMultiItemEntity.COMMENT_LIST;
import static com.ichsy.hrys.entity.ArtVideoCommentInfoMultiItemEntity.COMMENT_NUM;
import static com.ichsy.hrys.entity.ArtVideoCommentInfoMultiItemEntity.ITEM_DIVIDER;
import static com.ichsy.hrys.entity.ArtVideoCommentInfoMultiItemEntity.ORIGN_REPLY;
import static zz.mk.utilslibrary.system.InputMethodUtils.hideInputMethod;
import static zz.mk.utilslibrary.system.InputMethodUtils.isShouldHideInput;

/**
 * Created by zhu on 2017/9/8.
 */

public class CommentListActivity extends BaseActivity implements RefreshLay.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, View.OnLayoutChangeListener {
    @BindView(R.id.rl_rootview)
    View mRootView;
    @BindView(R.id.main_item_list)
    ScrollingPauseLoadImageRecyclerView mRecyclerView;
    @BindView(refresh)
    RefreshLay refreshLay;
    @BindView(R.id.cv_comment_layer)
    public CommentView cvCommentLayer;

    CommentListAdapter mAdapter;

    ArtGetVideoCommentInfoInput mRequestParams;
    //发送评论
    ArtSendVideoCommentInput commentEntity;

    private Dialog commentDialog;
    private String videoNumber;

    private List<ArtVideoCommentInfoMultiItemEntity> mData = new ArrayList<>();

    @Override
    public void loadLayout() {
        setContentView(R.layout.activity_comment_list);
    }

    @Override
    public void logic() {
        showDefaultTittle("全部回复");
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_blue));
        getCenterTittleView().setTextColor(ContextCompat.getColor(getContext(), R.color.color_white));
        setLeftDrawable(R.drawable.icon_back_white);

        setParams();
        setCommentAdapter();
    }

    /**
     * 初始化请求参数
     */
    private void setParams() {
        mRequestParams = new ArtGetVideoCommentInfoInput();
        commentEntity = new ArtSendVideoCommentInput();
        mRequestParams.pageOption.pageNum = 0;
        mRequestParams.pageOption.itemCount = 10;
        mRequestParams.commentId = getIntent().getStringExtra(StringConstant.COMMENT_ID);
        //默认评论顶部
        commentEntity.publishType = COMMENT_REPLY;
        videoNumber = getIntent().getStringExtra(StringConstant.VIDEO_NUMBER);
    }

    private void setCommentAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new CommentListAdapter(getContext(), mData);
        mRecyclerView.setAdapter(mAdapter);
        refreshLay.setRefreshListener(this);
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
//        homeAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setEnableLoadMore(false);
        mAdapter.setRequestUnicode(getRequestUnicode());
    }

    @Override
    public void loadListener() {
        mRootView.addOnLayoutChangeListener(this);

        cvCommentLayer.getEditText().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!LoginUtils.isLogin(context)) {
                    LoginParams params = new LoginParams(context, LoginEvent.LOGIN);
                    CenterEventBus.getInstance().postTask(params);
                }
                commentEntity.commentId = mAdapter.getData().get(0).videoCommentInfo.getCommentId();
                commentEntity.receiverCode = mAdapter.getData().get(0).videoCommentInfo.getSenderInfo().getUserCode();
                return false;
            }
        });

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 1 || position == 2) return;
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
                commentEntity.videoId = videoNumber;
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
        SimpleDialogViewThree view = new SimpleDialogViewThree(context);
        view.getTopTV().setText("回复");
        view.getCenterTV().setText("复制");
        view.getLastTV().setText("删除");
        view.getBottomTV().setText("取消");

        if (isMyself(position)) {
            view.getLastTV().setVisibility(View.VISIBLE);
            view.getDevide2().setVisibility(View.VISIBLE);
        } else {
            view.getLastTV().setVisibility(View.GONE);
            view.getDevide2().setVisibility(View.GONE);
        }

        final Dialog headerViewDialog = DialogUtils.getBottomDialog(context, view, true);
        view.getTopTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerViewDialog.dismiss();
                String userName = "";
                if (position == 0) {
                    userName = mAdapter.getData().get(position).videoCommentInfo.getSenderInfo().getUserName();
                    commentEntity.commentId = mAdapter.getData().get(position).videoCommentInfo.getCommentId();
                    commentEntity.receiverCode = mAdapter.getData().get(position).videoCommentInfo.getSenderInfo().getUserCode();
                } else {
                    userName = mAdapter.getData().get(position).videoReplyInfo.getReplySenderUserInfo().getUserName();
                    commentEntity.commentId = mAdapter.getData().get(position).videoReplyInfo.getCommentId();
                    commentEntity.receiverCode = mAdapter.getData().get(position).videoReplyInfo.getReplySenderUserInfo().getUserCode();
                }
                commentEntity.publishType = COMMENT_REPLY;
                cvCommentLayer.getEditText().setHint("回复:" + userName);
                showKeyBoard(cvCommentLayer.getEditText());
            }
        });
        view.getCenterTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerViewDialog.dismiss();
                ClipUtil.copy(getContext(), mAdapter.getData().get(position).videoReplyInfo.getReplyContent());
                ToastUtils.showShortToast("内容已复制到剪切板");
            }
        });
        view.getLastTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CommonDialog callDialog = new CommonDialog(context, CommonDialog.CommonDialogViewType.TWO);
                callDialog.setTittleAndContent("温馨提示", "是否要删除该评论？");
                callDialog.getCloseBtn().setVisibility(View.GONE);
                callDialog.getLeftButton().setText("否");
                callDialog.getRightButton().setText("是");
                callDialog.getRightButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callDialog.dismiss();
                        ArtDeleteVideoCommentInput entity = new ArtDeleteVideoCommentInput();
                        entity.commentId = mAdapter.getData().get(position).videoReplyInfo.getReplyId();
                        RequestUtils.deleteVideoComment(getRequestUnicode(), entity, new SimpleRequestListener() {
                            @Override
                            public void onHttpRequestComplete(String url, HttpContext httpContext) {
                                BaseResponse result = httpContext.getResponseObject();
                                if (result.status == 1) {
                                    onRefresh();
                                    ToastUtils.showShortToast("删除成功！");
                                } else {
                                    ToastUtils.showShortToast("删除成功！");
                                }
                                headerViewDialog.dismiss();
                            }
                        });
                    }
                });


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

    private boolean isMyself(int position) {
        if (position == 0) {
            return (SharedPreferencesUtils.getUserInfo(context).getUserCode()).equals(mAdapter.getData().get(position).videoCommentInfo.getSenderInfo().getUserCode());
        } else {
            return (SharedPreferencesUtils.getUserInfo(context).getUserCode()).equals(mAdapter.getData().get(position).videoReplyInfo.getReplySenderUserInfo().getUserCode());
        }
    }

    @Override
    public void request() {
        if (checkNet()) {
            RequestUtils.getVideoCommentListInfo(getRequestUnicode(), mRequestParams, this);
        } else {
            refreshLay.refreshComplete();
        }
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
        if (checkResponse(httpContext)) {
            ArtGetVideoCommentInfoResult result = httpContext.getResponseObject();
            if (mRequestParams.pageOption.pageNum == 0) {
                mData.clear();
                //原评论信息
                mData.add(getOrignInfo(result.commentInfo));
                //分割线
                mData.add(new ArtVideoCommentInfoMultiItemEntity(ITEM_DIVIDER));
                //回复
                mData.add(new ArtVideoCommentInfoMultiItemEntity(COMMENT_NUM));

                //评论列表
                if (result.commentInfo.getCommentReplyList() != null && result.commentInfo.getCommentReplyList().size() > 0) {
                    mData.addAll(getCommentItemEntity(result.commentInfo.getCommentReplyList()));
                }
                mAdapter.setNewData(mData);

            } else {
                if (result.commentInfo.pageResults.isMore) {
                    List<ArtVideoReplyInfo> replyInfoList = result.commentInfo.getCommentReplyList();
                    if (replyInfoList != null && replyInfoList.size() > 0) {
                        mAdapter.addData(getCommentItemEntity(replyInfoList));
                    }
                    mAdapter.loadMoreComplete();
                } else {
                    mAdapter.loadMoreEnd();
                }
            }
        }
    }

    //视频信息
    private ArtVideoCommentInfoMultiItemEntity getOrignInfo(ArtVideoCommentInfo result) {
        ArtVideoCommentInfoMultiItemEntity data = new ArtVideoCommentInfoMultiItemEntity(ORIGN_REPLY);
        data.videoCommentInfo = result;
        return data;
    }

    // 评论列表
    private List<ArtVideoCommentInfoMultiItemEntity> getCommentItemEntity(List<ArtVideoReplyInfo> result) {
        List<ArtVideoCommentInfoMultiItemEntity> list = new ArrayList<>();
        for (ArtVideoReplyInfo replyInfo : result) {
            ArtVideoCommentInfoMultiItemEntity entity = new ArtVideoCommentInfoMultiItemEntity(COMMENT_LIST);
            entity.videoReplyInfo = replyInfo;
            list.add(entity);
        }
        return list;
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

    @Override
    public void onViewClick(int viewId) {

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
            commentEntity.commentId = mAdapter.getData().get(0).videoCommentInfo.getCommentId();
            commentEntity.receiverCode = mAdapter.getData().get(0).videoCommentInfo.getSenderInfo().getUserCode();
            commentEntity.publishType = COMMENT_REPLY;
        }
    }
}
