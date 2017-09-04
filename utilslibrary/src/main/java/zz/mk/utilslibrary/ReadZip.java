package zz.mk.utilslibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Created by Administrator on 2016/12/9.
 */

public class ReadZip {

    /**
     * 复制assets中文件到sd卡
     *
     * @param context
     * @param localDir
     * @param outFileDir
     * @throws IOException
     */
    public static void copyAssetsToSD(Context context, String localDir, String outFileDir) throws IOException {
        InputStream myInput;
        OutputStream myOutput = new FileOutputStream(outFileDir);
        myInput = context.getAssets().open(localDir);
        byte[] buffer = new byte[1024];
        int length = myInput.read(buffer);
        while (length > 0) {
            myOutput.write(buffer, 0, length);
            length = myInput.read(buffer);
        }

        myOutput.flush();
        myInput.close();
        myOutput.close();
    }

    /**
     * tab-image 读取
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static InputStream readTabPicFile(String file, String ResId) throws Exception {
        String fileName = file.substring(file.length() - 9, file.length() - 4);
        ZipFile zf = new ZipFile(file);

        InputStream in = new BufferedInputStream(new FileInputStream(file));
        ZipInputStream zin = new ZipInputStream(in);
        ZipEntry ze;
        while ((ze = zin.getNextEntry()) != null) {
            if (ze.isDirectory()) {
                //Do nothing
            } else {
                Log.i("tag", "file - " + ze.getName() + " : " + ze.getSize() + " bytes");
                if (ze.getName().equals(fileName + "/tabbar/" + ResId + ".png")) {
                    InputStream is = zf.getInputStream(ze);
                    return is;
                } else if (ze.getName().equals(fileName + "/tabbar/" + ResId + ".png")) {
                    InputStream is = zf.getInputStream(ze);
                    return is;
                } else if (ze.getName().equals(fileName + "/tabbar/" + ResId + ".png")) {
                    InputStream is = zf.getInputStream(ze);
                    return is;
                } else if (ze.getName().equals(fileName + "/tabbar/" + ResId + ".png")) {
                    InputStream is = zf.getInputStream(ze);
                    return is;
                } else if (ze.getName().equals(fileName + "/tabbar/" + ResId + ".png")) {
                    InputStream is = zf.getInputStream(ze);
                    return is;
                } else if (ze.getName().equals(fileName + "/tabbar/" + ResId + ".png")) {
                    InputStream is = zf.getInputStream(ze);
                    return is;
                } else if (ze.getName().equals(fileName + "/tabbar/" + ResId + ".png")) {
                    InputStream is = zf.getInputStream(ze);
                    return is;
                } else if (ze.getName().equals(fileName + "/tabbar/" + ResId + ".png")) {
                    InputStream is = zf.getInputStream(ze);
                    return is;
                }
            }
        }
        zin.closeEntry();
        return null;
    }

    /**
     * guide-images 读取
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static Bitmap readGuidePic(String file, String ResId) throws Exception {
        String fileName = file.substring(file.length() - 9, file.length() - 4);
        ZipFile zf = new ZipFile(file);
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        ZipInputStream zin = new ZipInputStream(in);
        ZipEntry ze;
        while ((ze = zin.getNextEntry()) != null) {
            if (ze.isDirectory()) {
                //Do nothing
            } else {
                Log.i("tag", "file - " + ze.getName() + " : " + ze.getSize() + " bytes");
                if (ze.getName().equals(fileName + "/guide/" + ResId + ".png")) {
                    InputStream is = zf.getInputStream(ze);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    return bitmap;
                } else if (ze.getName().equals(fileName + "/guide/" + ResId + ".png")) {
                    InputStream is = zf.getInputStream(ze);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    return bitmap;
                } else if (ze.getName().equals(fileName + "/guide/" + ResId + ".png")) {
                    InputStream is = zf.getInputStream(ze);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    return bitmap;
                }
            }
        }
        zin.closeEntry();
        return null;
    }

    /**
     * data.json读取
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static String readDataFile(String file) throws Exception {
        String fileName = file.substring(file.length() - 9, file.length() - 4);
        ZipFile zf = new ZipFile(file);
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        ZipInputStream zin = new ZipInputStream(in);
        ZipEntry ze;
        while ((ze = zin.getNextEntry()) != null) {
            if (ze.isDirectory()) {
                //Do nothing
            } else {
                if (ze.getName().equals(fileName + "/data/data.json")) {
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(zf.getInputStream(ze)));
                    String line;
                    while ((line = br.readLine()) != null) {
                        return line;
                    }
                    br.close();
                }
            }
        }
        zin.closeEntry();
        return "";
    }

    /**
     * APP.xml 读取
     *
     * @param file
     * @return
     * @throws IOException
     */

    public static InputStream readAppFile(String file) throws IOException {
        String fileName = file.substring(file.length() - 9, file.length() - 4);
        ZipFile zf = new ZipFile(file);
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        ZipInputStream zin = new ZipInputStream(in);
        ZipEntry ze;
        while ((ze = zin.getNextEntry()) != null) {
            if (ze.isDirectory()) {
                //Do nothing
            } else {
                if (ze.getName().equals(fileName + "/app/app.xml")) {
                    InputStream inputStream = zf.getInputStream(ze);
                    return inputStream;
                }
            }
        }
        zin.closeEntry();
        return null;
    }
}
