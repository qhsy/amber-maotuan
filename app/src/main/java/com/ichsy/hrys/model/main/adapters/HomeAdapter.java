package com.ichsy.hrys.model.main.adapters;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ichsy.hrys.R;
import com.ichsy.hrys.common.utils.imageloadutils.ImageLoaderUtils;
import com.ichsy.hrys.common.utils.imageloadutils.ImageStyleType;
import com.ichsy.hrys.common.view.video.PictureGSYVideoPlayer;
import com.ichsy.hrys.common.view.video.SampleListener;
import com.ichsy.hrys.entity.ArtVideoInfo;
import com.ichsy.hrys.entity.ArtVideoUserInfo;
import com.ichsy.hrys.model.main.controller.TaskController;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

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
    PictureGSYVideoPlayer gsyVideoPlayer;

    public HomeAdapter(Context context) {
        super(R.layout.item_home_fragment);
        this.activity = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ArtVideoInfo item) {
        final ArtVideoUserInfo mUserInfo = item.getVideoUserInfo();
        gsyVideoPlayer = helper.getView(R.id.video_item_player);
        setVideoPlay(helper, item, gsyVideoPlayer);
//        ImageLoaderUtils.loadViewImage(mContext, (ImageView) helper.getView(R.id.item_videourl), item.getVideoCover(), R.drawable.list_placeholder,R.drawable.list_placeholder,ImageStyleType.RoundedCorners);

        ImageLoaderUtils.loadViewImage(mContext, (ImageView) helper.getView(R.id.item_usericon), mUserInfo.getUserIconThumburl(), R.drawable.head_placeholder,R.drawable.icon_wode,ImageStyleType.CropCircle);
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
    public void setVideoPlay(final BaseViewHolder helper, ArtVideoInfo pItem, final PictureGSYVideoPlayer gsyVideoPlayer) {

        gsyVideoOptionBuilder = new GSYVideoOptionBuilder();
        ImageView imageView = new ImageView(mContext);
        ImageLoaderUtils.loadViewImage(mContext, imageView, pItem.getVideoCover(), R.drawable.list_placeholder,R.drawable.list_placeholder,ImageStyleType.RoundedCorners);

        gsyVideoOptionBuilder
                .setIsTouchWiget(false)
                .setThumbImageView(imageView)
                .setUrl(pItem.getVideoUrl())
                .setCacheWithPlay(true)
                .setRotateViewAuto(true)
                .setLockLand(true)
                .setPlayTag(TAG)
                .setShowFullAnimation(true)
                .setNeedLockFull(true)
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
                        helper.setVisible(R.id.item_videotime, false);
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

    public PictureGSYVideoPlayer getGsyVideoPlayer() {
        return gsyVideoPlayer;
    }
}
