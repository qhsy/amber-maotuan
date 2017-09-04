package zz.mk.utilslibrary;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static zz.mk.utilslibrary.bitmap.BitmapUtil.drawableToBitmap;

public class FileUtil {

	public static String saveFn = "sdcard/"
			+ "/user_chat_data/";
	public static String savelistFn = "sdcard/"
			+ "/user_chat_data/chatList/";
	public static String savechannelFn = "sdcard/"
			+ "/user_chat_data/channel_id/";

	/** SD卡是否存在 **/
	private boolean hasSD = false;
	/** 当前程序包的路径 **/
	private String FILESPATH;

	public static boolean isFileExists(File file) {
		if (!file.exists()) {
			return false;
		}
		return true;
	}

	/**
	 * 获取文件夹下的所有文件名
	 */
	public static List<String> getFileName(String fileName) {
		List<String> fileList = new ArrayList<String>();
		String path = fileName; // 路径
		File f = new File(path);
		if (!f.exists()) {
			System.out.println(path + " not exists");
			return null;
		}

		File fa[] = f.listFiles();
		for (int i = 0; i < fa.length; i++) {
			File fs = fa[i];
			if (!fs.isDirectory()) {
				fileList.add(fs.getName());
			}
		}
		return fileList;
	}

	/**
	 * 在SD卡上创建文件
	 * 
	 * @throws IOException
	 */
	public static File createSDFile(String fileName) throws IOException {
		File file = new File(fileName);
		if (!isFileExists(file))
			if (file.isDirectory()) {
				file.mkdirs();
			} else {
				file.createNewFile();
			}
		return file;
	}

	/**
	 * 在SD卡上创建文件夹
	 * 
	 * @throws IOException
	 */
	public static File createSDDirectory(String fileName) throws IOException {
		File file = new File(fileName);
		if (!isFileExists(file))
			file.mkdirs();
		return file;
	}

	/**
	 * @content 存储内容
	 * @file 文件目录
	 * @isAppend 是否追加
	 */
	public synchronized static void writeString(String content, String file, boolean isAppend) {
		try {
			createSDDirectory(saveFn);
			createSDDirectory(savelistFn);
			createSDDirectory(savechannelFn);
			byte[] data = content.getBytes("utf-8");
			writeBytes(file, data, isAppend);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public synchronized static boolean writeBytes(String filePath, byte[] data,
			boolean isAppend) {
		try {
			FileOutputStream fos;
			if (isAppend)
				fos = new FileOutputStream(filePath, true);
			else
				fos = new FileOutputStream(filePath);
			fos.write(data);
			fos.close();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	/**
	 * 读取SD卡中文本文件
	 * 
	 * @param fileName
	 * @return
	 */
	public synchronized static String readSDFile(String fileName) {
		StringBuffer sb = new StringBuffer();
		File f1 = new File(fileName);
		String str = null;
		try {
			InputStream is = new FileInputStream(f1);
			InputStreamReader input = new InputStreamReader(is, "UTF-8");
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(input);
			while ((str = reader.readLine()) != null) {
				sb.append(str);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	public String getFILESPATH() {
		return FILESPATH;
	}

	public boolean hasSD() {
		return hasSD;
	}

	/**
	 * 删除单个文件
	 * 
	 * @param filePath
	 *            被删除文件的文件名
	 * @return 文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String filePath) {
		File file = new File(filePath);
		if (file.isFile() && file.exists()) {
			return file.delete();
		}
		return false;
	}

	/**
	 * 删除文件夹以及目录下的文件
	 * 
	 * @param filePath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String filePath) {
		boolean flag = false;
		// 如果filePath不以文件分隔符结尾，自动添加文件分隔符
		if (!filePath.endsWith(File.separator)) {
			filePath = filePath + File.separator;
		}
		File dirFile = new File(filePath);
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		flag = true;
		File[] files = dirFile.listFiles();
		// 遍历删除文件夹下的所有文件(包括子目录)
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				// 删除子文件
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} else {
				// 删除子目录
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前空目录
		return dirFile.delete();
	}

	/**
	 * 把文件转换成base64
	 * 
	 * @param path
	 * @return
	 */
	public static String encodeBase64File(String path) throws Exception {
		byte[] videoBytes;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		@SuppressWarnings("resource")
		FileInputStream fis = new FileInputStream(new File(path));
		byte[] buf = new byte[1024];
		int n;
		while (-1 != (n = fis.read(buf)))
			baos.write(buf, 0, n);
		videoBytes = baos.toByteArray();
		return Base64.encodeToString(videoBytes, Base64.NO_WRAP);
	}

	/**
	 * 保存drawable到SD卡
	 *
	 * @param drawable
	 * @param path
	 * @author LiuYuHang
	 * @date 2014年11月3日
	 */
	public static boolean  drawableTofile(Drawable drawable, String path,
										  String fileName) {
		// Log.i(TAG, "drawableToFile:"+path);
		new File(path).mkdirs();
		Log.i("data", "fileName=====" + fileName);
		File file = new File(path + fileName);

		Bitmap bitmap = drawableToBitmap(drawable);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100 /* ignored for PNG */, bos);
		byte[] bitmapdata = bos.toByteArray();

		// write the bytes in file
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			fos.write(bitmapdata);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	/**
	 * 复制文件
	 */
	public static boolean copyFile(String olderPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(olderPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(olderPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/***
	 * @discretion 判断是否存在sd卡
	 * @author 时培飞 Create at 2015-1-5 下午3:07:19
	 */
	public static boolean isExistSDCard() {
		return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
	}

	public static long getFileSize(String filePath) {
		if (null == filePath) {
			LogUtil.zLog().e("Invalid param. filePath: " + filePath);
			return 0;
		}

		File file = new File(filePath);
		if (file == null || !file.exists()) {
			return 0;
		}

		return file.length();
	}
}