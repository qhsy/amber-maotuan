package com.ichsy.hrys.common.view.textwacher;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * 功能：过滤表情
 * ＊创建者：赵然 on 16/8/31 10:11
 * ＊
 */
public class CommonTextWatcher implements TextWatcher {
    private EditText editText;


    private boolean isEmojiEnable = false;
    public CommonTextWatcher(EditText editText){

        this.editText = editText;
    }
    //记录字符串临时变量
    private CharSequence temp = "";
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
    }

    @Override
    public void afterTextChanged(Editable s) {

        if (isContentEnable(s.toString())){
            editText.setText(temp);
            editText.setSelection(temp.length());
        }else{
            temp = s.toString();
        }
    }

    private boolean  isContentEnable(String string){


        return  TextWatcherUtils.checkEmojiEnable(string,isEmojiEnable);
    }


}
