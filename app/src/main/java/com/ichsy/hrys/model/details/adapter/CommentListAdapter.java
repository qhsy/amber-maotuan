package com.ichsy.hrys.model.details.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ichsy.hrys.R;
import com.ichsy.hrys.common.utils.RequestUtils;
import com.ichsy.hrys.common.utils.TextParser;
import com.ichsy.hrys.common.utils.http.HttpContext;
import com.ichsy.hrys.common.utils.http.SimpleRequestListener;
import com.ichsy.hrys.common.view.AvatorView;
import com.ichsy.hrys.entity.ArtVideoCommentInfo;
import com.ichsy.hrys.entity.ArtVideoCommentInfoMultiItemEntity;
import com.ichsy.hrys.entity.ArtVideoReplyInfo;
import com.ichsy.hrys.entity.ArtVideoUserInfo;
import com.ichsy.hrys.entity.request.ArtSendVideoCommentInput;
import com.ichsy.hrys.entity.response.BaseResponse;
import com.ichsy.hrys.model.details.CommentListActivity;

import java.util.List;

import zz.mk.utilslibrary.DateUtil;
import zz.mk.utilslibrary.ToastUtils;

import static com.ichsy.hrys.entity.ArtVideoCommentInfoMultiItemEntity.COMMENT_LIST;
import static com.ichsy.hrys.entity.ArtVideoCommentInfoMultiItemEntity.COMMENT_NUM;
import static com.ichsy.hrys.entity.ArtVideoCommentInfoMultiItemEntity.ITEM_DIVIDER;
import static com.ichsy.hrys.entity.ArtVideoCommentInfoMultiItemEntity.ORIGN_REPLY;

/**
 * 全部回复
 * author: zhu on 2017/8/24 16:06
 * email: mackkilled@gmail.com
 */

public class CommentListAdapter extends BaseMultiItemQuickAdapter<ArtVideoCommentInfoMultiItemEntity, BaseViewHolder> {
    private Context activity;

    public CommentListAdapter(Context activity, List<ArtVideoCommentInfoMultiItemEntity> data) {
        super(data);
        this.activity = activity;
        //原评论
        addItemType(ORIGN_REPLY, R.layout.item_home_detail_comment);
        //分割线
        addItemType(ITEM_DIVIDER, R.layout.item_divider);
        //回复
        addItemType(COMMENT_NUM, R.layout.item_home_detail_comment_no);
        //评论列表
        addItemType(COMMENT_LIST, R.layout.item_home_detail_comment);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArtVideoCommentInfoMultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case ORIGN_REPLY:
                setOrignReply(helper, item);
                break;
            case COMMENT_NUM:
                setReplyItem(helper, item);
                break;
            case COMMENT_LIST:
                setCommentList(helper, item);
                break;
        }
    }

    /**
     * 回复
     */
    protected void setReplyItem(BaseViewHolder helper, ArtVideoCommentInfoMultiItemEntity pItem) {
        TextParser.getInstance().append("回复", 16, ContextCompat.getColor(mContext, R.color.color_btn_black)).parse((TextView) helper.getView(R.id.detail_list_comment));
    }

    /**
     * 原评论
     */
    protected void setOrignReply(BaseViewHolder helper, ArtVideoCommentInfoMultiItemEntity pItem) {
        final ArtVideoCommentInfo item = pItem.videoCommentInfo;
        final ArtVideoUserInfo userInfo = item.getSenderInfo();
        AvatorView avatorView = helper.getView(R.id.detail_comment_icon);
        avatorView.setUserInfo(userInfo, true);
        //评论人
        helper.setText(R.id.detail_comment_name, userInfo.getUserName());
        //时间
        helper.setText(R.id.time, DateUtil.getTime(item.getCommentTime(),"yyyy-MM-dd HH:mm"));
        //内容
        helper.setText(R.id.detail_comment_content, item.getCommentContent());
    }

    /**
     * 评论列表
     * @param helper
     * @param pItem
     */
    protected void setCommentList(BaseViewHolder helper, ArtVideoCommentInfoMultiItemEntity pItem) {
        ArtVideoReplyInfo videoReplyInfo = pItem.videoReplyInfo;
        ArtVideoUserInfo senderInfo = videoReplyInfo.getReplySenderUserInfo();
        ArtVideoUserInfo receiverInfo = videoReplyInfo.getReplyReceiverUserInfo();
        //头像
        AvatorView avatorView = helper.getView(R.id.detail_comment_icon);
        avatorView.setUserInfo(senderInfo, true);
        //评论人
        helper.setText(R.id.detail_comment_name, senderInfo.getUserName());
        //时间
        helper.setText(R.id.time, DateUtil.getTime(videoReplyInfo.getReplyTime(),"yyyy-MM-dd HH:mm"));
        //内容
        TextParser mTextParser = TextParser.getInstance();
        int mTextSize = 13;

        if (receiverInfo != null) {
            mTextParser.append("回复 ", mTextSize, ContextCompat.getColor(mContext, R.color.color_text_mediumcolor));
            mTextParser.append(receiverInfo.getUserName()+" ", mTextSize, ContextCompat.getColor(mContext, R.color.color_btn_gray2));
            mTextParser.append(videoReplyInfo.getReplyContent(), mTextSize, ContextCompat.getColor(mContext, R.color.color_text_mediumcolor));
            mTextParser.parse((TextView) helper.getView(R.id.detail_comment_content));
        }
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
