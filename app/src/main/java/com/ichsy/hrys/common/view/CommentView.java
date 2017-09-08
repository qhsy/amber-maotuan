package com.ichsy.hrys.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ichsy.hrys.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 评论弹出框
 */
public class CommentView extends RelativeLayout {

    @BindView(R.id.et_comment_content)
    EditText mEtComment;
    @BindView(R.id.btn__comment_send)
    ImageView mBtnCommentSend;

    public CommentView(Context context) {
        super(context);
        init();
    }

    public CommentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CommentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.comment_layer, this);
        ButterKnife.bind(this);
    }

    /**
     * 获取评论内容
     */
    public String getCommentText() {
        return mEtComment.getText().toString().trim();
    }

    public void setOnSendCommentListener(OnClickListener clickListener) {
        mBtnCommentSend.setOnClickListener(clickListener);
    }

    public void clearContent() {
        mEtComment.setText("");
    }

    public void resetHintContent() {
        mEtComment.setHint("请输入评论");
    }

    public EditText getEditText() {
        return mEtComment;
    }
}
