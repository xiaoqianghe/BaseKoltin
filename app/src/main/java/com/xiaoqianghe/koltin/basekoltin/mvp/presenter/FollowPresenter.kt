package com.xiaoqianghe.koltin.basekoltin.mvp.presenter

import com.xiaoqianghe.basekoltin.net.exception.ExceptionHandle
import com.xiaoqianghe.koltin.basekoltin.base.BasePresenter
import com.xiaoqianghe.koltin.basekoltin.mvp.contract.FollowContract
import com.xiaoqianghe.koltin.basekoltin.mvp.model.FollowModel
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean


/**
 *
 * Author：Wq
 * Date：2017/12/26 15:46
 * Description：//todo
 *
 *
 */
class FollowPresenter : BasePresenter<FollowContract.View>(),FollowContract.Presenter{



    private var nextPageUrl:String?=null

    /**
     *  请求关注数据
     */
    override fun requestFollowList() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = followModel.requestFollowList()
                .subscribe({ issue ->
                    mRootView?.apply {
                        dismissLoading()
                        nextPageUrl = issue.nextPageUrl
                        setFollowInfo(issue)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        dismissLoading()
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable),ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    /**
     * 加载更多
     */
    override fun loadMoreData(){
        val disposable = nextPageUrl?.let {
            followModel.loadMoreData(it)
                    .subscribe({ issue->
                        mRootView?.apply {
                            nextPageUrl = issue.nextPageUrl
                            setFollowInfo(issue)
                        }

                    },{ t ->
                        mRootView?.apply {
                            showError(ExceptionHandle.handleException(t),ExceptionHandle.errorCode)
                        }
                    })


        }
        if (disposable != null) {
            addSubscription(disposable)
        }
    }


    private val followModel :FollowModel by lazy {

        FollowModel()
    }



}