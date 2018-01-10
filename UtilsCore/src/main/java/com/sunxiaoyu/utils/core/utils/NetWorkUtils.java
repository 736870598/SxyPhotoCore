package com.sunxiaoyu.utils.core.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 此类用于判断当前网络状态
 * @author sunxiaoyu
 */
public class NetWorkUtils {

	/**
	 * 此类用于得到当前网络连接状态
	 * @return   0 无网络连接   1 流量连接  2 wifi连接  3 未知(有网能用，不是流量)
	 */
	public static int getNetType(Context context){
		ConnectivityManager connectManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(connectManager == null){
			return 0;
		}
		NetworkInfo mNetworkInfo = connectManager.getActiveNetworkInfo();
		if(mNetworkInfo == null){
			return 0;
		}

		if(!mNetworkInfo.isAvailable()){
			//无网络连接
			return 0;
		}

		switch (mNetworkInfo.getType()){
			case ConnectivityManager.TYPE_MOBILE:
				return 1;
			case ConnectivityManager.TYPE_WIFI:
				return 2;
			default:
				return 3;
		}
	}

	/**
	 * 此方法用于判断当前网络是否连接正常
	 */
	public static boolean isNetworkAvailable(Context context){
		int netType = getNetType(context);
		return (netType != 0);
	}

	/**
	 *此方法用于判断当前网络是否连接wifi
	 * @param context
	 * @return
	 */
	public static boolean isConnWifi(Context context){
		int netType = getNetType(context);
		return netType == 2;
	}
}
