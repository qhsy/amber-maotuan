package com.ichsy.hrys.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

import com.ichsy.hrys.R;

/**
 * 条目分割线  嵌套高度
 */
public class ReloadWHGridView extends GridView {

	private int dividerColorId;
	private float strokeWidth = 3.0f;

	private boolean isDrawBorder;

	public ReloadWHGridView(Context context) {
		super(context);
	}

	public ReloadWHGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ReloadWHGridView);
		isDrawBorder = typedArray.getBoolean(R.styleable.ReloadWHGridView_draw_border, true);
		dividerColorId = typedArray.getColor(R.styleable.ReloadWHGridView_divider_color, Color.parseColor("#efefef"));
		strokeWidth = typedArray.getDimension(R.styleable.ReloadWHGridView_divider_width,3.0f);
		typedArray.recycle();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		if(isDrawBorder){
			View localView1 = getChildAt(0);
			if (localView1 == null){
				return ;
			}
			int column = getWidth() / localView1.getWidth();// 计算出一共有多少列，假设有3列
			int childCount = getChildCount();// 子view的总数
			System.out.println("子view的总数childCount==" + childCount);
			Paint localPaint;// 画笔
			localPaint = new Paint();
			localPaint.setStrokeWidth(strokeWidth);
			localPaint.setStyle(Paint.Style.STROKE);
			localPaint.setColor(dividerColorId);// 设置画笔的颜色
			for (int i = 0; i < childCount; i++) {// 遍历子view
				View cellView = getChildAt(i);// 获取子view
				if (i < 4) {// 第一行
					//canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getRight(), cellView.getTop(), localPaint);
				}
				if (i % column == 0) {// 第一列
					canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getLeft(), cellView.getBottom(), localPaint);
				}
				if ((i + 1) % column == 0) {// 第三列
					// 画子view底部横线
					canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
					canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
				} else if ((i + 1) > (childCount - (childCount % column))) {// 如果view是最后一行
					// 画子view的右边竖线
					canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
					canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
				} else {// 如果view不是最后一行
					// 画子view的右边竖线
					canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
					// 画子view的底部横线
					canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
				}
			}
		}
	}
}
