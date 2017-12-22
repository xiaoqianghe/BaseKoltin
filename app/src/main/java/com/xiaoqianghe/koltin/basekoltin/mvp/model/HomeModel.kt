package com.xiaoqianghe.koltin.basekoltin.mvp.model

import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean
import com.xiaoqianghe.koltin.basekoltin.net.RetrofitManager
import io.reactivex.Observable
import com.xiaoqianghe.koltin.basekoltin.rx.scheduler.SchedulerUtils


/**
 *
 * Author：Wq
 * Date：2017/12/21 15:33
 * Description：//todo
 *
 *
 */
class HomeModel {



    fun requestHomeData(num:Int) : Observable<HomeBean>{
        return RetrofitManager.service.getFirstHomeData(num)
                .compose(SchedulerUtils.ioToMain())




    }

    fun loadMoreData(url:String) : Observable<HomeBean>{

        return RetrofitManager.service.getMoreHomeData(url)
                .compose(SchedulerUtils.ioToMain())



    }


}