package com.ichsy.hrys.common.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ichsy.hrys.R;

import zz.mk.utilslibrary.ScreenUtil;

/**
 * author: zhu on 2017/4/12 17:48
 * email: mackkilled@gmail.com
 */

public class CustomTittleView extends LinearLayout {

    /**
     * 左侧 中间 右侧 的textviews
     */
    private TextView mLeftTextView, mCenterTextView, mRightTextView;
    /**
     * 整体view
     */
    private View view;

    private final int LEFT = 0;
    private final int CENTER = 1;
    private final int RIGHT = 2;

    public CustomTittleView(Context context) {
        super(context);
        init(context);
    }


    public CustomTittleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomTittleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        view = View.inflate(context, R.layout.base_title, this);

        mLeftTextView = (TextView) view.findViewById(R.id.base_left);
        mCenterTextView = (TextView) view.findViewById(R.id.base_title);
        mRightTextView = (TextView) view.findViewById(R.id.base_right);
    }

    /**
     * 设置左侧tittle的文本
     *
     * @param leftText
     */
    public void setLeftText(String leftText) {
        setVisible(this);
        setVisible(mLeftTextView);
        mLeftTextView.setText(leftText);
    }

    /**
     * 设置中间文本
     *
     * @param centerText
     */
    public void setCenterText(String centerText) {
        setVisible(this);
        setVisible(mCenterTextView);
        mCenterTextView.setText(centerText);
    }

    /**
     * 设置右侧文本
     *
     * @param rightText
     */
    public void setRightText(String rightText) {
        setVisible(this);
        setVisible(mRightTextView);
        mRightTextView.setText(rightText);
    }

    public void setVisible(View view) {
        if (view != null && view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
        }
    }
    /**
     * 设置右侧文本的左侧的图片
     *
     * @param viewType         修改视图的位置  0:左边 1:中间  2:右边
     * @param drawableId       图片ID
     * @param drawableLocation 0: left 1:right
     *                         图片位置
     */
    public void setTextViewDrawableResource(int viewType, int drawableId, int drawableLocation) {
        Drawable drawable = getViewDrawable(drawableId);
        switch (viewType) {
            case LEFT:
                setVisible(mLeftTextView);
                setTextViewDrawableWithLocation(mLeftTextView, drawable, drawableLocation);
                break;
            case CENTER:
                setVisible(mCenterTextView);
                setTextViewDrawableWithLocation(mCenterTextView, drawable, drawableLocation);
                break;
            case RIGHT:
                setVisible(mRightTextView);
                setTextViewDrawableWithLocation(mRightTextView, drawable, drawableLocation);
                break;
            default:
                break;
        }

    }

    /**
     * 根据图片位置 设置图片
     *
     * @param view
     * @param drawable
     * @param drawableLocation
     */
    private void setTextViewDrawableWithLocation(TextView view, Drawable drawable, int drawableLocation) {
        switch (drawableLocation) {
            case 0:
                view.setCompoundDrawables(drawable, null, null, null); // 设置左图标
                view.setCompoundDrawablePadding(ScreenUtil.dip2px(getContext(), 10));
                break;
            case 1:
                view.setCompoundDrawables(null, null, drawable, null); // 设置右图标
                view.setCompoundDrawablePadding(ScreenUtil.dip2px(getContext(), 10));
                break;
            default:
                break;

        }
    }

    /**
     * 设置TestView时需要先获取drawable 这里根据ID构建Drawable
     *
     * @param drawableID
     * @return
     */
    private Drawable getViewDrawable(int drawableID) {
        Drawable drawable;
        Resources res = getResources();
        drawable = res.getDrawable(drawableID);
        return getAvailableDrawable(drawable);
    }

    /**
     * 设置drawable大小
     *
     * @param drawable
     * @return
     */
    private Drawable getAvailableDrawable(Drawable drawable) {
        // 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }

    /**
     * 获取左侧的view
     * @return
     */
    public TextView getLeftView() {
        mLeftTextView.setVisibility(VISIBLE);
        return mLeftTextView;
    }
    /**
     * 获取右侧的view
     * @return
     */
    public TextView getRightView(){
        mRightTextView.setVisibility(VISIBLE);
        return mRightTextView;
    }

    /**
     * 获取中间的View
     * @return
     */
    public TextView getCenterView(){
        mCenterTextView.setVisibility(VISIBLE);
        return mCenterTextView;
    }

    /**
     * 设置左侧文本的颜色
     * @param colorId  eg: R.color.blue
     */
    public void setLeftTextColorId(int colorId){
        mLeftTextView.setTextColor(getResources().getColor(colorId));
    }

    /**
     * 设置右侧文本的颜色
     * @param colorString  eg:"#ffffff"
     */
    public void setLeftTextColorString(String colorString){
        mLeftTextView.setTextColor(Color.parseColor(colorString));
    }
    /**
     * 设置中间文本的颜色
     * @param colorId  eg: R.color.blue
     */
    public void setCenterTextColorId(int colorId) {
        mCenterTextView.setTextColor(getResources().getColor(colorId));
    }
    /**
     * 设置中间文本的颜色
     * @param colorString  eg:"#ffffff"
     */
    public void setCenterTextColorString(String colorString){
        mCenterTextView.setTextColor(Color.parseColor(colorString));
    }
    /**
     * 设置右侧文本的颜色
     * @param colorId  eg: R.color.blue
     */
    public void setRightTextColorId(int colorId){
        mRightTextView.setTextColor(getResources().getColor(colorId));
    }
    /**
     * 设右侧文本的颜色
     * @param colorString  eg:"#ffffff"
     */
    public void setRightTextColorString(String colorString){
        mRightTextView.setTextColor(Color.parseColor(colorString));
    }

    public void setViewBackgroundColor(int colorID){
        findViewById(R.id.rl_title_background).setBackgroundColor(colorID);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setViewBackground(Drawable backgroundDrable){
        findViewById(R.id.rl_title_background).setBackground(backgroundDrable);
    }

    public void setViewBackgroundResource(int resid){
        findViewById(R.id.rl_title_background).setBackgroundResource(resid);
    }

    /**
     * 设置左侧view的点击事件
     * @param listener
     */
    public void setLeftClickListener(OnClickListener listener){
        mLeftTextView.setOnClickListener(listener);
    }
    /**
     * 设置右侧view的点击事件
     * @param listener
     */
    public void setRightClickListener(OnClickListener listener){
        mRightTextView.setOnClickListener( listener);
    }
}
