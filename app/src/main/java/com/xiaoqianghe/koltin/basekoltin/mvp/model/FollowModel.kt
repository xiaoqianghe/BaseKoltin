package com.xiaoqianghe.koltin.basekoltin.mvp.model

import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean
import com.xiaoqianghe.koltin.basekoltin.net.RetrofitManager
import com.xiaoqianghe.koltin.basekoltin.rx.scheduler.SchedulerUtils
import io.reactivex.Observable


/**
 *
 * Author：Wq
 * Date：2017/12/26 15:39
 * Description：//todo
 *
 *
 */
class FollowModel {

    fun requestFollowList() :Observable<HomeBean.Issue>{

        return RetrofitManager.service.getFollowInfo()
                .compose(SchedulerUtils.ioToMain())

    }

    fun  loadMoreData(url:String):Observable<HomeBean.Issue>{

        return RetrofitManager.service.getIssueData(url)
                .compose(SchedulerUtils.ioToMain())
    }
}