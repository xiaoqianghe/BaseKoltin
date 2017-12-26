package com.xiaoqianghe.koltin.basekoltin.mvp.model

import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.TabInfoBean
import com.xiaoqianghe.koltin.basekoltin.net.RetrofitManager
import com.xiaoqianghe.koltin.basekoltin.rx.scheduler.SchedulerUtils
import io.reactivex.Observable


/**
 *
 * Author：Wq
 * Date：2017/12/26 14:24
 * Description：//todo
 *
 *
 */
class HotTabModel {

    fun getTabInfo() : Observable<TabInfoBean>{

        return RetrofitManager.service.getRankList().compose(SchedulerUtils.ioToMain())

    }
}