package com.ichsy.hrys.model.person.view;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ichsy.hrys.R;
import com.ichsy.hrys.common.utils.CommonDialogUtil;
import com.ichsy.hrys.common.utils.imageloadutils.ImageLoaderUtils;
import com.ichsy.hrys.entity.ArtPic;
import com.ichsy.hrys.entity.ArtUserInfo;

import static com.ichsy.hrys.common.utils.imageloadutils.ImageStyleType.Blur;


/**
 * 个人主页 - 头部信息
 * author: ihesen on 2017/1/6 17:28
 * email: hesen@ichsy.com
 */

public class PersonalInfoHeadView extends LinearLayout {

    //标签容器
    private LinearLayout mLablesLayout;
    //关注数
//    private TextView mTvAttentionCount;
    //粉丝数
//    private TextView mTvFansCount;
    //用户昵称
    private TextView mTvUserName;
    //用户性别图标
//    private ImageView mIvUserNameSexIcon;
    //关注操作钮
//    private ImageView mIvMarkattention;
    //个人简介
    private TextView mTvUserInstroduce;
    //用户头像
    private ImageView mIvUserIcon;
    //头部背景
    private ImageView mIvUserIconBg;

    public PersonalInfoHeadView(Context context) {
        super(context);
        init();
    }

    public PersonalInfoHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PersonalInfoHeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.item_userinfolay, this);
        mLablesLayout = (LinearLayout) findViewById(R.id.ll_personal_info_lables);
//        mTvAttentionCount = (TextView) findViewById(R.id.tv_attention_count);
//        mTvFansCount = (TextView) findViewById(R.id.tv_fans_count);
        mTvUserName = (TextView) findViewById(R.id.tv_itemuserinfo_username);
//        mIvUserNameSexIcon = (ImageView) findViewById(R.id.iv_itemuserinfo_username_sex_icon);
//        mIvMarkattention = (ImageView) findViewById(R.id.iv_personal_markattention);
        mTvUserInstroduce = (TextView) findViewById(R.id.tv_itemuserinfo_userinstroduce);
        mIvUserIcon = (ImageView) findViewById(R.id.sdv_itemuserinfo_icon);
        mIvUserIconBg = (ImageView) findViewById(R.id.iv_usericon_bg);

        //测试
//        updateUI();
    }

    /**
     * 更新UI
     * attentionStatus:0:未关注，1:关注，2：自己
     */
    public void updateUI(final ArtUserInfo artUserInfo, final int attentionStatus) {
        //用户头像
        ImageLoaderUtils.loadCircleWhite(getContext(), mIvUserIcon, artUserInfo.getUserIconThumburl());
        //头部背景图 高斯模糊
        ImageLoaderUtils.loadViewImage(getContext(), mIvUserIconBg, artUserInfo.getUserIconurl(),Blur);
        //昵称
        mTvUserName.setText(artUserInfo.getUserName());
        //性别icon
//        if (!StringConstant.SEX_UNKNOW.equals(artUserInfo.getSex())) {
//            boolean isMan = StringConstant.SEX_MAN.equals(artUserInfo.getSex());
//            int sexRes = isMan ? R.drawable.icon_nan : R.drawable.icon_nv;
//            mIvUserNameSexIcon.setImageResource(sexRes);
//        }
        int preColor = ContextCompat.getColor(getContext(), R.color.color_standard_8);
        int nextColor = ContextCompat.getColor(getContext(), R.color.color_white);
        //关注
//        TextParser.getInstance().append("关注  ", 13, preColor).append(NumberFormatUtil.formateNum2String(artUserInfo.getAttentionNum()), 13, nextColor).parse(mTvAttentionCount);
        //粉丝
//        TextParser.getInstance().append("粉丝  ", 13, preColor).append(NumberFormatUtil.formateNum2String(artUserInfo.getFansNum()), 13, nextColor).parse(mTvFansCount);
        //标签
        if (artUserInfo.domainLabelList != null && artUserInfo.domainLabelList.size() > 0) {
            mLablesLayout.removeAllViews();
            //前台控制只取三个标签
            int size = artUserInfo.domainLabelList.size() > 3 ? 3 : artUserInfo.domainLabelList.size();
            for (int i = 0; i < size; i++) {
                TextView view = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.item_personal_lables, this, false);
                view.setText(artUserInfo.domainLabelList.get(i));
                mLablesLayout.addView(view);
            }
        }
        //个人简介
        mTvUserInstroduce.setText(artUserInfo.getUserIntroduction());
        //关注状态
//        if (attentionStatus != 2) {
//            mIvMarkattention.setVisibility(View.VISIBLE);
//            mIvMarkattention.setSelected(attentionStatus == 1);
//        } else {
//            mIvMarkattention.setVisibility(View.GONE);
//        }
        setListener(artUserInfo);
    }

    private void setListener(final ArtUserInfo artUserInfo) {
        //关注点击
//        RxView.clicks(mIvMarkattention).throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
//            @Override
//            public void call(Void aVoid) {
//                //attentionStatus == 0 此时操作是关注的操作
//                markAttention(artUserInfo.getUserCode(), !mIvMarkattention.isSelected());
//            }
//        });
        //头像点击查看大图
        mIvUserIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ArtPic artPic = new ArtPic();
                artPic.picUrl = artUserInfo.getUserIconurl();
                new CommonDialogUtil().showBigPic((Activity) getContext(), artPic);
            }
        });
        //跳转关注页面
//        mTvAttentionCount.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                UMAnalyticsUtils.onEvent(getContext(), "1130002");
//                ForwordManager.openPersonalAttentionPage(getContext(), artUserInfo.getUserCode());
//            }
//        });
        //点击粉丝数页面
//        mTvFansCount.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                UMAnalyticsUtils.onEvent(getContext(), "1130001");
//                ForwordManager.openPersonalFansPage(getContext(), artUserInfo.getUserCode());
//            }
//        });
    }

    /**
     * 关注操作
     *
     * @param attention:true:关注操作 false:取消关注
     */
//    private void markAttention(String userCode, boolean attention) {
//        //用户登录
//        if (!LoginUtils.isLogin(getContext())) {
//            CenterEventBus.getInstance().postTask(new LoginParams(getContext(), LoginEvent.LOGIN));
//            return;
//        }
//        PersonalController.markAttention(this.toString(), userCode, attention, new PersonalController.AttentionCallBack() {
//            @Override
//            public void onResult(boolean attentionResult, boolean attentionOption) {
//                if (attentionResult) {
//                    startAttentionAnim(mIvMarkattention, attentionOption);
//                    ToastUtils.showMessage(getContext(), attentionOption ? "关注成功" : "取消关注成功");
//                }
//            }
//        });
//    }

    /**
     * 关注动画
     */
    private void startAttentionAnim(final View view, final boolean attention) {
        final Animation enterAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.attention_exit);
        enterAnimation.setFillAfter(true);
        final Animation exitAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.attention_enter);
        exitAnimation.setFillAfter(true);

        enterAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                mIvMarkattention.setSelected(attention);
                view.clearAnimation();
                view.startAnimation(exitAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(enterAnimation);
    }

}
