package zz.mk.utilslibrary.system;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.security.MessageDigest;
import java.util.List;

public class TaskUtil {

	/**
	 * 判断当前应用程序处于前台还是后台 需加权限 <uses-permission
	 * android:name="android.permission.GET_TASKS" />
	 */
	public static boolean isAppRunInFront(Context context) {
		boolean result = false;
		if (context != null) {
			String packageName = context.getPackageName();
			String topActivityClassName = getTopActivityName(context);
			if (packageName != null && topActivityClassName != null
					&& topActivityClassName.startsWith(packageName)) {
				result = true;
			}
		}
		return result;
	}

	public static String getTopActivityName(Context context) {
		String topActivityClassName = null;
		ActivityManager activityManager = (ActivityManager) (context
				.getSystemService(Context.ACTIVITY_SERVICE));
		List<RunningTaskInfo> runningTaskInfos = activityManager
				.getRunningTasks(1);
		if (runningTaskInfos != null) {
			ComponentName f = runningTaskInfos.get(0).topActivity;
			topActivityClassName = f.getClassName();
		}
		return topActivityClassName;
	}

	public static String getAllAppPackage(Context context) {
		StringBuffer sb = new StringBuffer();
		PackageManager packageManager = context.getPackageManager();
		List<PackageInfo> packageInfoList = packageManager
				.getInstalledPackages(0);

		for (int i = 0; i < packageInfoList.size(); i++) {
			PackageInfo pak = (PackageInfo) packageInfoList.get(i);
			if (pak.applicationInfo.packageName.startsWith("com.android")) {
				continue;
			}
			if (sb.length() > 4950) {
				break;
			}
			// 判断是否为非系统预装的应用程序
			String packge = pak.applicationInfo.packageName;
			int dot = packge.indexOf(".");
			if (dot != -1) {
				packge = packge.substring(dot + 1, packge.length());
			}
			if ((pak.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
				sb.append(packge).append("@0|");
			} else {
				sb.append(packge).append("@1|");
			}
		}

		String result = sb.toString();
		if (result.endsWith("|")) {
			result = result.substring(0, result.length() - 1);
		}

		return result;
	}


	// 判断手机里面是否安装了某个应用
	public static boolean isAppInstalled(Context context, String pkgName) {
		PackageInfo packageInfo = null;
		try {
			packageInfo = context.getPackageManager()
					.getPackageInfo(pkgName, 0);
		} catch (NameNotFoundException e) {
			packageInfo = null;
			e.printStackTrace();
		}
		if (packageInfo == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 安装apk
	 *
	 * @param context 上下文
	 * @param file    APK文件uri
	 */
	public static void installApk(Context context, Uri file) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(file, "application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	/**
	 * 卸载apk
	 *
	 * @param context     上下文
	 * @param packageName 包名
	 */
	public static void uninstallApk(Context context, String packageName) {
		Intent intent = new Intent(Intent.ACTION_DELETE);
		Uri packageURI = Uri.parse("package:" + packageName);
		intent.setData(packageURI);
		context.startActivity(intent);
	}


	// 通过包名启动其他app
	public static void StartApplicationWithPackageName(Context context,
			String packagename) {

		// 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
		PackageInfo packageinfo = null;
		try {
			packageinfo = context.getPackageManager().getPackageInfo(
					packagename, 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		if (packageinfo == null) {
			return;
		}

		// 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
		Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
		resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		resolveIntent.setPackage(packageinfo.packageName);

		// 通过getPackageManager()的queryIntentActivities方法遍历
		List<ResolveInfo> resolveinfoList = context.getPackageManager()
				.queryIntentActivities(resolveIntent, 0);

		ResolveInfo resolveinfo = resolveinfoList.iterator().next();
		if (resolveinfo != null) {
			// packagename = 参数packname
			String packageName = resolveinfo.activityInfo.packageName;
			// 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]
			String className = resolveinfo.activityInfo.name;
			// LAUNCHER Intent
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);

			// 设置ComponentName参数1:packagename参数2:MainActivity路径
			ComponentName cn = new ComponentName(packageName, className);

			intent.setComponent(cn);
			context.startActivity(intent);
		}
	}

	/**
	 * 检测服务是否运行
	 *
	 * @param context   上下文
	 * @param className 类名
	 * @return 是否运行的状态
	 */
	public static boolean isServiceRunning(Context context, String className) {
		boolean isRunning = false;
		ActivityManager activityManager
				= (ActivityManager) context.getSystemService(
				Context.ACTIVITY_SERVICE);
		List<RunningServiceInfo> servicesList
				= activityManager.getRunningServices(Integer.MAX_VALUE);
		for (RunningServiceInfo si : servicesList) {
			if (className.equals(si.service.getClassName())) {
				isRunning = true;
			}
		}
		return isRunning;
	}


	/**
	 * 停止运行服务
	 *
	 * @param context   上下文
	 * @param className 类名
	 * @return 是否执行成功
	 */
	public static boolean stopRunningService(Context context, String className) {
		Intent intent_service = null;
		boolean ret = false;
		try {
			intent_service = new Intent(context, Class.forName(className));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (intent_service != null) {
			ret = context.stopService(intent_service);
		}
		return ret;
	}

	/**
	 * 获取应用签名
	 *
	 * @param context 上下文
	 * @param pkgName 包名
	 * @return 返回应用的签名
	 */
	public static String getSign(Context context, String pkgName) {
		try {
			PackageInfo pis = context.getPackageManager()
					.getPackageInfo(pkgName,
							PackageManager.GET_SIGNATURES);
			return hexdigest(pis.signatures[0].toByteArray());
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 将签名字符串转换成需要的32位签名
	 *
	 * @param paramArrayOfByte 签名byte数组
	 * @return 32位签名字符串
	 */
	private static String hexdigest(byte[] paramArrayOfByte) {
		final char[] hexDigits = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97,
				98, 99, 100, 101, 102 };
		try {
			MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
			localMessageDigest.update(paramArrayOfByte);
			byte[] arrayOfByte = localMessageDigest.digest();
			char[] arrayOfChar = new char[32];
			for (int i = 0, j = 0; ; i++, j++) {
				if (i >= 16) {
					return new String(arrayOfChar);
				}
				int k = arrayOfByte[i];
				arrayOfChar[j] = hexDigits[(0xF & k >>> 4)];
				arrayOfChar[++j] = hexDigits[(k & 0xF)];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
