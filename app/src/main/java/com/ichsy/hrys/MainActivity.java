package com.ichsy.hrys;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ichsy.centerbus.CenterEventBus;
import com.ichsy.hrys.common.utils.IntentUtils;
import com.ichsy.hrys.common.utils.LoginUtils;
import com.ichsy.hrys.common.utils.RequestUtils;
import com.ichsy.hrys.common.utils.SharedPreferencesUtils;
import com.ichsy.hrys.common.utils.UMAnalyticsUtils;
import com.ichsy.hrys.common.utils.http.HttpContext;
import com.ichsy.hrys.common.utils.imageloadutils.ImageLoaderUtils;
import com.ichsy.hrys.common.utils.otto.OttoController;
import com.ichsy.hrys.common.view.DividerItemDecoration;
import com.ichsy.hrys.common.view.ScrollingPauseLoadImageRecyclerView;
import com.ichsy.hrys.entity.ArtUserInfo;
import com.ichsy.hrys.entity.response.BaseResponse;
import com.ichsy.hrys.entity.response.GetUserAccountInfoResponseEntity;
import com.ichsy.hrys.model.base.BaseActivity;
import com.ichsy.hrys.model.login.LoginEvent;
import com.ichsy.hrys.model.login.LoginParams;
import com.ichsy.hrys.model.main.MainFragment;
import com.ichsy.hrys.model.setting.SettingAcitivity;
import com.ichsy.hrys.model.user.ModifyUserInfoActivity;
import com.ichsy.hrys.model.user.MyCollectionsActivity;
import com.ichsy.hrys.model.user.MyNotifactionActivity;
import com.ichsy.hrys.model.user.adapter.UserCenterGridAdapter;
import com.ichsy.hrys.model.user.bean.UserCenterItemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import zz.mk.utilslibrary.ToastUtils;

public class MainActivity extends BaseActivity {

    @BindView(R.id.content_frame)
    FrameLayout fragmentLayout;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    @BindView(R.id.leftMeun)
    LinearLayout mLeftMeun;
    @BindView(R.id.leftMeun_head)
    RelativeLayout mLeftMeunHead;

    @BindView(R.id.usercenter_icon)
    ImageView usercenterIcon;
    @BindView(R.id.usercenter_login)
    TextView usercenterLogin;
    @BindView(R.id.usercenter_list)
    ScrollingPauseLoadImageRecyclerView mRecyclerView;

    UserCenterGridAdapter userAdapter;
    List<UserCenterItemBean> itemList;

    private ArtUserInfo mUserInfo;

    String [] text = {"我的收藏", "我的消息", "设置"};
    int [] icon = {R.drawable.icon_collect, R.drawable.icon_news, R.drawable.icon_set};

    MainFragment fragment;

    @Override
    public void loadLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void logic() {
        OttoController.register(this);
        hiddenTitlebar();
        setMainFragment();
        initUserCenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mUserInfo = SharedPreferencesUtils.getUserInfo(getContext());
        updateUI();
        if (LoginUtils.isLogin(getContext())) {
            getData();
        }
    }

    private void updateUI() {
        if (LoginUtils.isLogin(getContext())) {
            if (TextUtils.isEmpty(mUserInfo.getUserName())) {
                usercenterLogin.setText(mUserInfo.getUserPhone());
            } else {
                usercenterLogin.setText(mUserInfo.getUserName());
            }
        } else {
            usercenterLogin.setText("立即登录");
        }
        ImageLoaderUtils.loadCircleWhite(getContext(), usercenterIcon, mUserInfo.getUserIconThumburl());
    }

    private void initUserCenter() {
        itemList = new ArrayList<>();
        for (int i = 0; i < text.length; i++) {
            UserCenterItemBean item = new UserCenterItemBean();
            item.setText(text[i]);
            item.setIcon(icon[i]);
            itemList.add(item);
        }

        userAdapter = new UserCenterGridAdapter(this);
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(userAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, false));
        userAdapter.setNewData(itemList);
    }

    private void setMainFragment() {
        // update the main content by replacing fragments
        fragment = new MainFragment();
//        Bundle args = new Bundle();
//        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
//        fragment.setArguments(args);
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().add(R.id.content_frame, fragment).commit();
        replaceFragment(fragment, fragment.getClass().getSimpleName(), R.id.content_frame);
    }

    @Override
    public void loadListener() {
        fragment.setOnImageViewClick(new MainFragment.onImageViewClick() {
            @Override
            public void OnClick(View view) {
                if (!drawerlayout.isDrawerOpen(mLeftMeun)) {
                    drawerlayout.openDrawer(mLeftMeun);
                }
            }
        });
        userAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LoginParams loginParams = new LoginParams(getContext(), LoginEvent.LOGIN);

                if (position == 2) {
                    IntentUtils.startActivity(getContext(), SettingAcitivity.class);
                    return;
                }
                if (LoginUtils.isLogin(getContext())) {
                    if (position == 1) {
                        IntentUtils.startActivity(getContext(), MyNotifactionActivity.class);
                        return;
                    }
                    if (position == 0) {
                        IntentUtils.startActivity(getContext(), MyCollectionsActivity.class);
                        return;
                    }
                } else {
                    CenterEventBus.getInstance().postTask(loginParams);
                }
            }
        });
    }

    private void getData(){
        RequestUtils.getUserAccountInfo(getRequestUnicode(),this);
    }

    @Override
    public void request() {

    }

    @Override
    public void onViewClick(int viewId) {

    }

    @Override
    public void onHttpRequestBegin(String url) {
        super.onHttpRequestBegin(url);
    }

    @Override
    public void onHttpRequestSuccess(String url, HttpContext httpContext) {
        super.onHttpRequestSuccess(url, httpContext);
        if (httpContext.getResponseObject() != null && ((BaseResponse) httpContext.getResponseObject()).status == 1) {
            GetUserAccountInfoResponseEntity responseEntity = httpContext.getResponseObject();
            ArtUserInfo responseUserInfo = responseEntity.userInfo;
            if (responseUserInfo != null){
                responseUserInfo.setQrCodeFlowUrl(responseEntity.getQrCodeFlowUrl());
                responseUserInfo.artMasterField = responseEntity.getArtMasterField();
                //存储用户基本信息和标签信息
                updateLocalUserMessage(responseUserInfo);
                updateUI();
                /**
                 * 存储用户得形象信息
                 */
                SharedPreferencesUtils.saveImageUserInfo(getContext(),responseEntity.getArtPersonalImage());
            }
        }
    }

    @Override
    public void onHttpRequestFailed(String url, HttpContext httpContext, Throwable e) {
        super.onHttpRequestFailed(url, httpContext, e);
    }

    @Override
    public void onHttpRequestComplete(String url, HttpContext httpContext) {
        super.onHttpRequestComplete(url, httpContext);
    }

    /**
     * 保存用户信息
     * @param userInfo
     */
    private void updateLocalUserMessage(ArtUserInfo userInfo){
        mUserInfo = SharedPreferencesUtils.getUserInfo(getContext());
        mUserInfo.setUserIconurl(userInfo.getUserIconurl());
        mUserInfo.setUserIconThumburl(userInfo.getUserIconThumburl());
        mUserInfo.setUserName(userInfo.getUserName());
        mUserInfo.setAttentionNum(userInfo.getAttentionNum());
        mUserInfo.setFansNum(userInfo.getFansNum());
        mUserInfo.setPostNum(userInfo.getPostNum());
        mUserInfo.setUserIntroduction(userInfo.getUserIntroduction());
        mUserInfo.setQrCodeFlowUrl(userInfo.getQrCodeFlowUrl());
        mUserInfo.setBjIconurl(userInfo.getBjIconurl());
        mUserInfo.setUserType(userInfo.getUserType());
        mUserInfo.state = userInfo.state;
        mUserInfo.artMasterField = userInfo.artMasterField;
        mUserInfo.isLogin = true;
        SharedPreferencesUtils.saveUserMsg(getContext(),mUserInfo);
    }

    @OnClick({R.id.leftMeun_head})
    public void onViewClicked(View view) {
        LoginParams loginParams = new LoginParams(getContext(), LoginEvent.LOGIN);
        switch (view.getId()) {
            case R.id.leftMeun_head:
                if(LoginUtils.isLogin(getContext())){
                    IntentUtils.startActivity(getContext(), ModifyUserInfoActivity.class);
                }else{
                    CenterEventBus.getInstance().postTask(loginParams);
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private long exitTime = 0;

    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtils.showShortToast("再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            UMAnalyticsUtils.onKillProcess(this);
            android.os.Process.killProcess(android.os.Process.myPid());
            finish();
        }
    }
}
