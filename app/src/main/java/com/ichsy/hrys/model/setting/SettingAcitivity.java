package com.ichsy.hrys.model.setting;

import android.app.NotificationManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ichsy.hrys.R;
import com.ichsy.hrys.common.utils.IntentUtils;
import com.ichsy.hrys.common.utils.LoginUtils;
import com.ichsy.hrys.common.utils.SharedPreferencesUtils;
import com.ichsy.hrys.common.utils.http.HttpContext;
import com.ichsy.hrys.common.view.UpdateProgressDialogView;
import com.ichsy.hrys.common.view.dialog.CommonDialog;
import com.ichsy.hrys.config.config.ServiceConfig;
import com.ichsy.hrys.config.constants.StringConstant;
import com.ichsy.hrys.entity.response.CheckUpdateResponseEntity;
import com.ichsy.hrys.model.base.BaseActivity;
import com.ichsy.hrys.model.splash.SplashCntroller;

import java.io.File;

import butterknife.BindView;
import zz.mk.utilslibrary.LogUtil;
import zz.mk.utilslibrary.ToastUtils;


/**
 * 设置界面
 * 创建者:赵然
 */
public class SettingAcitivity extends BaseActivity {

    @BindView(R.id.tv_settingactivity_loginout)
    TextView loginOut;
    @BindView(R.id.sc_buttontoggle)
    SwitchCompat switchCompat;
    @BindView(R.id.rl_settingactivitiy_pushlay)
    RelativeLayout pushlay;

    private boolean isReceivePush;
    /**
     * 发送修改推送状态的请求
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            sendModifyPushRequest();
        }
    };

    @Override
    public void loadLayout() {
        setContentView(R.layout.activity_setting);
    }

    @Override
    public void logic() {

        showDefaultTittle("设置");
        setBackgroundColor(getResources().getColor(R.color.color_blue));
        getCenterTittleView().setTextColor(getResources().getColor(R.color.color_white));
        setLeftDrawable(R.drawable.icon_back_white);

        if (LoginUtils.isLogin(getContext())) {
            pushlay.setVisibility(View.GONE);
            loginOut.setVisibility(View.VISIBLE);
            isReceivePush = SharedPreferencesUtils.getUserPushStatus(getContext());
            switchCompat.setChecked(isReceivePush);

        } else {
            pushlay.setVisibility(View.GONE);
            loginOut.setVisibility(View.GONE);
        }
    }

    @Override
    public void loadListener() {

        setClickListeners(
                R.id.tv_settingactivity_clearcache
                , R.id.tv_settingactivity_checkupdate
                , R.id.tv_settingactivity_about
                , R.id.tv_settingactivity_loginout
                , R.id.tv_settingactivity_advice
        );
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LogUtil.zLog().e("选中状态：" + isChecked);
                if (isReceivePush != isChecked) {
                    isReceivePush = isChecked;
                    handler.removeMessages(0);
                    handler.sendEmptyMessageDelayed(0, 400);
                }
            }
        });
    }

    @Override
    public void request() {

    }

    @Override
    public void onViewClick(int viewId) {
        switch (viewId) {
            case R.id.tv_settingactivity_clearcache:
                clearCache();
                break;
            case R.id.tv_settingactivity_checkupdate:
                checkUpdate();
                break;
            case R.id.tv_settingactivity_about:
                // 判断跳转原生或是RN界面

            IntentUtils.startActivity(getContext(), AboutActivity.class);


                break;
            case R.id.tv_settingactivity_loginout:
                final CommonDialog commonDialog = new CommonDialog(getContext(), CommonDialog.CommonDialogViewType.TWO);
                commonDialog.setTittleAndContent("退出登录", "是否退出当前账号？");
                commonDialog.getLeftButton().setText("取消");
                commonDialog.getRightButton().setText("确定退出");
                commonDialog.setBackground(R.drawable.shape_btn_leftrightbottomroundcorner_fullbwhite);
                commonDialog.getRightButton().setTextColor(getResources().getColor(R.color.color_text_red));
                commonDialog.getRightButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //清除本地信息--用户信息  推送信息
                        SharedPreferencesUtils.clearSp(getContext());
                        //清除 通知栏所有通知
                        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
                        manager.cancelAll();
                        // 发送退出登录的请求
//                        RequestUtils.sendLoginOutRequest(getContext(), getRequestUnicode());

                        commonDialog.dismiss();
                        loginOut.setVisibility(View.GONE);
                        ToastUtils.showShortToast("退出登录成功");
                        finish();

                    }
                });
                break;
            case R.id.tv_settingactivity_advice:
//                IntentUtils.startActivity(getContext(), AdviceActivity.class);
                break;
            default:
                break;
        }
    }

    //确定清空缓存
    private void clearCache() {

        final CommonDialog dialog = new CommonDialog(getContext(), CommonDialog.CommonDialogViewType.TWO).setTittleAndContent("清除缓存", "确定要清除缓存么");
        dialog.getLeftButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFile(getCacheDir());
                Glide.get(getContext()).clearMemory();
                ToastUtils.showShortToast("缓存已清理");
                dialog.dismiss();
            }
        });

    }


    /**
     * 删除文件夹所有内容
     */
    public void deleteFile(File file) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                file.delete(); // delete()方法 你应该知道 是删除的意思;
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    this.deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
                }
            }
            file.delete();
        }
    }

    /**
     * 检查更新
     */
    private void checkUpdate() {
        CheckUpdateResponseEntity appUpdate = SharedPreferencesUtils.getAppUpdate(getContext());
        switch (appUpdate.getUpgradeType()) {
            case StringConstant.UPDATE_FAILURE:
                //失败
                if (checkNet()) {
                    showLoadingDialog(true);
                    SplashCntroller.checkUpdate(getContext(), getRequestUnicode(), this);
                }
                break;
            case StringConstant.UPDATE_WARN:
            case StringConstant.UPDATE_SILENCE:
                //不强升  静默
                SplashCntroller.dealUpdate(getContext(), appUpdate, (UpdateProgressDialogView) findViewById(R.id.updv_setting_pb), false);
                break;

            default:
                ToastUtils.showShortToast("当前已是最新版本");
                break;
        }
    }

    /**
     * 发送修改推送状态的请求
     */
//    private void sendModifyPushRequest() {
//        if (checkNet()) {
//            showLoadingDialog(false);
//            RequestUtils.modifyUserPushStatus(getContext(), getRequestUnicode(), isReceivePush, this);
//        } else {
//            isReceivePush = !isReceivePush;
//            switchCompat.setChecked(isReceivePush);
//        }
//    }

    @Override
    public void onHttpRequestSuccess(String url, HttpContext httpContext) {
        super.onHttpRequestSuccess(url, httpContext);

        if (checkResponse(httpContext)) {
            if (ServiceConfig.CHECKUPDATE.equals(url)) {
                SplashCntroller.dealUpdate(getContext()
                        , (CheckUpdateResponseEntity) httpContext.getResponseObject()
                        , (UpdateProgressDialogView) findViewById(R.id.updv_setting_pb)
                        , false);
            }
        }
//        else if (ServiceConfig.MODIFY_USERPUSH.equals(url)) {
//            LogUtil.zLog().e("modify success");
//            SharedPreferencesUtils.saveUserPushStatus(getContext(), isReceivePush);
//        }

    }

    @Override
    public void onHttpRequestFailed(String url, HttpContext httpContext, Throwable e) {
        super.onHttpRequestFailed(url, httpContext, e);
        LogUtil.zLog().e("failed");
        if (ServiceConfig.CHECKUPDATE.equals(url)) {

            CheckUpdateResponseEntity responseObject = new CheckUpdateResponseEntity();
            responseObject.setUpgradeType(StringConstant.UPDATE_FAILURE);
            SharedPreferencesUtils.saveAppUpdate(getContext(), responseObject);
            ToastUtils.showShortToast("当前已是最新版本");
        }
    }

    @Override
    public void onHttpRequestComplete(String url, HttpContext httpContext) {
        super.onHttpRequestComplete(url, httpContext);
        hiddenLoadingDialog();
    }


}
