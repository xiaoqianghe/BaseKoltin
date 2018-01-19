package com.xiaoqianghe.koltin.basekoltin.mvp.model


import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean
import com.xiaoqianghe.koltin.basekoltin.net.RetrofitManager
import com.xiaoqianghe.koltin.basekoltin.rx.scheduler.SchedulerUtils
import io.reactivex.Observable


/**
 *
 * Author：Wq
 * Date：2018/1/19 14:45
 * Description：//todo
 *
 *
 */
class SearchModel {



    //请求热门关键词的数据
    fun requestHotData(): Observable<ArrayList<String>>? {

        return RetrofitManager.service.getHotWord()
                .compose(SchedulerUtils.ioToMain())
    }


    fun  getSearchResultData(words:String): Observable<HomeBean.Issue>{

        return RetrofitManager.service.getSearchData(words)
                .compose(SchedulerUtils.ioToMain())

    }


    fun  getLoadMoreData(url:String): Observable<HomeBean.Issue>{
        return RetrofitManager.service.getIssueData(url)
                .compose(SchedulerUtils.ioToMain())
    }


}