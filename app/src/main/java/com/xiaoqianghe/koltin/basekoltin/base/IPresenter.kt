package com.xiaoqianghe.koltin.basekoltin.base


/**
 *
 * Author：Wq
 * Date：2017/12/20 15:28
 * Description：//todo
 *
 *
 */
interface IPresenter<in V :IBaseView> {

    fun  attachView(mRootView:V)

    fun detachView()
}