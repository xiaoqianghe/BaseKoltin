package com.xiaoqianghe.wqbaselib.utils;

import android.content.Context;

/**
 * Author：Wq
 * Date：2018/1/3 11:47
 * Description：//todo
 */

public class ContextUtils {

    private static Context mApplication;

    public static void init(Context context) {
        mApplication = context;
    }

    public static Context getApplication() {
        return mApplication;
    }
}
