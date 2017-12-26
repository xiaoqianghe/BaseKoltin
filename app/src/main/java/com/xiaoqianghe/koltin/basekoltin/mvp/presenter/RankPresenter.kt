package com.xiaoqianghe.koltin.basekoltin.mvp.presenter

import com.xiaoqianghe.basekoltin.net.exception.ExceptionHandle
import com.xiaoqianghe.koltin.basekoltin.base.BasePresenter
import com.xiaoqianghe.koltin.basekoltin.mvp.contract.RankContract
import com.xiaoqianghe.koltin.basekoltin.mvp.model.RankModel
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean


/**
 *
 * Author：Wq
 * Date：2017/12/26 17:49
 * Description：//todo
 *
 *
 */
class RankPresenter :BasePresenter<RankContract.View>(),RankContract.Presenter{


    private val rankModel by lazy {
        RankModel()
    }
    override fun requestRankList(apiUrl: String) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        checkViewAttached()
        mRootView?.showLoading()

        val disposable = rankModel.requestRankList(apiUrl)
                .subscribe({
                    issue ->
                    mRootView?.apply {
                        dismissLoading()
                        setRankList(issue.itemList)
                    }

                },{
                    throwable ->
                    mRootView?.apply {

                        dismissLoading()
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)

                    }

                })

        addSubscription(disposable)
    }

}