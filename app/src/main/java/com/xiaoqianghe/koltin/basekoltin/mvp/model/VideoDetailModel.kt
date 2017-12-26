package com.xiaoqianghe.koltin.basekoltin.mvp.model

import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean
import com.xiaoqianghe.koltin.basekoltin.net.RetrofitManager
import com.xiaoqianghe.koltin.basekoltin.rx.scheduler.SchedulerUtils
import io.reactivex.Observable


/**
 *
 * Author：Wq
 * Date：2017/12/25 15:00
 * Description：//todo
 *
 *
 */
class VideoDetailModel {

    fun requestRelatedData(id : Long) : Observable<HomeBean.Issue>{

        return RetrofitManager.service.getRelatedData(id)
                .compose(SchedulerUtils.ioToMain())

    }
}