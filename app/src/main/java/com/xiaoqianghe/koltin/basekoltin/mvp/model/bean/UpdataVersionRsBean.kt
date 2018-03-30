package com.xiaoqianghe.koltin.basekoltin.mvp.model.bean

import java.net.HttpURLConnection


/**
 *
 * Author：Wq
 * Date：2018/3/30 10:24
 * Description：//todo
 *
 *
 */
data class UpdataVersionRsBean(val status: Boolean,val msg:String,val mDataBean: DataBean ) {

     data class DataBean(val appUrl:String,val downFlag:String,val verDesc:String,val versionCode:String,val version:String)
}