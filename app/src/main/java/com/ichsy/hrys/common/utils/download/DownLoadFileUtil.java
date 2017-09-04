package com.ichsy.hrys.common.utils.download;

import java.util.UUID;


public class DownLoadFileUtil {
	
	public static String getFileNameByUrl(String url) {
		if (isEmpty(url)) {
			return null;
		}
		int index = url.lastIndexOf('?');
		int index2 = url.lastIndexOf("/");
		if (index > 0 && index2 >= index) {
			return UUID.randomUUID().toString();
		}
		return url.substring(index2 + 1, index < 0 ? url.length() : index);

	}

	public static boolean isEmpty(String input) {
		if (input == null || "".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

}
