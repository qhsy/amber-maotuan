package com.ichsy.hrys.common.utils;

import android.app.Activity;
import android.os.Bundle;

import com.ichsy.hrys.common.utils.Bus.IntentBus;


/**
 * activity 之间跳转的工具类
 */
public class IntentUtils {
	/**
	* Activity 页面跳转
	 * 
	 * @param fromActivity
	 *            从哪个页面跳得
	 * @param toActivity
	 *            到哪个页面去得
	 */
	public static void startActivity(Activity fromActivity, Class toActivity) {

		IntentBus.getInstance().startActivity(fromActivity,toActivity);
	}

	/**
	 * Activity 页面跳转
	 * 
	 * @param fromActivity
	 *            从哪个页面跳得
	 * @param toActivity
	 *            到哪个页面去得
	 * @param bundle
	 *            携带的数据
	 */
	public static void startActivity(Activity fromActivity, Class toActivity, Bundle bundle) {
		IntentBus.getInstance().startActivity(fromActivity,toActivity,bundle);
	}
	/**
	 * Activity 页面跳转
	 * 
	 * @param fromActivity
	 *            从哪个页面跳得
	 * @param toActivity
	 *            到哪个页面去得
	 * @param flag
	 *            Activity 跳转标示
	 */
	public static void startActivity(Activity fromActivity, Class toActivity, int flag) {
		IntentBus.getInstance().startActivity(fromActivity,toActivity,flag);
	}
	/**
	 * Activity 页面跳转
	 * @param fromActivity
	 *  	从哪个页面跳得
	 * @param toActivity
	 * 			  到哪个页面去得
	 * @param bundle  页面需要传递的数据
	 * @param flag  跳转标示
	 */
	
	public static void startActivity(Activity fromActivity, Class toActivity, Bundle bundle, int flag) {
		IntentBus.getInstance().startActivity(fromActivity,toActivity,bundle,flag);
	}


}
