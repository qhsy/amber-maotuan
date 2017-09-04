package zz.mk.utilslibrary.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

public class NetUtil {
	/**
	 * 判断网络是否连接
	 * @param context
	 * @return
	 */
	public static boolean checkNetWork(Context context) {
		// ConnectivityManager//系统服务
		// 判断WIFI联网情况

		if (context != null) {
			
			boolean netSataus = false;
			ConnectivityManager conManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
			if (networkInfo != null) { // 注意，这个判断一定要的哦，要不然会出�?
				netSataus = networkInfo.isAvailable();
				
			}
			return netSataus;
		}
		return false;
	}


	/**
	 * 判断wifi是否处于连接
	 */
	public static boolean isWifi(Context context) {
		if (context != null) {
			ConnectivityManager manager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			// NetworkInfo:支持WIFI和MOBILE
			NetworkInfo networkInfo = manager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (networkInfo != null) {
				return networkInfo.isConnected();
			}
		}
		return false;
	}

	/**
	 * 判断Mobile是否处于连接
	 */
	public static boolean isMobile(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		// NetworkInfo:支持WIFI和MOBILE
		NetworkInfo networkInfo = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (networkInfo != null) 
		{
			return networkInfo.isConnected();
		}

		return false;
	}

	public static String getAllWifiInfo(Context mContext) {
		StringBuffer sb = new StringBuffer();
		WifiManager mWifiManager = (WifiManager) mContext
				.getSystemService(Context.WIFI_SERVICE);
		List<ScanResult> scanResults = mWifiManager.getScanResults();
		if (null == scanResults) {
			return "";
		}
		for (int i = 0; i < Math.min(scanResults.size(), 50); i++) {
			ScanResult scanResult = scanResults.get(i);
			sb.append(scanResult.BSSID.replace(":", "")).append("|");
		}
		String result = sb.toString();
		if (result.endsWith("|")) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	/**
	 * @return 返回boolean ,是否为wifi网络
	 *
	 */
	public static final boolean hasWifiConnection(Context context)
	{
		final ConnectivityManager connectivityManager= (ConnectivityManager) context.
				getSystemService(Context.CONNECTIVITY_SERVICE);
		final NetworkInfo networkInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		//是否有网络并且已经连接
		return (networkInfo!=null&& networkInfo.isConnectedOrConnecting());


	}

	/**
	 * @return 返回boolean,判断网络是否可用,是否为移动网络
	 *
	 */

	public static final boolean hasGPRSConnection(Context context){
		//获取活动连接管理器
		final ConnectivityManager connectivityManager= (ConnectivityManager) context.
				getSystemService(Context.CONNECTIVITY_SERVICE);
		final NetworkInfo networkInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		return (networkInfo!=null && networkInfo.isAvailable());

	}
	/**
	 * @return  判断网络是否可用，并返回网络类型，ConnectivityManager.TYPE_WIFI，ConnectivityManager.TYPE_MOBILE，不可用返回-1
	 */
	public static final int getNetWorkConnectionType(Context context){
		final ConnectivityManager connectivityManager=(ConnectivityManager) context.
				getSystemService(Context.CONNECTIVITY_SERVICE);
		final NetworkInfo wifiNetworkInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		final NetworkInfo mobileNetworkInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);


		if(wifiNetworkInfo!=null &&wifiNetworkInfo.isAvailable())
		{
			return ConnectivityManager.TYPE_WIFI;
		}
		else if(mobileNetworkInfo!=null &&mobileNetworkInfo.isAvailable())
		{
			return ConnectivityManager.TYPE_MOBILE;
		}
		else {
			return -1;
		}
	}


	/**
	 * 获取IP地址
	 * <p>需添加权限 {@code <uses-permission android:name="android.permission.INTERNET"/>}</p>
	 *
	 * @param useIPv4 是否用IPv4
	 * @return IP地址
	 */
	public static String getIPAddress(boolean useIPv4) {
		try {
			for (Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces(); nis.hasMoreElements(); ) {
				NetworkInterface ni = nis.nextElement();
				// 防止小米手机返回10.0.2.15
				if (!ni.isUp()) continue;
				for (Enumeration<InetAddress> addresses = ni.getInetAddresses(); addresses.hasMoreElements(); ) {
					InetAddress inetAddress = addresses.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						String hostAddress = inetAddress.getHostAddress();
						boolean isIPv4 = hostAddress.indexOf(':') < 0;
						if (useIPv4) {
							if (isIPv4) return hostAddress;
						} else {
							if (!isIPv4) {
								int index = hostAddress.indexOf('%');
								return index < 0 ? hostAddress.toUpperCase() : hostAddress.substring(0, index).toUpperCase();
							}
						}
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getWifiName(Context context) {
		WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifiMgr.getConnectionInfo();
		String wifiId = info != null ? info.getSSID() : "";
		return wifiId;
	}
}
