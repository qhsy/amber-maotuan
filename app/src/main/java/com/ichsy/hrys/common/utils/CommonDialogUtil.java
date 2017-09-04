package com.ichsy.hrys.common.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.ichsy.hrys.R;
import com.ichsy.hrys.common.utils.imageloadutils.ImageLoaderUtils;
import com.ichsy.hrys.common.view.dialog.CommonDialog;
import com.ichsy.hrys.common.view.dialog.SimpleDialogView;
import com.ichsy.hrys.entity.ArtPic;
import com.ichsy.hrys.model.person.adapter.PicPagerAdapter;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.util.List;

import rx.functions.Action1;
import zz.mk.utilslibrary.FileUtil;
import zz.mk.utilslibrary.ScreenUtil;
import zz.mk.utilslibrary.ToastUtils;

/**
 * 部分公用对话框
 * author: ihesen on 2016/12/6 11:19
 * email: hesen@ichsy.com
 */

public class CommonDialogUtil {

    public void showBigPic(Activity activity, final List<ArtPic> mPics, int position) {
        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Black_NoTitleBar);
        View rootView = View.inflate(activity, R.layout.dialog_pics, null);

        final TextView martTextView = (TextView) rootView.findViewById(R.id.tv_pic_mark);
        martTextView.setText(position + 1 + "/" + mPics.size());

        final ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        PicPagerAdapter adapter = new PicPagerAdapter(activity, mPics);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
        viewPager.setTag(position);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                martTextView.setText(position + 1 + "/" + mPics.size());
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(rootView);
        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimMaterialDialog); //设置窗口弹出动画
        dialog.show();
    }

    public void showBigPic(Activity activity, ArtPic artPic) {
        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Black_NoTitleBar);
        View rootView = View.inflate(activity, R.layout.item_material_big_pic, null);
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.iv_material_big_pic);
        int windowWidth = ScreenUtil.getScreenWidth(activity);
        int windowHeight = ScreenUtil.getScreenHeight(activity);
        ImageLoaderUtils.loadViewImageWithWH(activity, imageView, artPic.picUrl, windowWidth, windowHeight);

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CommonDialogUtil dialogUtil = new CommonDialogUtil();
                dialogUtil.showSaveImgDialog(imageView);
                return false;
            }
        });
        dialog.setContentView(rootView);
        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimMaterialDialog); //设置窗口弹出动画
        dialog.show();
    }

    /**
     * 素材/付款码 下载成功
     */
//    public void showSaveResourceCard(final Activity activity, String content) {
//        final CommonGuideDialog dialog = new CommonGuideDialog(activity, CommonGuideDialog.CommonGuideDialogStyle.BUTTONTYPE_TWO);
//        dialog.getFacePic().setBackgroundResource(R.drawable.pic_tjcg);
//        dialog.getTittle().setText("操作成功!");
//        dialog.getContent().setText(content);
//        dialog.getLeftBtn().setText("取消");
//        dialog.getLeftBtn().setTextColor(ContextCompat.getColor(activity, R.color.color_standard_3));
//        dialog.getRightBtn().setText("分享到微信");
//        dialog.getRightBtn().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                openWechat(activity);
//
//            }
//        });
//        dialog.getLeftBtn().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//    }
    private void openWechat(Activity activity) {
        try {
            Intent intent = new Intent();
            ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            activity.startActivity(intent);
        } catch (Exception e) {
            ToastUtils.showShortToast("未安装微信");
        }
    }

    /**
     * 保存图片
     */
    public void showSaveImgDialog(final ImageView saveImag) {
        final String filePath = Environment.getExternalStorageDirectory().getPath() + File.separator + "DCIM" + File.separator + "Camera" + File.separator;
        SimpleDialogView view = new SimpleDialogView(saveImag.getContext());
        view.getBottomTV().setText("取消");
        view.getTopTV().setVisibility(View.GONE);
        view.getCenterTV().setText("保存");

        final Dialog saveDialog = DialogUtils.getBottomDialog(saveImag.getContext(), view, true);
        view.getBottomTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDialog.dismiss();
            }
        });
        view.getCenterTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDialog.dismiss();
                boolean isSave = FileUtil.drawableTofile(saveImag.getDrawable(), filePath, System.currentTimeMillis() + ".jpg");
                if (isSave) {
                    ToastUtils.showShortToast("已保存到相册");
                } else {
                    ToastUtils.showShortToast("保存失败~");
                }

            }
        });
        saveDialog.show();
    }

    /**
     * 弹出拨打电话弹框
     */
    public static void showCallDialog(final Activity context, final String phoneNum) {
        final RxPermissions rxPermissions = new RxPermissions(context);
        final CommonDialog callDialog = new CommonDialog(context, CommonDialog.CommonDialogViewType.TWO);
        callDialog.setTittleAndContent("联系客服", phoneNum);
        callDialog.getCloseBtn().setVisibility(View.GONE);
        callDialog.getLeftButton().setText("取消");
        callDialog.getRightButton().setText("拨打");
        callDialog.getRightButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNum));
                context.startActivity(intent);
                callDialog.dismiss();
            }
        });
    }

}
