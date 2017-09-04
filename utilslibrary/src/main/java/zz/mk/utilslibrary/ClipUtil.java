package zz.mk.utilslibrary;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * author: zhu on 2017/4/21 16:31
 * email: mackkill@gmail.com
 */

public class ClipUtil {

    public static final void copy(Context context, String text) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("WebViewUrl", text);
        clipboardManager.setPrimaryClip(clipData);
    }

    public static final String parse(Context context) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = clipboardManager.getPrimaryClip();
        ClipData.Item item = clipData.getItemAt(0);
        return item.getText().toString();
    }
}
