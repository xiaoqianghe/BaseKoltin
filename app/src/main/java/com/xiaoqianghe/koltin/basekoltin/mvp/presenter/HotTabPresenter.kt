package com.xiaoqianghe.koltin.basekoltin.mvp.presenter

import com.xiaoqianghe.basekoltin.net.exception.ExceptionHandle
import com.xiaoqianghe.koltin.basekoltin.base.BasePresenter
import com.xiaoqianghe.koltin.basekoltin.mvp.contract.HotTabContract
import com.xiaoqianghe.koltin.basekoltin.mvp.model.HotTabModel


/**
 *
 * Author：Wq
 * Date：2017/12/26 14:31
 * Description：//todo
 *
 *
 */
class HotTabPresenter : BasePresenter<HotTabContract.View>(),HotTabContract.Presenter {

    private val hottabModel by lazy{
        HotTabModel()
    }
    override fun getTableInfo() {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        checkViewAttached()

        val disposable =hottabModel.getTabInfo()
                .subscribe({

                    tableInfo ->

                    mRootView?.setTabInfo(tableInfo)


                },{

                    throwable ->

                    mRootView?.showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)

                })

        addSubscription(disposable)
    }
}