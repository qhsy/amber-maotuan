package com.ichsy.hrys.model.details.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ichsy.hrys.R;
import com.ichsy.hrys.common.utils.TextParser;
import com.ichsy.hrys.entity.ArtVideoReplyInfo;

/**
 * 详情页评论回复列
 * author: zhu on 2017/9/8 15:18
 * email: mackkilled@gmail.com
 */

public class CommentReplyAdapter extends BaseQuickAdapter<ArtVideoReplyInfo, BaseViewHolder> {

    public CommentReplyAdapter(Context context) {
        super(R.layout.item_detail_comment_reply_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArtVideoReplyInfo item) {
        TextView content = helper.getView(R.id.content);
        TextParser mTextParser = TextParser.getInstance();
        int mTextSize = 13;

        if (item.getReplySenderUserInfo() != null) {
            mTextParser.append(item.getReplySenderUserInfo().getUserName()+" :  ", mTextSize, ContextCompat.getColor(mContext, R.color.color_text_mediumcolor));
            mTextParser.append(item.getReplyContent(), mTextSize, ContextCompat.getColor(mContext, R.color.color_btn_gray2));
            mTextParser.parse(content);
        }
    }
}
