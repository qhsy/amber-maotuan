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
import com.ichsy.hrys.entity.ArtVideoInfo;
import com.ichsy.hrys.entity.ArtVideoUserInfo;
import com.ichsy.hrys.model.main.controller.TaskController;

/**
 * author: zhu on 2017/4/14 17:21
 * email: mackkill@gmail.com
 */

public class HomeAdapter extends BaseQuickAdapter<ArtVideoInfo, BaseViewHolder> {

    // 用来区分是否可点击， 例如 首页列表头像可点击 ，而个人中心无法点击 默认true 可点击
    private boolean isCanClick = true;

    public HomeAdapter(Context context) {
        super(R.layout.item_home_fragment);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArtVideoInfo item) {
        final ArtVideoUserInfo mUserInfo = item.getVideoUserInfo();
        ImageLoaderUtils.loadViewImage(mContext, (ImageView) helper.getView(R.id.item_videourl), item.getVideoCover(), R.drawable.list_placeholder,R.drawable.icon_wode,ImageStyleType.RoundedCorners);
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

    public boolean isCanClick() {
        return isCanClick;
    }

    public void setCanClick(boolean canClick) {
        isCanClick = canClick;
    }
}
