package com.ichsy.hrys.common.view.video;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.ichsy.hrys.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;


/**
 * author: zhu on 2017/8/4 14:59
 * email: mackkilled@gmail.com
 */

public class PictureGSYVideoPlayer extends StandardGSYVideoPlayer {

//    private RelativeLayout mPreviewLayout;

//    private SeekBar seekBar;
//    private View currentView;

    public PictureGSYVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public PictureGSYVideoPlayer(Context context) {
        super(context);
    }

    public PictureGSYVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        initView();

    }

    private void initView() {
//        mPreviewLayout = (RelativeLayout) findViewById(R.id.pic_layout);
//        seekBar = getProgressBar();
    }

    @Override
    public int getLayoutId() {
        return R.layout.video_seekbar_picture;
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
