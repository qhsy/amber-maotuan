package com.ichsy.hrys.model.user;

import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ichsy.hrys.R;
import com.ichsy.hrys.common.utils.RequestUtils;
import com.ichsy.hrys.common.utils.UMAnalyticsUtils;
import com.ichsy.hrys.common.utils.http.HttpContext;
import com.ichsy.hrys.common.utils.otto.OttoController;
import com.ichsy.hrys.common.utils.otto.OttoEventType;
import com.ichsy.hrys.config.config.ServiceConfig;
import com.ichsy.hrys.config.constants.StringConstant;
import com.ichsy.hrys.entity.ArtMasterFieldVo;
import com.ichsy.hrys.entity.ArtUserInfo;
import com.ichsy.hrys.entity.MarsterFieldEntity;
import com.ichsy.hrys.entity.request.OnlyPageRequestEntity;
import com.ichsy.hrys.entity.response.GetDomainLableListResponseEntity;
import com.ichsy.hrys.model.base.BaseActivity;
import com.ichsy.hrys.model.user.adapter.AllLableGridAdapter;
import com.ichsy.hrys.model.user.adapter.LableGridAdapter;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import zz.mk.utilslibrary.ToastUtils;


/**
 * 个人标签选择
 */
public class LableViewActivity extends BaseActivity {

    @BindView(R.id.tv_lableactivity_selectedlabletitle)
    TextView seletedLablesTittle;
    @BindView(R.id.tlv_lableacitivity_selectedlableview)
    GridView seletedLableView;
    @BindView(R.id.ll_lableactivity_alllables)
    LinearLayout linearLayoutLables;

    @BindView(R.id.tv_activitylables_tips)
    TextView tips;

    /**
     * 适配器
     */
    private LableGridAdapter selectedLablesAdapter;

    /**
     * 全部的标签
     */
    private List<ArtMasterFieldVo> resultDatas;
    /**
     * 暂时用于处理取消已选择标签时，遍历使用方便
     */
    private List<MarsterFieldEntity> allLableList = new ArrayList<>();

    /**
     * 已选中的标签集合
     */
    private List<MarsterFieldEntity> selectedLablesDatas;
    /**
     * 用户信息
     */
    private ArtUserInfo userInfo;
    private final int CAN_SELECTED_COUNT = 6;

    @Override
    public void loadLayout() {
        setContentView(R.layout.activity_lable_view);
    }

    @Override
    public void logic() {
        showDefaultTittle("标签");
        openUMAnlyse("137");
        getRightTittleView().setText("完成");
        RxView.clicks(getRightTittleView()).throttleFirst(1500, TimeUnit.MILLISECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (selectedLablesDatas.size() == 0) {
                    ToastUtils.showShortToast("请至少选择一个标签");
                } else {
                    userInfo.artMasterField = selectedLablesDatas;
                    OttoController.post(userInfo, OttoEventType.OTTO_MODIFY_USERINFO, "");
                    finish();
                }
            }
        });
        if (getIntent() != null) {
            userInfo = (ArtUserInfo) getIntent().getSerializableExtra(StringConstant.USERINFO);
        }
        selectedLablesDatas = userInfo.artMasterField;

        if (selectedLablesDatas == null) {
            selectedLablesDatas = new ArrayList<>();

        }
        if (selectedLablesDatas.size() == 0) {
            tips.setVisibility(View.VISIBLE);
        } else {
            tips.setVisibility(View.INVISIBLE);
        }
        selectedLablesAdapter = new LableGridAdapter(getContext(), selectedLablesDatas);
        seletedLablesTittle.setText("已选择 (" + selectedLablesDatas.size() + "/" + CAN_SELECTED_COUNT + ")");
        seletedLableView.setAdapter(selectedLablesAdapter);
        for (int i = 0; i < selectedLablesDatas.size(); i++) {
            selectedLablesDatas.get(i).setChecked(true);
        }
    }


    @Override
    public void loadListener() {
        seletedLableView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updateLableUI(selectedLablesAdapter.getDatas().get(position).getFieldCode(), selectedLablesAdapter, allLableList);
                measureAllLables();
                UMAnalyticsUtils.onEvent(getContext(), "1370001");
            }
        });
    }

    /**
     * 更新标签
     *
     * @param selectedCode
     */
    private void updateLableUI(String selectedCode, BaseAdapter adapter, List<MarsterFieldEntity> selectList) {

        int size = selectList.size();
        for (int i = 0; i < size; i++) {
            MarsterFieldEntity marsterFieldEntity = selectList.get(i);
            if (marsterFieldEntity.getFieldCode().equals(selectedCode)) {
                boolean isChecked = marsterFieldEntity.isChecked();
                if (!isChecked) {
                    if (selectedLablesAdapter.getDatas().size() == CAN_SELECTED_COUNT) {
                        ToastUtils.showShortToast("您最多只能选择6个标签哦");
                        return;
                    }
                }
                marsterFieldEntity.setChecked(!isChecked);
                if (marsterFieldEntity.isChecked()) {
                    selectedLablesDatas.add(marsterFieldEntity);
                } else {
                    //查找是否在选中标签中含有此标签---更新选中标签中的数据
                    for (int j = 0; j < selectedLablesDatas.size(); j++) {
                        if (selectedCode.equals(selectedLablesDatas.get(j).getFieldCode())) {
                            selectedLablesDatas.remove(j);
                            break;
                        }
                    }
                }
                break;
            }
        }

        seletedLablesTittle.setText("已选择 (" + selectedLablesDatas.size() + "/" + CAN_SELECTED_COUNT + ")");
        if (selectedLablesDatas.size() > 0) {
            tips.setVisibility(View.INVISIBLE);
        } else {
            tips.setVisibility(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();
        selectedLablesAdapter.notify(selectedLablesDatas);
    }

    @Override
    public void request() {
        requestDomainLables();
    }

    @Override
    public void onViewClick(int viewId) {

    }

    /**
     * 获取领域标签
     */
    private void requestDomainLables() {
        if (checkNet()) {
            showLoadingDialog(true);
            OnlyPageRequestEntity requestEntity = new OnlyPageRequestEntity();
            requestEntity.pageOption.itemCount = 100;
            requestEntity.pageOption.pageNum = 0;
            RequestUtils.getDomainLabls(getRequestUnicode(), requestEntity, this);
        }
    }


    @Override
    public void onHttpRequestSuccess(String url, HttpContext httpContext) {
        super.onHttpRequestSuccess(url, httpContext);
        if (checkResponse(httpContext)) {
            if (ServiceConfig.GETDOMAINLABLE.equals(url)) {
                GetDomainLableListResponseEntity responseEntity = httpContext.getResponseObject();
                for (ArtMasterFieldVo vo : responseEntity.list) {
                    allLableList.addAll(vo.artMasterField);
                }
                resultDatas = responseEntity.list;
                measureAllLables();
            }
        }
    }

    @Override
    public void onHttpRequestComplete(String url, HttpContext httpContext) {
        super.onHttpRequestComplete(url, httpContext);
        hiddenLoadingDialog();
    }

    /**
     * 绘制所有的可选择标签
     */
    private void measureAllLables() {
        linearLayoutLables.removeAllViews();
        for (ArtMasterFieldVo vo : resultDatas) {
            if (vo.artMasterField != null && vo.artMasterField.size() > 0) {
                View parentView = View.inflate(getContext(), R.layout.item_lable_tittle, null);
                ((TextView) parentView.findViewById(R.id.tv_lableactivity_alllabletitle)).setText(vo.labelName);
                linearLayoutLables.addView(parentView);

                View childLineView = View.inflate(getContext(), R.layout.item_child_lables, null);
                GridView childGridView = (GridView) childLineView.findViewById(R.id.gridview_child_lable);

                final List<MarsterFieldEntity> changeStatusMarsterField = getChangeStatusMarsterField(vo.artMasterField);
                final AllLableGridAdapter allLablesAdapter = new AllLableGridAdapter(getContext(), changeStatusMarsterField);
                childGridView.setAdapter(allLablesAdapter);
                linearLayoutLables.addView(childLineView);

                childGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        updateLableUI(allLablesAdapter.getDatas().get(position).getFieldCode(), allLablesAdapter, changeStatusMarsterField);
                        UMAnalyticsUtils.onEvent(getContext(), "1370002");
                    }
                });
            }
        }
    }


    /**
     * 获取到全部标签后更新标签状态 刷新全部标签界面
     */
    private List<MarsterFieldEntity> getChangeStatusMarsterField(List<MarsterFieldEntity> lablesDatas) {
        int selectLableCount = selectedLablesDatas.size();
        for (int i = 0; i < selectLableCount; i++) {
            MarsterFieldEntity selectLableEntity = selectedLablesDatas.get(i);
            int allLableCount = lablesDatas.size();
            for (int j = 0; j < allLableCount; j++) {
                MarsterFieldEntity allLableEntity = lablesDatas.get(j);
                if (selectLableEntity.getFieldCode().equals(allLableEntity.getFieldCode())) {
                    lablesDatas.get(j).setChecked(true);
                    break;
                } else {
                    continue;
                }
            }
        }
        return lablesDatas;
    }

}
