package com.ichsy.hrys.model.user;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ichsy.hrys.R;
import com.ichsy.hrys.common.interfaces.OnReceiveOttoEventInterface;
import com.ichsy.hrys.common.utils.IntentUtils;
import com.ichsy.hrys.common.utils.RequestUtils;
import com.ichsy.hrys.common.utils.SharedPreferencesUtils;
import com.ichsy.hrys.common.utils.UMAnalyticsUtils;
import com.ichsy.hrys.common.utils.http.HttpContext;
import com.ichsy.hrys.common.utils.imageloadutils.ImageLoaderUtils;
import com.ichsy.hrys.common.utils.imageloadutils.ImageStyleType;
import com.ichsy.hrys.common.utils.otto.OttoController;
import com.ichsy.hrys.common.utils.otto.OttoEventEntity;
import com.ichsy.hrys.common.utils.otto.OttoEventType;
import com.ichsy.hrys.common.view.textwacher.CommonTextWatcher;
import com.ichsy.hrys.common.view.textwacher.NameTextWatcher;
import com.ichsy.hrys.config.config.ServiceConfig;
import com.ichsy.hrys.config.constants.IntConstant;
import com.ichsy.hrys.config.constants.StringConstant;
import com.ichsy.hrys.entity.ArtUserInfo;
import com.ichsy.hrys.entity.MarsterFieldEntity;
import com.ichsy.hrys.entity.request.ModifyUserInfoRequestEntity;
import com.ichsy.hrys.model.base.BaseActivity;
import com.ichsy.hrys.model.user.controller.MyUserInfoController;
import com.jakewharton.rxbinding.view.RxView;
import com.squareup.otto.Subscribe;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.functions.Action1;
import zz.mk.utilslibrary.ToastUtils;

/**
 * 修改个人基本资料页
 * 创建者:赵然
 */
public class ModifyUserInfoActivity extends BaseActivity implements OnReceiveOttoEventInterface {

    @BindView(R.id.rl_activitymodifyuserinfo_head)
    RelativeLayout userIconhead;
    @BindView(R.id.iv_modifyuserinfoactivity_icon)
    ImageView userIcon;
    @BindView(R.id.et_modifyuserinfoactivity_nick)
    EditText userNickTV;
    @BindView(R.id.tv_userinfoactivity_sex)
    TextView userSex;
    @BindView(R.id.et_modifyuserinfoactivity_instroduce)
    EditText userInstroduce;
    @BindView(R.id.tv_modifyuserinfoactivity_lables)
    TextView lableText;

    private ArtUserInfo mUserInfo;
    private Dialog iconDialog;


    @Override
    public void loadLayout() {
        setContentView(R.layout.activity_modify_userinfo);
    }

    @Override
    public void logic() {
        showDefaultTittle("编辑资料");
        OttoController.register(this);
        openUMAnlyse("136");

        getRightTittleView().setText("提交");
        RxView.clicks(getRightTittleView()).throttleFirst(1500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                sendModifyRequest();
                UMAnalyticsUtils.onEvent(getContext(), "1360006");
            }
        });

        userNickTV.addTextChangedListener(new NameTextWatcher(userNickTV));
        userInstroduce.addTextChangedListener(new CommonTextWatcher(userInstroduce));

        userNickTV.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    UMAnalyticsUtils.onEvent(getContext(), "1360002");
                }
            }
        });
        userInstroduce.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                UMAnalyticsUtils.onEvent(getContext(), "1360004");
            }
        });

        updateUI();
    }

    /**
     * 更新界面
     */
    private void updateUI() {
        if (mUserInfo == null) {

            mUserInfo = SharedPreferencesUtils.getUserInfo(getContext());
            if (mUserInfo.artMasterField == null) {
                mUserInfo.artMasterField = new ArrayList<>();
            }

            userNickTV.setText(mUserInfo.getUserName());
            userInstroduce.setText(mUserInfo.getUserIntroduction());
        }


        switch (mUserInfo.getSex()) {
            case "dzsd4029100100030002":
                userSex.setText("女");
                break;
            case "dzsd4029100100030001":
                userSex.setText("男");
                break;
            default:
                userSex.setText("未设置");
                break;
        }
        String iconUrl = TextUtils.isEmpty(mUserInfo.getUserIconThumburl()) ? mUserInfo.getUserIconurl() : mUserInfo.getUserIconThumburl();


        ImageLoaderUtils.loadViewImage(getContext(), userIcon, iconUrl, R.drawable.list_placeholder, R.drawable.icon_wode, ImageStyleType.CropCircle);
        setLable();
    }

    /**
     * 处理个人标签展示
     */
    private void setLable() {
        StringBuilder sb = new StringBuilder();
        List<MarsterFieldEntity> artMasterField = mUserInfo.artMasterField;
        int count = artMasterField.size();
        for (int i = 0; i < count; i++) {
            if (i != count - 1) {

                sb.append(artMasterField.get(i).getFieldName() + "/");
            } else {
                sb.append(artMasterField.get(i).getFieldName());
            }
        }
        lableText.setText(sb.toString());

    }

    @Override
    public void loadListener() {
        setClickListeners(R.id.rl_activitymodifyuserinfo_head, R.id.iv_modifyuserinfoactivity_icon, R.id.rl_activitymodifyuserinfo_sexlay, R.id.rl_activitymodifyuserinfo_lableslay);

    }


    @Override
    public void request() {

    }


    @Override
    public void onViewClick(int viewId) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(StringConstant.USERINFO, mUserInfo);
        switch (viewId) {
            case R.id.iv_modifyuserinfoactivity_icon:
            case R.id.rl_activitymodifyuserinfo_head:
                RxPermissions rxPermissions = new RxPermissions(this);
                rxPermissions.request(android.Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            UMAnalyticsUtils.onEvent(getContext(), "1360001");
                            showHeadViewDialog();
                        } else {
                            showMissingPermissionDialog();
                        }
                    }
                });
                break;

            case R.id.rl_activitymodifyuserinfo_sexlay:
                UMAnalyticsUtils.onEvent(getContext(), "1360003");
                IntentUtils.startActivity(getContext(), ModifySexActivity.class, bundle);
                break;
            case R.id.rl_activitymodifyuserinfo_lableslay:
                UMAnalyticsUtils.onEvent(getContext(), "1360005");
                IntentUtils.startActivity(getContext(), LableViewActivity.class, bundle);
                break;
            default:
                break;

        }
    }

    private void showHeadViewDialog() {
        if (iconDialog == null) {

            initHeadViewDialog();
        }
        iconDialog.show();
    }

    /**
     * 获取头像选择弹框
     */
    private void initHeadViewDialog() {
        iconDialog = MyUserInfoController.getHeaderViewDialo(context);
    }

    // 显示缺失权限提示
    public void showMissingPermissionDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(R.string.basic_help);
        builder.setMessage(R.string.basic_string_help_text);

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.basic_quit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton(R.string.basic_settings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startAppSettings();
            }
        });

        builder.setCancelable(false);

        builder.show();
    }

    // 启动应用的设置
    public void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 针对手机拍照做单做处理

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case IntConstant.CAMERA:
                    Uri cameraUri = Uri.fromFile(new File(MyUserInfoController.filePath));
                    if (cameraUri != null) {
                        goZoomImage(cameraUri);
                    }
                    break;

                case IntConstant.PICTURE:
                    //调用本地图片
                    if (data != null) {

                        Uri picUri = data.getData();
                        if (picUri != null) {
                            goZoomImage(picUri);
                        }
                    }
                    break;

                default:
                    break;
            }
        }
    }

    private void goZoomImage(Uri uri) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(StringConstant.USERINFO, mUserInfo);
        bundle.putString(StringConstant.IMAGE_PATH, getRealFilePath(getContext(), uri));
        bundle.putInt(StringConstant.IMAGE_CLIPTYPE, IntConstant.CLIPIMAGETYPE_ICON);
        IntentUtils.startActivity(getContext(), ClipActivity.class, bundle);
    }

    /**
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 发送修改基本资料的接口
     */
    private void sendModifyRequest() {
        if (checkParams() && checkNet()) {
            showLoadingDialog(false);
            ModifyUserInfoRequestEntity requestEntity = new ModifyUserInfoRequestEntity();
            requestEntity.userInfo = mUserInfo;
            requestEntity.perfectType = IntConstant.MODIFYUSERINFO_BASEINFO;
            requestEntity.domainLabel = getLableRequestList();
            RequestUtils.modifyUserInfo(getRequestUnicode(), requestEntity, this);
        }
    }

    /**
     * 获取请求所需的标签集合
     *
     * @return
     */
    private List<String> getLableRequestList() {
        List<String> lables = new ArrayList<>();
        int count = mUserInfo.artMasterField.size();
        for (int i = 0; i < count; i++) {

            lables.add(mUserInfo.artMasterField.get(i).getFieldCode());
        }
        return lables;
    }

    /**
     * 校验请求参数是否正确
     * 1.头像，必填项，点击调用相机/相册，同原有规则
     * 2.昵称，必填，规则同原有
     * 3.性别，必填，规则同原有
     * 4.个人简介，选填，规则同原有
     * 5.标签，必填，点击跳转至标签选择页
     * 6.个人介绍，选填
     * @return
     */
    private boolean checkParams() {

        //头像
        if (TextUtils.isEmpty(mUserInfo.getUserIconurl())) {
            ToastUtils.showShortToast("请上传头像");
            return false;
        }

        /**
         * 昵称
         */
        if (userNickTV.getText().toString().trim().length() == 0) {
            ToastUtils.showShortToast("请输入昵称");
            return false;
        }
        mUserInfo.setUserName(userNickTV.getText().toString().trim());
        /**
         * 性别
         */
        String sexString = userSex.getText().toString().trim();
        if (sexString != null && !"男".equals(sexString) && !"女".equals(sexString)) {
            ToastUtils.showShortToast("请选择性别");
            return false;
        }

        /**
         * 标签  
         */
        if (mUserInfo.artMasterField == null || mUserInfo.artMasterField.size() == 0) {
            ToastUtils.showShortToast("请选择至少一个标签");
            return false;
        }

        mUserInfo.setUserIntroduction(userInstroduce.getText().toString().trim());
        return true;
    }

    @Override
    public void onHttpRequestSuccess(String url, HttpContext httpContext) {
        super.onHttpRequestSuccess(url, httpContext);
        if (checkResponse(httpContext)) {
            if (ServiceConfig.MODIFYUSERINFO.equals(url)) {
                mUserInfo.state = true;
                SharedPreferencesUtils.saveUserMsg(getContext(), mUserInfo);
                finish();
            }
        }
    }

    @Override
    public void onHttpRequestComplete(String url, HttpContext httpContext) {
        super.onHttpRequestComplete(url, httpContext);
        hiddenLoadingDialog();
    }

    @Override
    public void onHttpRequestFailed(String url, HttpContext httpContext, Throwable e) {
        super.onHttpRequestFailed(url, httpContext, e);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OttoController.unregister(this);
    }

    @Subscribe
    @Override
    public void OnReceiveEvent(OttoEventEntity eventEntity) {
        if (eventEntity.getType() == OttoEventType.OTTO_MODIFY_USERINFO) {

            mUserInfo = (ArtUserInfo) eventEntity.getDatas();
            updateUI();
        }
    }
}
