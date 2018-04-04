package com.xiaoqianghe.wqbaselib.utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Author：Wq
 * Date：2018/1/10 15:34
 * Description：//todo
 */

public class StringUtils {

    /**
     * @// TODO: 2018/1/10  字符串保留两位小数 
     * 
     * */
    public static String float2(String value){
        if (null==value||TextUtils.isEmpty(value) || ".".equals(value))
            return "0.00";
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.toString();
    }

    /**
     * 字符型数字转化成double类型：保留两位小数
     *
     * @param str
     * @return
     */
    public static double str2Double(String str) {//11.0  11.25896
        double digit;
        str = ".".equals(str) ? "0" : str;
        str = TextUtils.isEmpty(str) ? "0" : str;
        str = float2(str);//11.00   11.26
        digit = Double.valueOf(str);
        return digit;//11.0   11.26
    }
}
