package com.xiaoqianghe.koltin.basekoltin.mvp.presenter

import com.xiaoqianghe.koltin.basekoltin.base.BasePresenter
import com.xiaoqianghe.koltin.basekoltin.mvp.contract.SplashContract
import com.xiaoqianghe.koltin.basekoltin.mvp.model.SplashModel


/**
 *
 * Author：Wq
 * Date：2018/3/30 10:06
 * Description：//todo
 *
 *
 */
class SplashPresenter: BasePresenter<SplashContract.View>(),SplashContract.Presenter {



    private val splashModel by lazy{
        SplashModel()
    }

    override fun checkViewData() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        checkViewAttached()



        splashModel.requestVersionData()


    }
}