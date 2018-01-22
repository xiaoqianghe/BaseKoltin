package com.xiaoqianghe.koltin.basekoltin.mvp.model

import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean
import com.xiaoqianghe.koltin.basekoltin.net.RetrofitManager
import com.xiaoqianghe.koltin.basekoltin.rx.scheduler.SchedulerUtils
import io.reactivex.Observable
import io.reactivex.Scheduler


/**
 *
 * Author：Wq
 * Date：2018/1/22 10:49
 * Description：//todo
 *
 *
 */
class CategoryDetailModel {

    fun getCategoryDetailList(id:Long):Observable<HomeBean.Issue>{

        return RetrofitManager.service.getCategoryDetailList(id)
                .compose(SchedulerUtils.ioToMain())

    }


    fun  loadMoreData(url:String):Observable<HomeBean.Issue>{

        return RetrofitManager.service.getIssueData(url)
                .compose(SchedulerUtils.ioToMain())

    }
}