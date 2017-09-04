package com.ichsy.hrys.common.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文本处理工具类
 */
public class StringUtils {

	/**
	 * 判断手机号是否符合规则
	 * @param MobileNo
	 * @return
     */
	public static boolean IsValidMobileNo(String MobileNo) {
		Pattern p = Pattern.compile("^1\\d{10}$");

		Matcher m = p.matcher(MobileNo);
		return m.matches();

	}

	/**
	 * 判断姓名是否符合 字母数字下划线 汉字
	 *
	 * @return
	 */
	public static boolean isNameAvailable(String nameString) {

		Pattern pattern = Pattern.compile("[a-zA-Z0-9_\\u4e00-\\u9fa5]+$");
		Matcher matcher = pattern.matcher(nameString);

		return matcher.matches();
	}

	/**
	 * 判断是否是真实姓名
	 * @param nameString
	 * @return
     */
	public static boolean isTrueName(String nameString) {
		Pattern pattern = Pattern.compile("[a-zA-Z·\\u4e00-\\u9fa5]+$");
		Matcher matcher = pattern.matcher(nameString);

		return matcher.matches();
	}

	public static boolean isIdentifyCode(String identifyCode){

		Pattern pattern = Pattern.compile("[a-zA-Z0-9]+$");
		Matcher matcher = pattern.matcher(identifyCode);

		return matcher.matches();
	}
	public static boolean isIDCard (String IDCardStr){
		Pattern pattern = Pattern.compile("(^\\d{18}$)|(^\\d{15}$)");
		Matcher matcher = pattern.matcher(IDCardStr);

		return matcher.matches();
	}
	/**
	 * 获取字符串的真实长度，汉字占2个长度，字母占1个长度
	 *
	 * @param nickName
	 * @return
	 * @author LiuYuHang
	 * @date 2014年12月29日
	 */
	public static int getTrueLengh(String nickName) {
		int count = 0;
		char[] temC = nickName.toCharArray();


		for (int i = 0; i < temC.length; i++) {
			int a = temC[i];
			if (a >= 19968 && a <= 40869) {
				count += 2;
			}else if ((a >= 48 && a <= 57) || (a >= 97 && a <= 122)
					|| (a >= 65 && a <= 90)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 返回中间四位*的手机号
	 * @param phoneNum
	 * @return
     */
  public static String getShowPhoneNum(String phoneNum){
	  if(!TextUtils.isEmpty(phoneNum) && phoneNum.length() >= 7){
		  StringBuilder sb = new StringBuilder();
		  sb.append(phoneNum.substring(0,3));
		  sb.append("****");
		  sb.append(phoneNum.substring(7));
		  return sb.toString();
	  }else{
		  return "";
	  }
  }

  public static String CheckParams(String pParams) {
	  return TextUtils.isEmpty(pParams) ? "" : pParams;
  }

  public static double StringToDouble(String text) {
	  return text.equals("")? 0 : Double.parseDouble(text);
  }
}
