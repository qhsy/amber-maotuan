package com.ichsy.hrys.model.details.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ichsy.hrys.R;
import com.ichsy.hrys.common.utils.RequestUtils;
import com.ichsy.hrys.common.utils.TextParser;
import com.ichsy.hrys.common.utils.http.HttpContext;
import com.ichsy.hrys.common.utils.http.SimpleRequestListener;
import com.ichsy.hrys.common.utils.imageloadutils.ImageLoaderUtils;
import com.ichsy.hrys.common.utils.imageloadutils.ImageStyleType;
import com.ichsy.hrys.entity.ArtVideoCommentInfo;
import com.ichsy.hrys.entity.ArtVideoCommentInfoMultiItemEntity;
import com.ichsy.hrys.entity.ArtVideoInfo;
import com.ichsy.hrys.entity.ArtVideoReplyInfo;
import com.ichsy.hrys.entity.ArtVideoUserInfo;
import com.ichsy.hrys.entity.request.ArtSendVideoCommentInput;
import com.ichsy.hrys.entity.response.BaseResponse;
import com.ichsy.hrys.model.details.VideoDetailActivity;
import com.ichsy.hrys.model.main.controller.TaskController;

import java.util.ArrayList;
import java.util.List;

import zz.mk.utilslibrary.DateUtil;
import zz.mk.utilslibrary.ToastUtils;

import static com.ichsy.hrys.entity.ArtVideoCommentInfoMultiItemEntity.COMMENT_LIST;
import static com.ichsy.hrys.entity.ArtVideoCommentInfoMultiItemEntity.COMMENT_NUM;
import static com.ichsy.hrys.entity.ArtVideoCommentInfoMultiItemEntity.NO_DATA;
import static com.ichsy.hrys.entity.ArtVideoCommentInfoMultiItemEntity.PUBLISH_INFO;

/**
 * author: zhu on 2017/8/24 16:06
 * email: mackkilled@gmail.com
 */

public class CommentAdapter extends BaseMultiItemQuickAdapter<ArtVideoCommentInfoMultiItemEntity, BaseViewHolder> {
    private Context activity;
    //收藏状态
    boolean collectStatus = false;
    //赞
    boolean zanStatus = false;

    public CommentAdapter(Context activity, List<ArtVideoCommentInfoMultiItemEntity> data) {
        super(data);
        this.activity = activity;
        //视频信息
        addItemType(PUBLISH_INFO, R.layout.item_home_detail_publishinfo);
        //评论数目
        addItemType(COMMENT_NUM, R.layout.item_home_detail_comment_no);
        //评论列表
        addItemType(COMMENT_LIST, R.layout.item_home_detail_comment);
        //空数据
        addItemType(NO_DATA, R.layout.item_nodata);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArtVideoCommentInfoMultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case PUBLISH_INFO:
                setVideoPublisherInfo(helper, item);
                break;
            case COMMENT_NUM:
                setCommentNo(helper, item);
                break;
            case COMMENT_LIST:
                setCommentList(helper, item);
                break;
        }
    }

    /**
     * 视频信息
     */
    protected void setVideoPublisherInfo(BaseViewHolder helper, ArtVideoCommentInfoMultiItemEntity item) {
        final ArtVideoInfo artVideoInfo = item.videoInfo;
        final ImageView mDetailCollect = helper.getView(R.id.detail_collect);
        //收藏状态初始化
        setCollectStatus(mDetailCollect, item.collected);
        collectStatus = item.collected;

        //视屏标题
        helper.setText(R.id.detail_title, artVideoInfo.getVideoCaption());
        //描述
        if (TextUtils.isEmpty(artVideoInfo.getVideoDescription())) {
            helper.setVisible(R.id.detail_descripe, false);
        } else {
            helper.setVisible(R.id.detail_descripe, true);
        }
        helper.setText(R.id.detail_descripe, artVideoInfo.getVideoDescription());
        //播放量
        helper.setText(R.id.detail_person_count, artVideoInfo.getVideoPlayCount()+"");
        //视频发布人
        if (artVideoInfo.getVideoUserInfo() != null) {
            final ArtVideoUserInfo videoUserInfo = artVideoInfo.getVideoUserInfo();
            ImageView publish_icon = helper.getView(R.id.detail_person_icon);
            ImageLoaderUtils.loadViewImage(activity, publish_icon, videoUserInfo.getUserIconThumburl(),R.drawable.head_placeholder, R.drawable.icon_wode,ImageStyleType.CropCircle);
            helper.setText(R.id.detail_person_name, videoUserInfo.getUserName());
            helper.setText(R.id.detail_person_mark, videoUserInfo.getUserIntroduction());

            publish_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TaskController.openPersionInfo((Activity) activity, videoUserInfo.getUserCode());
                }
            });
        }

        //收藏
        mDetailCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskController.collectTask(activity, getRequestUnicode(), artVideoInfo.getVideoNumber(), !collectStatus, new TaskController.CollectCallBack() {
                    @Override
                    public void onResult(boolean collcetResult, boolean collectOption) {
                        if (collcetResult) {
                            collectStatus = collectOption;
                            setCollectStatus(mDetailCollect, collectOption);
                            if (collectOption) {
                                ToastUtils.showShortToast(activity.getString(R.string.collect_success));
                            } else {
                                ToastUtils.showShortToast(activity.getString(R.string.collect_cancel));
                            }
                        }
                    }
                });
            }
        });
    }

    /**
     * 评论数目
     */
    protected void setCommentNo(BaseViewHolder helper, ArtVideoCommentInfoMultiItemEntity pItem) {
        //评论数目
        TextParser.getInstance().append("评论  (", 16).append(pItem.commentCount + "", 16, Color.parseColor("#5b76d2")).append(")", 16).parse((TextView) helper.getView(R.id.detail_list_comment));

    }

    /**
     * 评论列表
     * @param helper
     * @param pItem
     */
    protected void setCommentList(BaseViewHolder helper, ArtVideoCommentInfoMultiItemEntity pItem) {
        ArtVideoCommentInfo item = pItem.videoCommentInfo;
        ArtVideoUserInfo userInfo = item.getSenderInfo();

        helper.setText(R.id.detail_comment_content, item.getCommentContent());
        helper.setText(R.id.time, DateUtil.getTime(item.getCommentTime(),"yyyy-MM-dd HH:mm"));

        ImageLoaderUtils.loadViewImage(activity,(ImageView) helper.getView(R.id.detail_comment_icon),userInfo.getUserIconThumburl(),R.drawable.head_placeholder, R.drawable.icon_wode, ImageStyleType.CropCircle);
        helper.setText(R.id.detail_comment_name, userInfo.getUserName());

        //点赞
        TextView zan = helper.getView(R.id.detail_comment_thumbsup);


        //回复列表 只显示两条
        if (pItem.commentReplyList.size() > 0) {
            List<ArtVideoReplyInfo> commentReplyList = new ArrayList<>();
            commentReplyList.clear();
            helper.setVisible(R.id.detail_comment_reply_ll, true);
            if (pItem.commentReplyList.size() > 2) {
                helper.setVisible(R.id.detail_comment_findall, true);
                for (int i = 0; i < 2; i++) {
                    commentReplyList.add(pItem.commentReplyList.get(i));
                }
                setCommentReplyAdapter((RecyclerView) helper.getView(R.id.detail_comment_reply_list), commentReplyList);
            } else {
                helper.setVisible(R.id.detail_comment_findall, false);
            }
        } else {
            helper.setVisible(R.id.detail_comment_reply_ll, false);
        }

        zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskController.zanTask(activity, getRequestUnicode(), "", false, new TaskController.CollectCallBack() {
                    @Override
                    public void onResult(boolean collcetResult, boolean collectOption) {

                    }
                });
            }
        });
    }

    // 回复列表
    private void setCommentReplyAdapter(RecyclerView mRecyclerView, List<ArtVideoReplyInfo> commentReplyList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        CommentReplyAdapter mAdapter = new CommentReplyAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setNewData(commentReplyList);
    }

    /**
     * 发送评论
     * @param entity
     */
    public void sendComment(ArtSendVideoCommentInput entity) {
        //本地添加假数据
//        addComment(SharedPreferencesUtils.getUserInfo(mContext), receiverInfo, commentText, receiverInfo == null);

        RequestUtils.sendVideoComment(getRequestUnicode(), entity, new SimpleRequestListener() {

            @Override
            public void onHttpRequestSuccess(String url, HttpContext httpContext) {
                super.onHttpRequestSuccess(url, httpContext);
                BaseResponse result = httpContext.getResponseObject();
                if (result.status == 1) {
                    ((VideoDetailActivity) activity).isPullDownRefresh = true;
                    ((VideoDetailActivity) activity).onRefresh();
                    ToastUtils.showShortToast("回复成功!");
                }
            }
        });
    }

    /**
     * 任务收藏状态
     */
    protected void setCollectStatus(View view, boolean collection) {
        view.setSelected(collection);
    }

    public String getRequestUnicode() {
        return requestUnicode;
    }

    public void setRequestUnicode(String requestUnicode) {
        this.requestUnicode = requestUnicode;
    }

    private String requestUnicode;

}
