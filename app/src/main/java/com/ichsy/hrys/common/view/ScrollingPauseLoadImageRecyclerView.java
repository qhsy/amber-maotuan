package com.ichsy.hrys.common.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;

/**
 * 功能：滚动时不加载图片（目前只设置了快滑不加载  慢滑依旧加载）
 *
 */
public class ScrollingPauseLoadImageRecyclerView extends RecyclerView {


    public ScrollingPauseLoadImageRecyclerView(Context context) {
        this(context, null);
        init();
    }

    public ScrollingPauseLoadImageRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public ScrollingPauseLoadImageRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();

    }
    private void init(){
        addOnScrollListener(new AutoLoadScrollListener());
    }


    /**
     * 滑动自动加载监听器
     */
    private class AutoLoadScrollListener extends OnScrollListener {

        /**
         * 滑动是否暂停
         */
        private  boolean pauseOnScroll = false;
        //当屏幕停止滚动时为0；当屏幕滚动且用户使用的触碰或手指还在屏幕上时为1；由于用户的操作，屏幕产生惯性滑动时为2
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

            //根据newState状态做处理
            if(getContext() != null){
                switch (newState) {
                    case 0:
                        Glide.with(getContext()).resumeRequests();
                        break;

                    case 1:
                        if (pauseOnScroll) {
                            Glide.with(getContext()).pauseRequests();
                        } else {
                            Glide.with(getContext()).resumeRequests();
                        }
                        break;

                    case 2:
                        if (pauseOnFling) {
                            Glide.with(getContext()).pauseRequests();
                        } else {
                            Glide.with(getContext()).resumeRequests();
                        }
                        break;
                }
            }
        }

        /**
         * 快滑是否暂停
         */
        private  boolean pauseOnFling =  true;

        public AutoLoadScrollListener() {
            super();
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    }
}