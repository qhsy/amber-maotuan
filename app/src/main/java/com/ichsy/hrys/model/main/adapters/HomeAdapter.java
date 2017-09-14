package com.ichsy.hrys.model.main.adapters;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ichsy.hrys.R;
import com.ichsy.hrys.common.utils.imageloadutils.ImageLoaderUtils;
import com.ichsy.hrys.common.utils.imageloadutils.ImageStyleType;
import com.ichsy.hrys.common.view.video.HomeGSYVideoPlayer;
import com.ichsy.hrys.common.view.video.SampleListener;
import com.ichsy.hrys.entity.ArtVideoInfo;
import com.ichsy.hrys.entity.ArtVideoUserInfo;
import com.ichsy.hrys.model.main.controller.TaskController;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import zz.mk.utilslibrary.ScreenUtil;

/**
 * author: zhu on 2017/4/14 17:21
 * email: mackkill@gmail.com
 */

public class HomeAdapter extends BaseQuickAdapter<ArtVideoInfo, BaseViewHolder> {
    public static final String TAG = HomeAdapter.class.getSimpleName();
    // 用来区分是否可点击， 例如 首页列表头像可点击 ，而个人中心无法点击 默认true 可点击
    private boolean isCanClick = true;
    Context activity;
    GSYVideoOptionBuilder gsyVideoOptionBuilder;
    HomeGSYVideoPlayer gsyVideoPlayer;

    private int oldPosition;

    public HomeAdapter(Context context) {
        super(R.layout.item_home_fragment);
        this.activity = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ArtVideoInfo item) {
        Log.i("fuyong", "convert");
        final ArtVideoUserInfo mUserInfo = item.getVideoUserInfo();
        gsyVideoPlayer = helper.getView(R.id.video_item_player);
        setVideoPlay(helper, item, gsyVideoPlayer);

        ImageLoaderUtils.loadViewImage(mContext, (ImageView) helper.getView(R.id.item_usericon), mUserInfo.getUserIconThumburl(), R.drawable.head_placeholder,R.drawable.icon_wode,ImageStyleType.CropCircle);

        helper.setVisible(R.id.item_videotime, item.isTime);
        helper.setText(R.id.item_videotime, item.getVideoLong());
        if (TextUtils.isEmpty(item.getVideoCaption())) {
            helper.setVisible(R.id.item_description, false);
        } else {
            helper.setVisible(R.id.item_description, true);
        }
        helper.setText(R.id.item_description, item.getVideoCaption());
        helper.setText(R.id.item_username, mUserInfo.getUserName());
        helper.setText(R.id.item_count, item.getVideoPlayCount()+"");

        // 头像区域是否可点击
        if (!isCanClick()) {
            return;
        }
        helper.getView(R.id.item_person_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskController.openPersionInfo((Activity) mContext, mUserInfo.getUserCode());
            }
        });
    }

    /**
     * 设置播放器
     * @param helper
     * @param pItem
     * @param gsyVideoPlayer
     */
    public void setVideoPlay(final BaseViewHolder helper, final ArtVideoInfo pItem, final HomeGSYVideoPlayer gsyVideoPlayer) {

        gsyVideoOptionBuilder = new GSYVideoOptionBuilder();
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtil.dip2px(mContext, 100));
        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(lp);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageLoaderUtils.loadViewRoundCornerImage(mContext, imageView, pItem.getVideoCover(), R.drawable.list_placeholder,R.drawable.list_placeholder, 22);

        gsyVideoOptionBuilder
                .setIsTouchWiget(false)
                .setThumbImageView(imageView)
                .setUrl(pItem.getVideoUrl())
                .setCacheWithPlay(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setPlayTag(TAG)
                .setShowFullAnimation(true)
                .setNeedLockFull(false)
                .setThumbPlay(true)
                .setPlayPosition(helper.getLayoutPosition())
                .setStandardVideoAllCallBack(new SampleListener() {
                    @Override
                    public void onPrepared(String url, Object... objects) {
                        super.onPrepared(url, objects);
                        if (!gsyVideoPlayer.isIfCurrentIsFullscreen()) {
                            //静音
                            GSYVideoManager.instance().setNeedMute(true);
                        }

                    }

                    @Override
                    public void onClickStartIcon(String url, Object... objects) {
                        super.onClickStartIcon(url, objects);
                        pItem.isTime = false;
                        helper.setVisible(R.id.item_videotime, pItem.isTime);
                        pItem.isTime = true;
                    }

                    @Override
                    public void onClickStartThumb(String url, Object... objects) {
                        super.onClickStartThumb(url, objects);
                        pItem.isTime = false;
                        helper.setVisible(R.id.item_videotime, pItem.isTime);
                        pItem.isTime = true;
                    }

                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        super.onQuitFullscreen(url, objects);
                        //全屏不静音
                        GSYVideoManager.instance().setNeedMute(true);
                    }

                    @Override
                    public void onEnterFullscreen(String url, Object... objects) {
                        super.onEnterFullscreen(url, objects);
                        GSYVideoManager.instance().setNeedMute(false);
                    }
                }).build(gsyVideoPlayer);
        //增加title
        gsyVideoPlayer.getTitleTextView().setVisibility(View.GONE);

        //设置返回键
        gsyVideoPlayer.getBackButton().setVisibility(View.GONE);

        //设置全屏按键功能
        gsyVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolveFullBtn(gsyVideoPlayer);
            }
        });
//        gsyVideoPlayer.getStartButton().setVisibility(View.GONE);
    }

    /**
     * 全屏幕按键处理
     */
    private void resolveFullBtn(final StandardGSYVideoPlayer standardGSYVideoPlayer) {
        standardGSYVideoPlayer.startWindowFullscreen(mContext, true, true);
    }

    public boolean isCanClick() {
        return isCanClick;
    }

    public void setCanClick(boolean canClick) {
        isCanClick = canClick;
    }

    public HomeGSYVideoPlayer getGsyVideoPlayer() {
        return gsyVideoPlayer;
    }
}
