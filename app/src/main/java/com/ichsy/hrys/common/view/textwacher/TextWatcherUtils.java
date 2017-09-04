package com.ichsy.hrys.common.view.textwacher;


import com.ichsy.hrys.common.utils.EmojiFilter;

/**
 * 功能：供textwatcher使用的 工具类集
 * ＊创建者：赵然 on 16/8/31 10:22
 * ＊
 */
public class TextWatcherUtils {
    /**
     * 校验emoji表情
     *
     * @param string        当前输入的文字
     * @param isEmojiEnable 是否需要判断
     * @return
     */
    public static boolean checkEmojiEnable(String string, boolean isEmojiEnable) {
        if (isEmojiEnable) {
            return true;
        } else {
            return EmojiFilter.containsEmoji(string.toString());
        }

    }
}
