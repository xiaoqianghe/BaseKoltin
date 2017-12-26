package com.xiaoqianghe.koltin.basekoltin.mvp.model

import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean
import com.xiaoqianghe.koltin.basekoltin.net.RetrofitManager
import com.xiaoqianghe.koltin.basekoltin.rx.scheduler.SchedulerUtils
import io.reactivex.Observable


/**
 *
 * Author：Wq
 * Date：2017/12/26 17:42
 * Description：//todo
 *
 *
 */
class RankModel {

    fun requestRankList(apiUrl:String):Observable<HomeBean.Issue>{

        return RetrofitManager.service.getIssueData(apiUrl)
                .compose(SchedulerUtils.ioToMain())
    }
}