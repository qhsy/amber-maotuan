package zz.mk.utilslibrary;

import android.content.Context;

/**
 * 用于解决provider冲突的util
 * author: zhu on 2017/9/1 11:46
 * email: mackkilled@gmail.com
 */

public class ProviderUtil {
    public static String getFileProviderName(Context context){
        return context.getPackageName()+".provider";
    }
}
