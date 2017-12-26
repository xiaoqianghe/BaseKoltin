package com.xiaoqianghe.koltin.basekoltin.mvp.presenter

import com.xiaoqianghe.basekoltin.net.exception.ExceptionHandle
import com.xiaoqianghe.koltin.basekoltin.base.BasePresenter
import com.xiaoqianghe.koltin.basekoltin.mvp.contract.VideoDetailContract
import com.xiaoqianghe.koltin.basekoltin.mvp.model.VideoDetailModel
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean


/**
 *
 * Author：Wq
 * Date：2017/12/25 14:57
 * Description：//todo
 *
 *
 */
class VideoDetailPresenter : BasePresenter<VideoDetailContract.View>(),VideoDetailContract.Presenter{



    private val videoDetailModel : VideoDetailModel by lazy {

        VideoDetailModel()
    }

    override fun loadVideoInfo(itemInfo: HomeBean.Issue.Item) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.




    }

    override fun requestRelateVideo(id: Long) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        mRootView?.showLoading()

        val  disposable= videoDetailModel.requestRelatedData(id)
                .subscribe({

                    issue ->
                    mRootView?.apply {
                        dismissLoading()
                        setRecentRelatedVideo(issue.itemList)
                    }
                }, { t ->
                    mRootView?.apply {
                        dismissLoading()
                        setErrorMsg(ExceptionHandle.handleException(t))
                    }
                })

        addSubscription(disposable)

    }
}