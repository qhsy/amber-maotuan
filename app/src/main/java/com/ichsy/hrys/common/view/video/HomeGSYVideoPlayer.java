package com.ichsy.hrys.common.view.video;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ichsy.hrys.R;
import com.ichsy.hrys.common.utils.TextureVideoViewOutlineProvider;
import com.shuyu.gsyvideoplayer.GSYRenderView;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import zz.mk.utilslibrary.ScreenUtil;


/**
 * author: zhu on 2017/8/4 14:59
 * email: mackkilled@gmail.com
 */

public class HomeGSYVideoPlayer extends StandardGSYVideoPlayer {

    private RelativeLayout mRootView;
    private RelativeLayout mThumb;
    private RelativeLayout mSurface;

    public HomeGSYVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public HomeGSYVideoPlayer(Context context) {
        super(context);
    }

    public HomeGSYVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        initView();

    }

    private void initView() {
        mRootView = (RelativeLayout) findViewById(R.id.video_rootview);
        mThumb = (RelativeLayout) findViewById(R.id.thumb);
        mSurface = (RelativeLayout) findViewById(R.id.surface_container);
        mRootView.setBackgroundResource(R.drawable.roundcorner_rect_black_82px);
        mThumb.setBackgroundResource(R.drawable.roundcorner_rect_black_82px);
        mSurface.setBackgroundResource(R.drawable.roundcorner_rect_black_82px);
    }

    @Override
    public int getLayoutId() {
        if (mIfCurrentIsFullscreen) {
            return R.layout.video_seekbar_picture_land;
        } else {
            return R.layout.video_seekbar_picture_normal;
        }
    }

    @Override
    public void ResizeGSYRenderView(GSYRenderView mTextureView) {
        super.ResizeGSYRenderView(mTextureView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (mTextureView.getShowView() != null) {
                mTextureView.getShowView().setOutlineProvider(new TextureVideoViewOutlineProvider(ScreenUtil.dip2px(getActivityContext(), 9)));
                mTextureView.getShowView().setClipToOutline(true);
            }
        }
    }

    @Override
    protected void insertPictureOnSeekBar() {
        super.insertPictureOnSeekBar();
//        if (isIfCurrentIsFullscreen()) {
//            currentView = new View(getActivityContext());
//            mPreviewLayout.setVisibility(VISIBLE);
//            mPreviewLayout.post(new Runnable() {
//                @Override
//                public void run() {
//                    for (int i = 1; i < 4; i++) {
//                        initImageView(i * 30);
//                    }
//                }
//            });
//        } else {
//            mPreviewLayout.setVisibility(INVISIBLE);
//        }
    }

    private void initImageView(final int process) {
//        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        final ImageView imageView = new ImageView(getActivityContext());
//        imageView.setVisibility(INVISIBLE);
//        imageView.setImageResource(R.drawable.video_small_close);
//        imageView.setBackground(addStateListDrawable(getActivityContext(), R.drawable.pop_u, R.drawable.message_tip_bg));
//        imageView.setLayoutParams(lp);
//
//        imageView.post(new Runnable() {
//            @Override
//            public void run() {
//                imageView.setVisibility(VISIBLE);
//                Rect bounds = seekBar.getProgressDrawable().getBounds();
//                int left = bounds.width() * process / seekBar.getMax() + seekBar.getPaddingLeft() - imageView.getWidth() / 2;
//                RelativeLayout.LayoutParams lp1 = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
//                lp1.setMargins(left, 0, 0, 0);
//                imageView.setLayoutParams(lp1);
//            }
//        });
//
//        imageView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (currentView != v) {
//                    currentView.setSelected(false);
//                    currentView = v;
//                    currentView.setSelected(true);
//                }
//                seekBar.setProgress(process);
//                GSYVideoManager.instance().getMediaPlayer().seekTo(process * getDuration() / 100);
//            }
//        });
//
//        mPreviewLayout.addView(imageView);
    }

    public static StateListDrawable addStateListDrawable(Context context, int idNormal, int idPressed) {
        StateListDrawable drawable = new StateListDrawable();

        drawable.addState(new int[]{android.R.attr.state_selected}, context.getResources().getDrawable(idPressed));
        drawable.addState(new int[]{android.R.attr.state_enabled}, context.getResources().getDrawable(idNormal));
        drawable.addState(new int[]{}, context.getResources().getDrawable(idNormal));
        return drawable;
    }

    @Override
    protected void updateStartImage() {
        if (mStartButton instanceof ImageView) {
            ImageView imageView = (ImageView) mStartButton;
            if (mCurrentState == CURRENT_STATE_PLAYING) {
                imageView.setImageResource(R.drawable.video_click_pause_selector);
            } else if (mCurrentState == CURRENT_STATE_ERROR) {
                imageView.setImageResource(R.drawable.video_click_play_selector);
            } else {
                imageView.setImageResource(R.drawable.video_click_play_selector);
            }
        }
    }
}
