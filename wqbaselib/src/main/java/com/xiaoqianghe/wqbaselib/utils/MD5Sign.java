package com.xiaoqianghe.wqbaselib.utils;

import com.eeepay.libbase.util.MD5;

/**
 * Created by Administrator on 2018/3/20.
 */

public class MD5Sign {
    private final static String factor = "superbankgetkey123456789!@#$%^&*(";

    public static String getHomeSign(String userid) {
        String timeStamp = System.currentTimeMillis() + "";
        String key = userid + timeStamp + factor;
        return "&timestamp=" + timeStamp + "&sign=" + MD5.getMessageDigest(key.getBytes());
    }

    public static String getShareSign(String userid, String orgid) {
        String key = userid + orgid + factor;
        return "&sign=" + MD5.getMessageDigest(key.getBytes());
    }

    public static String getUserSign(String userid) {
        String key = userid + "&superBankKey!@#$%^&*5646665656check";
        return MD5.getMessageDigest(key.getBytes());
    }
}
