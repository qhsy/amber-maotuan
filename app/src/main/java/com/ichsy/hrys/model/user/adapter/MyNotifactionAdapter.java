package com.ichsy.hrys.model.user.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ichsy.hrys.R;
import com.ichsy.hrys.common.utils.RequestUtils;
import com.ichsy.hrys.common.utils.TextParser;
import com.ichsy.hrys.common.utils.http.HttpContext;
import com.ichsy.hrys.common.utils.http.SimpleRequestListener;
import com.ichsy.hrys.common.view.AvatorView;
import com.ichsy.hrys.entity.ArtVideoCommentMessage;
import com.ichsy.hrys.entity.ArtVideoNotifactionMessage;
import com.ichsy.hrys.entity.ArtVideoUserInfo;
import com.ichsy.hrys.entity.request.ArtSendVideoCommentInput;
import com.ichsy.hrys.entity.response.BaseResponse;
import com.ichsy.hrys.model.details.CommentListActivity;

import java.util.List;

import zz.mk.utilslibrary.DateUtil;
import zz.mk.utilslibrary.ToastUtils;

/**
 * 我的消息
 * author: zhu on 2017/8/25 16:15
 * email: mackkilled@gmail.com
 */

public class MyNotifactionAdapter extends BaseQuickAdapter<ArtVideoNotifactionMessage, BaseViewHolder> {
    private Context activity;

    public MyNotifactionAdapter(Context context, List<ArtVideoNotifactionMessage> data) {
        super(R.layout.item_message);
        this.activity = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ArtVideoNotifactionMessage pItem) {
        ArtVideoUserInfo senderInfo = pItem.getReplySenderUserInfo();
        ArtVideoUserInfo receiverInfo = pItem.getReplyReceiverUserInfo();
        ArtVideoCommentMessage videoCommentMessage = pItem.getCommentMessage();
        //头像
        AvatorView avatorView = helper.getView(R.id.detail_comment_icon);
        avatorView.setUserInfo(senderInfo, true);
        //评论人
        helper.setText(R.id.detail_comment_name, senderInfo.getUserName());
        //时间
        helper.setText(R.id.time, DateUtil.getTime(pItem.getReplyTime(), "yyyy-MM-dd HH:mm"));
        //回复内容
        int mTextSize = 13;
        TextParser.getInstance().append("回复 ", mTextSize, ContextCompat.getColor(mContext, R.color.color_text_mediumcolor))
        .append("我 ：", mTextSize, ContextCompat.getColor(mContext, R.color.color_btn_gray2))
        .append(pItem.getReplyContent(), mTextSize, ContextCompat.getColor(mContext, R.color.color_text_mediumcolor))
        .parse((TextView) helper.getView(R.id.detail_comment_content));
        //我的评论
        TextParser.getInstance().append("我的评论： ", mTextSize, ContextCompat.getColor(mContext, R.color.color_btn_black)).append(videoCommentMessage.getCommentContent(), mTextSize, ContextCompat.getColor(mContext, R.color.color_btn_gray2)).parse((TextView) helper.getView(R.id.detail_comment_mine));

        helper.addOnClickListener(R.id.detail_comment_content).addOnClickListener(R.id.detail_comment_mine).addOnClickListener(R.id.rootview);
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
                    ((CommentListActivity) activity).onRefresh();
                    ToastUtils.showShortToast("回复成功!");
                }
            }
        });
    }

    public String getRequestUnicode() {
        return requestUnicode;
    }

    public void setRequestUnicode(String requestUnicode) {
        this.requestUnicode = requestUnicode;
    }

    private String requestUnicode;

}