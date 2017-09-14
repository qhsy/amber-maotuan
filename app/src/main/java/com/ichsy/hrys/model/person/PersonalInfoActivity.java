package com.ichsy.hrys.model.person;

import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ichsy.hrys.R;
import com.ichsy.hrys.common.utils.RequestUtils;
import com.ichsy.hrys.common.utils.http.HttpContext;
import com.ichsy.hrys.common.view.refreshview.RefreshLay;
import com.ichsy.hrys.config.constants.StringConstant;
import com.ichsy.hrys.entity.ArtUserInfo;
import com.ichsy.hrys.entity.request.ArtPersonalHomepageInput;
import com.ichsy.hrys.entity.response.ArtGetPersonalInfoResult;
import com.ichsy.hrys.model.base.BaseActivity;
import com.ichsy.hrys.model.main.adapters.HomeAdapter;
import com.ichsy.hrys.model.main.controller.TaskController;
import com.ichsy.hrys.model.person.view.PersonalInfoHeadView;

import butterknife.BindView;
import zz.mk.utilslibrary.ScreenUtil;

/**
 * 个人主页
 */

public class PersonalInfoActivity extends BaseActivity implements RefreshLay.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.srl_personal_info)
    RefreshLay mRefreshLay;
    @BindView(R.id.rl_personal_info)
    RecyclerView mRecyclerView;
    @BindView(R.id.item_personal_info_head)
    PersonalInfoHeadView mPersonalInfoHeadView;
    @BindView(R.id.tv_personal_top_name)
    TextView mTvPersonalTopName;
    @BindView(R.id.btn_psersonal_info_contact)
    Button mBtnContact;

    private HomeAdapter mAdapter;
    private ArtPersonalHomepageInput mRequestParams;
    private CollapsingToolbarLayoutState state;
    private ArtUserInfo mUserInfo;

    @Override
    public void loadLayout() {
        setContentView(R.layout.activity_personal_info);
    }

    @Override
    public void logic() {
        openUMAnlyse("113");
        hiddenTitlebar();
        setAppBarlayout();
        mAdapter = new HomeAdapter(this);
        mAdapter.setCanClick(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

        mRequestParams = new ArtPersonalHomepageInput();
        mRequestParams.pageOption.pageNum = 0;
        mRequestParams.pageOption.itemCount = 5;
        mRequestParams.deviceScreenWidth = ScreenUtil.getScreenWidth(getContext());
        mRequestParams.userCode = getIntent().getStringExtra(StringConstant.USERID);
        addEmptyView();
    }

    @Override
    public void loadListener() {
        mRefreshLay.setRefreshListener(this);
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TaskController.openVideoDetail(context, mAdapter.getData().get(position));
            }
        });
        setClickListeners(R.id.iv_back, R.id.btn_psersonal_info_contact);
    }

    @Override
    public void request() {
        if (checkNet()) {
            RequestUtils.getPersonalInfo(getRequestUnicode(), mRequestParams, this);
        } else {
            mRefreshLay.refreshComplete();
        }
    }

    @Override
    public void onHttpRequestBegin(String url) {
        super.onHttpRequestBegin(url);
        showLoadingDialog(true);
    }

    @Override
    public void onHttpRequestSuccess(String url, HttpContext httpContext) {
        super.onHttpRequestSuccess(url, httpContext);
        ArtGetPersonalInfoResult result = httpContext.getResponseObject();
        if (checkResponse(httpContext)) {
            if (mRequestParams.pageOption.pageNum == 0) {
                mUserInfo = result.userInfo;
                mAdapter.getData().clear();
                updateFirstPageUI(result);
            } else {
                if (result.videoContentList != null && result.videoContentList.size() > 0) {
                    mAdapter.addData(result.videoContentList);
                }
                if (result.pageresults.isMore) {
                    mAdapter.loadMoreComplete();
                } else {
                    mAdapter.loadMoreEnd();
                }
            }
        }
    }

    private void addEmptyView() {
        View view = View.inflate(getContext(), R.layout.item_my_price_empty, null);
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.nodata_layout);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) layout.getLayoutParams();
        lp.setMargins(0, ScreenUtil.dip2px(getContext(), 100), 0, 0);
        layout.setLayoutParams(lp);
        ImageView imageView = (ImageView) view.findViewById(R.id.item_nodata_iv);
        imageView.setImageResource(R.drawable.nodata_upvideo);
        mAdapter.setEmptyView(view);
    }

    /**
     * 更新头部信息
     */
    private void updateHeadView(ArtGetPersonalInfoResult result) {
        mPersonalInfoHeadView.updateUI(result.userInfo, result.attentionStatus);
    }

    /**
     * 更新第一页的数据UI
     */
    private void updateFirstPageUI(ArtGetPersonalInfoResult result) {
        //头部信息
        updateHeadView(result);

        if (result.videoContentList != null && result.videoContentList.size() > 0) {
            mAdapter.setNewData(result.videoContentList);
        }
        mAdapter.notifyDataSetChanged();
        //如果点击进入自己的个人主页不显示联系TA按钮
//        ArtUserInfo currentLoginUserInfo = SharedPreferencesUtils.getUserInfo(context);
//        mBtnContact.setVisibility(result.userInfo.getUserCode().equals(currentLoginUserInfo.getUserCode()) ? View.GONE : View.VISIBLE);
    }


    @Override
    public void onHttpRequestComplete(String url, HttpContext httpContext) {
        super.onHttpRequestComplete(url, httpContext);
        hiddenLoadingDialog();
        mRefreshLay.refreshComplete();
    }

    @Override
    public void onViewClick(int viewId) {
        switch (viewId) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_psersonal_info_contact:
//                Bundle bundle = new Bundle();
//                bundle.putString(StringConstant.USERID, mUserInfo.getUserCode());
//                IntentUtils.startActivity(getContext(), ContactTaActivity.class, bundle);
                break;
        }
    }

    private void setAppBarlayout() {
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d("ihesen", "verticalOffset:" + verticalOffset);
                if (verticalOffset == 0) {
                    mRefreshLay.setEnabled(true);
                } else {
                    mRefreshLay.setEnabled(false);
                }

                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                        mTvPersonalTopName.setText("");
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                    }
                    mTvPersonalTopName.setText(mUserInfo.getUserName());
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                    mTvPersonalTopName.setText("");
                }
            }
        });
        mRefreshLay.setRefreshListener(new RefreshLay.OnRefreshListener() {
            @Override
            public void onRefresh() {
                request();
            }
        });
    }

    @Override
    public void onRefresh() {
        mRequestParams.pageOption.pageNum = 0;
        request();
    }

    @Override
    public void onLoadMoreRequested() {
        mRequestParams.pageOption.pageNum++;
        request();
    }
}
