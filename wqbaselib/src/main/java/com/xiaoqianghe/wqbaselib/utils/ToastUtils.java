package com.xiaoqianghe.wqbaselib.utils;

import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Author：Wq
 * Date：2018/1/3 11:49
 * Description：//todo
 */

public class ToastUtils {


    public static void showToastMessage(int resId){
        if (resId==0){
            return;
        }
        showToastMessage(ContextUtils.getApplication().getString(resId));
    }

    public static void showToastMessage(String toastString) {
        if (TextUtils.isEmpty(toastString)) {
            return;
        }

        Toast toast = Toast.makeText(ContextUtils.getApplication(), toastString, Toast.LENGTH_SHORT);
        toast.show();
    }


    public  static void showToastMessageCENTER(String toastString) {
        if (TextUtils.isEmpty(toastString)) {
            return;
        }
        Toast toast = Toast.makeText(ContextUtils.getApplication(), toastString,
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
