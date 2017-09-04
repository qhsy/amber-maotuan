package com.ichsy.hrys.common.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ichsy.hrys.R;


/**
 * 下载素材对话框
 * author: ihesen on 2016/11/4 14:27
 * email: hesen@ichsy.com
 */

public class DownloadResouceDialog extends Dialog {

    private ProgressBar progressBar;
    private TextView progressTV;
    private OnClickCancelLister cancelLister;

    public DownloadResouceDialog(Context context) {
        super(context, R.style.loginDialog);
        init();
    }

    private void init() {
        show();
        setContentView(R.layout.dialog_save_resource);
        setCanceledOnTouchOutside(false);
        progressBar = (ProgressBar) findViewById(R.id.pb__save_resource);
        progressTV = (TextView) findViewById(R.id.tv_save_resource);
        findViewById(R.id.iv_download_res_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelLister != null) {
                    cancelLister.onDialogCancel();
                }
                dismiss();
            }
        });
    }

    public void setProgress(int progress) {
        progressBar.setProgress(progress);
    }

    public void setResourceText(String text) {
        progressTV.setText(text);
    }

    public void setOnClickCancelLister(OnClickCancelLister cancelLister) {
        this.cancelLister = cancelLister;
    }

    public interface OnClickCancelLister {
        void onDialogCancel();
    }
}
