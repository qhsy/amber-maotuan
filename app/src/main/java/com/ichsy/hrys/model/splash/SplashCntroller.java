package com.ichsy.hrys.model.splash;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;

import com.ichsy.hrys.MainActivity;
import com.ichsy.hrys.common.utils.RequestUtils;
import com.ichsy.hrys.common.utils.SharedPreferencesUtils;
import com.ichsy.hrys.common.utils.download.DownloadUtils;
import com.ichsy.hrys.common.utils.http.RequestListener;
import com.ichsy.hrys.common.utils.http.retrofit.updownload.UploadListener;
import com.ichsy.hrys.common.view.UpdateProgressDialogView;
import com.ichsy.hrys.common.view.dialog.UpdateDialog;
import com.ichsy.hrys.config.constants.StringConstant;
import com.ichsy.hrys.entity.AppConfigEntity;
import com.ichsy.hrys.entity.request.CheckUpdateRequestEntity;
import com.ichsy.hrys.entity.response.CheckUpdateResponseEntity;

import java.io.File;

import zz.mk.utilslibrary.LogUtil;
import zz.mk.utilslibrary.ToastUtils;
import zz.mk.utilslibrary.system.AppUtils;

//import com.ichsy.whds.common.utils.HotFixUtils;

/**
 * 功能：splash页面的控制器
 * ＊创建者：赵然 on 16/5/29 23:20
 * ＊
 */
public class SplashCntroller {
    /**
     * 处理检查更新的结果
     *
     * @param context
     * @param entity  更新相关数据
     */
    public static boolean dealUpdate(final Activity context, final CheckUpdateResponseEntity entity, final UpdateProgressDialogView pb, final boolean isLoadMainUi) {
//        entity.setUpgradeType("");
        SharedPreferencesUtils.saveAppUpdate(context, entity);
        boolean isInterrupt = false;
        switch (entity.getUpgradeType()) {
            case StringConstant.UPDATE_NONEED:
                //不升级
            case StringConstant.UPDATE_SILENCE:
            case StringConstant.UPDATE_WARN:
                //静默升级
                if (TextUtils.isEmpty(entity.getAppUrl())) {
                    return false;
                }
                if (StringConstant.UPDATE_SILENCE.equals(entity.getUpgradeType())) {
                    if (isLoadMainUi) {
                        return false;
                    }
                }
                isInterrupt = true;
                //提示升级
                final UpdateDialog warnDialog = new UpdateDialog(context, UpdateDialog.CommonDialogViewType.TWO);
                warnDialog.getLeftButton().setText("取消");
                warnDialog.setCancelable(false);
                warnDialog.setContent(entity.getUpgradeContent());
                warnDialog.getLeftButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferencesUtils.saveAppUpdate(context, entity);
                        warnDialog.dismiss();
                        if (isLoadMainUi) {

                            SplashCntroller.loadMainUi(context);
                        }
                    }
                });
                warnDialog.getRightButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        warnDialog.dismiss();
                        ToastUtils.showShortToast("已进入后台下载");
                        DownloadUtils.startDownLoad(context, entity.getAppUrl(), new UploadListener() {
                            @Override
                            public void onStart() {

                            }

                            @Override
                            public void onProgress(long currentSize, long totalSize, String speed) {

                            }

                            @Override
                            public void onSuccess(String imgUrl) {
                                install(context, imgUrl);
                            }

                            @Override
                            public void onFailure(Exception e) {
                                resetUpdate(context);
                                if (isLoadMainUi) {

                                    loadMainUi(context);
                                }
                            }

                        });
                    }
                });
                break;
            case StringConstant.UPDATE_MUST:
                if (TextUtils.isEmpty(entity.getAppUrl())) {
                    return false;
                }
                isInterrupt = true;
                //强生
                //提示升级
                final UpdateDialog mustDialog = new UpdateDialog(context, UpdateDialog.CommonDialogViewType.ONE);
                mustDialog.setContent(entity.getUpgradeContent());
                mustDialog.setCancelable(false);
                mustDialog.getRightButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mustDialog.dismiss();
                        DownloadUtils.startDownLoad(context, entity.getAppUrl(), new UploadListener() {
                            @Override
                            public void onStart() {
                                pb.setVisibility(View.VISIBLE);
                                pb.setProgress(0);
                            }

                            @Override
                            public void onProgress(long currentSize, long totalSize, String speed) {
                                int progress = (int) (currentSize * 100.0f / totalSize * 1.0f);
                                LogUtil.zLog().e("progress:" + progress);
                                pb.setProgress(progress);
                            }

                            @Override
                            public void onSuccess(String imgUrl) {
                                install(context, imgUrl);
                            }

                            @Override
                            public void onFailure(Exception e) {
                                resetUpdate(context);
                                if (isLoadMainUi) {

                                    loadMainUi(context);
                                }
                            }

                        });
                    }
                });
                break;
            default:
                break;

        }
        return isInterrupt;

    }


    public static void resetUpdate(Context context) {
        CheckUpdateResponseEntity responseObject = new CheckUpdateResponseEntity();
        responseObject.setUpgradeType(StringConstant.UPDATE_FAILURE);
        SharedPreferencesUtils.saveAppUpdate(context, responseObject);
    }

    /**
     * 页面跳转, 第一版这版不要引导
     */
    public static void loadMainUi(Activity context) {

        boolean isFirstLoadApp = SharedPreferencesUtils.isFirstLoadApp(context);
//        if (isFirstLoadApp) {
//            SharedPreferencesUtils.setFirstLoadApp(context);
//            Intent intent = new Intent(context, WelcomeActivity.class);
//            context.startActivity(intent);
//            context.finish();
//        } else {
            //直接进入首页
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
            context.finish();
//        }
    }

    /**
     * 安装 App
     *
     * @param context
     * @param filePath
     * @return 是否存在该 Apk
     */
    public static boolean install(Activity context, String filePath) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        File file = new File(filePath);
        if (file != null && file.length() > 0 && file.exists() && file.isFile()) {
            i.setDataAndType(Uri.parse("file://" + filePath), "application/vnd.android.package-archive");
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            android.os.Process.killProcess(android.os.Process.myPid());
            return true;
        }
        return false;
    }

    /**
     * 下载视频
     *
     * @param entity
     * @return
     */
    public static void loadMovie(final Context context, final AppConfigEntity entity) {
        if (TextUtils.isEmpty(entity.getLocalUrl())) return;
        DownloadUtils.startDownLoad(context, entity.getConfigContent(), new UploadListener() {
            @Override
            public void onStart() {
                LogUtil.zLog().e("onDownLoadStart");
            }

            @Override
            public void onProgress(long currentSize, long totalSize, String speed) {

            }

            @Override
            public void onSuccess(String imgUrl) {
                LogUtil.zLog().e("MOVIE success:" + imgUrl);
                entity.setLocalUrl(imgUrl);
                SharedPreferencesUtils.setLoginMovieInfo(context, entity);
            }

            @Override
            public void onFailure(Exception e) {

            }


        });
    }

    /**
     * 下载补丁
     *
     * @param entity
     * @return
     */
    public static void loadPatch(final Context context, AppConfigEntity entity) {
        if (TextUtils.isEmpty(entity.getLocalUrl())) return;
        DownloadUtils.startDownLoad(context, entity.getConfigUrl(), new UploadListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onProgress(long currentSize, long totalSize, String speed) {

            }

            @Override
            public void onSuccess(String imgUrl) {
                AppConfigEntity patchInfo = SharedPreferencesUtils.getPatchInfo(context);
                patchInfo.setLocalUrl(imgUrl);
                SharedPreferencesUtils.setPatchInfo(context, patchInfo);
//                HotFixUtils.getInstance().loadPatch(imgUrl);
                LogUtil.zLog().e("patch success:" + imgUrl);
            }

            @Override
            public void onFailure(Exception e) {

            }

        });
    }

    /**
     * 检查更新的请求
     *
     * @param context
     * @param listener
     */
    public static void checkUpdate(Context context, String requestUnicode, RequestListener listener) {
        CheckUpdateRequestEntity updateRequestEntity = new CheckUpdateRequestEntity();
        updateRequestEntity.versionNo = AppUtils.getVerName(context);
        updateRequestEntity.channel = AppUtils.getChannel(context);
        RequestUtils.checkUpdate(requestUnicode, updateRequestEntity, listener);
    }
}
