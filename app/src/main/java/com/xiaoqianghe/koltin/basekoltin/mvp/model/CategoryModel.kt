package com.xiaoqianghe.koltin.basekoltin.mvp.model

import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.CategoryBean
import com.xiaoqianghe.koltin.basekoltin.net.RetrofitManager
import com.xiaoqianghe.koltin.basekoltin.rx.scheduler.SchedulerUtils
import io.reactivex.Observable


/**
 *
 * Author：Wq
 * Date：2017/12/26 16:23
 * Description：//todo
 *
 *
 */
class CategoryModel {

    fun getCategoryData():Observable<ArrayList<CategoryBean>>{

        return RetrofitManager.service.getCategory()
                .compose(SchedulerUtils.ioToMain())


    }
}