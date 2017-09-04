package com.ichsy.hrys.model.user;

import android.view.View;
import android.widget.ImageView;

import com.ichsy.hrys.R;
import com.ichsy.hrys.common.utils.otto.OttoController;
import com.ichsy.hrys.common.utils.otto.OttoEventType;
import com.ichsy.hrys.config.constants.StringConstant;
import com.ichsy.hrys.entity.ArtUserInfo;
import com.ichsy.hrys.model.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 功能：修改性别的界面
 * ＊创建者：赵然 on 16/5/31 17:50
 * ＊
 */
public class ModifySexActivity extends BaseActivity {


    private String selectedType = "";

    @BindView(R.id.iv_activitysex_manchecked)
    ImageView manCheckedView;
    @BindView(R.id.iv_activitysex_womenchecked)
    ImageView womanCheckedView;
    private ArtUserInfo userInfo;

    @OnClick(R.id.rl_activity_sex_manlay)
    void manLayClick() {

        selectedType = StringConstant.SEX_MAN;
        manCheckedView.setVisibility(View.VISIBLE);
        womanCheckedView.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.rl_activity_sex_womanlay)
    void womanLayClick() {

        selectedType = StringConstant.SEX_WOMAN;
        manCheckedView.setVisibility(View.INVISIBLE);
        womanCheckedView.setVisibility(View.VISIBLE);
    }


    @Override
    public void loadLayout() {
        setContentView(R.layout.activity_sexselect);


    }

    @Override
    public void logic() {
        showDefaultTittle("性别");
        userInfo = (ArtUserInfo) getIntent().getSerializableExtra(StringConstant.USERINFO);

        selectedType = userInfo.getSex();
        if (selectedType.equals(StringConstant.SEX_MAN)) {

            manCheckedView.setVisibility(View.VISIBLE);
        } else if (selectedType.equals(StringConstant.SEX_WOMAN)) {
            womanCheckedView.setVisibility(View.VISIBLE);
        }


        getLeftTittleView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void saveUserInfo() {
//        ArtUserInfo userInfo = SharedPreferencesUtils.getUserInfo(getContext());
//        userInfo.setSex(selectedType);
//        SharedPreferencesUtils.saveUserMsg(context,userInfo);
        userInfo.setSex(selectedType);
        OttoController.post(userInfo, OttoEventType.OTTO_MODIFY_USERINFO, "");
    }

    @Override
    public void loadListener() {

    }

    @Override
    public void request() {

    }

    @Override
    public void onViewClick(int viewId) {

    }


    @Override
    protected void onDestroy() {
        saveUserInfo();
        super.onDestroy();
    }
}
