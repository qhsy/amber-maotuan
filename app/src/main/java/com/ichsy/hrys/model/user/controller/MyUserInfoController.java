package com.ichsy.hrys.model.user.controller;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;

import com.ichsy.hrys.common.utils.DialogUtils;
import com.ichsy.hrys.common.view.dialog.SimpleDialogView;
import com.ichsy.hrys.config.constants.IntConstant;

import java.io.File;

import zz.mk.utilslibrary.FileUtil;
import zz.mk.utilslibrary.ProviderUtil;

/**
 * 功能：用户信息界面的控制器
 * ＊创建者：赵然 on 16/5/15 21:04
 * ＊
 */
public class MyUserInfoController {
    public static String filePath = Environment.getExternalStorageDirectory() + File.separator + "iconphoto.jpg";

    /**
     * 获取头像选择的弹框
     *
     * @param context
     * @return
     */
    public static Dialog getHeaderViewDialo(final Context context) {
        SimpleDialogView view = new SimpleDialogView(context);
        view.getBottomTV().setText("取消");
        view.getTopTV().setText("相机");
        view.getCenterTV().setText("相册");

        final Dialog headerViewDialog = DialogUtils.getBottomDialog(context, view, true);
        view.getBottomTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerViewDialog.dismiss();
            }
        });
        view.getCenterTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerViewDialog.dismiss();
                openLocalPhoto(context);
            }
        });
        view.getTopTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerViewDialog.dismiss();
                openCamera(context);
            }
        });
        return headerViewDialog;
    }


    /**
     * 打开本地相册
     */
    private static void openLocalPhoto(Context context) {
        Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        ((Activity) context).startActivityForResult(picture, IntConstant.PICTURE);
    }

    /**
     * 打开照相机
     */
    private static void openCamera(Context context) {

        Uri imageUri;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 是否存在sd卡
        if (FileUtil.isExistSDCard()) {

            // 指定照片保存路径（SD卡），hmlphoto.jpg为一个临时文件，每次拍照后这个图片都会被替换

            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                imageUri = Uri.fromFile(new File(filePath));
            } else {
                /**
                 * 7.0 调用系统相机拍照不再允许使用Uri方式，应该替换为FileProvider
                 * 并且这样可以解决MIUI系统上拍照返回size为0的情况
                 */
                imageUri = FileProvider.getUriForFile(context, ProviderUtil.getFileProviderName(context), new File(filePath));
            }
            Log.i("provider", ProviderUtil.getFileProviderName(context));

            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            ((Activity) context).startActivityForResult(intent, IntConstant.CAMERA);
        }
    }
}
