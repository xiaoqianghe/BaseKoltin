package com.xiaoqianghe.koltin.basekoltin.net

import android.util.Log


/**
 *
 * Author：Wq
 * Date：2018/1/11 11:07
 * Description：//todo
 *
 *
 */
class HttpLogger {
    private val TAG = "HttpLogger"
    private val mMessage = StringBuilder()

    fun log(message: String) {
        var message = message
        // 请求或者响应开始
        if (message.startsWith("--> POST")) {
            mMessage.setLength(0)
        }
        // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
        if (message.startsWith("{") && message.endsWith("}") || message.startsWith("[") && message.endsWith("]")) {
            message = TextConver.formatJson(message)
        }
        mMessage.append(message + "\n")
        // 请求或者响应结束，打印整条日志
        if (message.startsWith("<-- END HTTP")) {
            Log.d(TAG, mMessage.toString())
        }
    }

}