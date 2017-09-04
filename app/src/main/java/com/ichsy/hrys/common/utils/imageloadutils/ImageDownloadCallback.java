package com.ichsy.hrys.common.utils.imageloadutils;

import java.io.File;

/**
 * author: ihesen on 2016/11/4 15:10
 * email: hesen@ichsy.com
 */

public interface ImageDownloadCallback {

    void onSuccess(File file);

    void onException(Exception exception);
}
