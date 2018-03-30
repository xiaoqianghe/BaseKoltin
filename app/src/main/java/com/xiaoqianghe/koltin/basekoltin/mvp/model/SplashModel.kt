package com.xiaoqianghe.koltin.basekoltin.mvp.model

import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.UpdataVersionRsBean
import com.xiaoqianghe.koltin.basekoltin.net.RetrofitManager
import com.xiaoqianghe.koltin.basekoltin.rx.scheduler.SchedulerUtils
import io.reactivex.Observable


/**
 *
 * Author：Wq
 * Date：2018/3/30 11:55
 * Description：//todo
 *
 *
 */
class SplashModel {

    fun  requestVersionData(): Observable<UpdataVersionRsBean>? {

        return RetrofitManager.service.checkVersionData()
                .compose(SchedulerUtils.ioToMain())


    }
}