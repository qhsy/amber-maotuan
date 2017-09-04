package com.ichsy.hrys.common.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.ichsy.hrys.R;

import zz.mk.utilslibrary.ScreenUtil;
import zz.mk.utilslibrary.ToastUtils;



/**
 *
 *
 * Package: com.ichsy.syxd.util
 *
 * File: DialogUtils.java
 *
 * @author: 赵然 Date: 2015-3-26
 *
 *          Modifier： wangshoubo Modified Date：2015/11/6 Modify：
 *
 *          Copyright @ 2015 ICHSY
 *
 */
public class DialogUtils {
	private static int animationID = R.style.AnimBottom;
	/**
	 *获取默认底部dialog，dialog不全屏，点击外部可消失
	 * @param context
	 * @param view
	 * @return
	 */
	public static Dialog getBottomDialog(Context context, View view) {
		return getAlertDialog(context, view, DialogGravity.BOOTOM, animationID, true, false);
	}

	/**
	 * 获取底部dialog，dialog可设置是否全屏，点击外部可消失
	 * @param context
	 * @param view
	 * @param isFullScreen
	 * @return
	 */
	public static Dialog getBottomDialog(Context context, View view, boolean isFullScreen) {
		return getAlertDialog(context, view, DialogGravity.BOOTOM, animationID, true, isFullScreen);
	}


	/**
	 *获取底部dialog，dialog设置是否全屏，点击外部设置是否可消失
	 * @param context
	 * @param view
	 * @param isFullScreen
	 * @param isCancle
	 * @return
	 */
	public static Dialog getBottomDialog(Context context, View view, boolean isFullScreen, boolean isCancle) {
		return getAlertDialog(context, view, DialogGravity.BOOTOM, animationID, isCancle, isFullScreen);
	}



	/**
	 *获取中部dialog，dialog设置是否全屏，点击外部设置是否可消失
	 * @param context
	 * @param view
	 * @return
	 */
	public static Dialog getDomainLablesDialog(Context context, View view) {
		Dialog dialog = new Dialog(context,R.style.loginDialog);
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();

		if (view.getParent() != null) {
			((ViewGroup) view.getParent()).removeAllViews();
		}
		Window window = dialog.getWindow();
		window.setGravity(Gravity.CENTER);
		window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, ScreenUtil.getScreenHeight(context)/2);
		window.setContentView(view);

		return dialog;
	}
	/**
	 *获取中部dialog，dialog设置是否全屏，点击外部设置是否可消失
	 * @param context
	 * @param view
	 * @return
	 */
	public static Dialog getDomainLablesDialog(Context context, View view, double percent) {
		Dialog dialog = new Dialog(context,R.style.loginDialog);
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();

		if (view.getParent() != null) {
			((ViewGroup) view.getParent()).removeAllViews();
		}
		Window window = dialog.getWindow();
		window.setGravity(Gravity.CENTER);
		window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, (int) (ScreenUtil.getScreenHeight(context)*percent));
		window.setContentView(view);

		return dialog;
	}

	private static Dialog getAlertDialog(Context context, View view, DialogGravity gravity, int animationID, boolean isCancle, boolean isFullScreen) {
		Dialog dialog = new Dialog(context,R.style.loginDialog);
		dialog.setCanceledOnTouchOutside(isCancle);
		dialog.show();

		if (view.getParent() != null) {
			((ViewGroup) view.getParent()).removeAllViews();
		}
		Window window = dialog.getWindow();
		if (gravity == DialogGravity.BOOTOM) {
			window.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL);
			if(isFullScreen) {
				window.setLayout(ScreenUtil.getScreenWidth(context), android.view.WindowManager.LayoutParams.WRAP_CONTENT);
			}
		} else if (gravity == DialogGravity.CENTER) {
			window.setGravity(Gravity.CENTER);
			if(isFullScreen) {
				window.setLayout(ScreenUtil.getScreenWidth(context), android.view.WindowManager.LayoutParams.WRAP_CONTENT);
			} else {
				window.setLayout(ScreenUtil.getScreenWidth(context)*4/5, android.view.WindowManager.LayoutParams.WRAP_CONTENT);
			}
		}
		if (animationID > 0) {
			window.setWindowAnimations(animationID);
		}
		window.setContentView(view);

		return dialog;

	}
	/**
	 * dialog 取消
	 * @param dialog
	 */
	public static void setDismiss(AlertDialog dialog) {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}

	public static void setEditTextDialogDismiss(Dialog dialog) {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}


	public enum DialogGravity {
		BOOTOM, CENTER
	}

	/**
	 * 获取展示再指定位置的dialog
	 * @param context 上下文
	 * @param view 展示的view
	 * @param locationView  对应View
	 *                      @param  type  view展示类型  0：上 1：下
	 * @return
	 */
	public static Dialog getLocationDialog(Context context, View view, View locationView, int type){

		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		int  contentViewWith = layoutParams.width;
		int contentViewHeight =layoutParams.height;
		int notifactionViewHeight = ScreenUtil.dip2px(context,25);


		Dialog dialog = new Dialog(context,R.style.loginDialog);
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();

		if (view.getParent() != null) {
			((ViewGroup) view.getParent()).removeAllViews();
		}
		Window window = dialog.getWindow();
		window.setGravity(Gravity.LEFT | Gravity.TOP);
//		window.setLayout(android.view.WindowManager.LayoutParams.WRAP_CONTENT, android.view.WindowManager.LayoutParams.WRAP_CONTENT);
		WindowManager.LayoutParams lp = window.getAttributes();
		int[]location = new int[2];
		//返回location的left top

		locationView.getLocationOnScreen(location);
		ToastUtils.showShortToast("location: x:"+location[0]+" Y:"+location[1] +"\nlocationview height:"+ locationView.getHeight() + " width:" +locationView.getWidth() + "\nview width:" + view.getWidth() + " height:"+view.getHeight()  );
		lp.x = location[0] + locationView.getWidth() - contentViewWith;

		lp.width = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
		lp.height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
		switch (type){
			case 0:
				//展示再locationView之上
				lp.y = location[1] - contentViewHeight - notifactionViewHeight;
				break;
			case 1 :
				//展示再locationView之下
				lp.y = location[1] + locationView.getHeight() - notifactionViewHeight;
				break;
			default:
		}


		window.setAttributes(lp);
//		if (animationID > 0) {
//			window.setWindowAnimations(animationID);
//		}
		window.setContentView(view);

		return dialog;
	}

}
