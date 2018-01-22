package com.xiaoqianghe.koltin.basekoltin.mvp.presenter

import com.xiaoqianghe.koltin.basekoltin.base.BasePresenter
import com.xiaoqianghe.koltin.basekoltin.mvp.contract.CategoryContract
import com.xiaoqianghe.koltin.basekoltin.mvp.contract.CategoryDetailContract
import com.xiaoqianghe.koltin.basekoltin.mvp.model.CategoryDetailModel
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean


/**
 *
 * Author：Wq
 * Date：2018/1/22 10:23
 * Description：//todo
 *
 *
 */
class CategoryDetailPresenter :BasePresenter<CategoryDetailContract.View>(),CategoryDetailContract.Presenter {
    private val categoryDetailModel  by lazy {

        CategoryDetailModel()
    }


    private var nextPageUrl : String? =null



    override fun getCategoryDetailList(id: Long) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        checkViewAttached()
        mRootView?.showLoading()

        val disposable= categoryDetailModel.getCategoryDetailList(id)
                .subscribe({
                    issue->
                    mRootView?.apply {
                        dismissLoading()
                        nextPageUrl=issue.nextPageUrl
                        setCateDetailList(issue.itemList)
                    }

                },{
                    t: Throwable? ->
                    mRootView?.apply {
                        dismissLoading()
                        showError(t.toString())
                    }
                })

        addSubscription(disposable)



    }

    override fun loadMoreData() {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val disposable = nextPageUrl?.let {
            categoryDetailModel.loadMoreData(it)
                    .subscribe({ issue ->
                        mRootView?.apply {
                            nextPageUrl = issue.nextPageUrl
                            setCateDetailList(issue.itemList)
                        }
                    }, { throwable ->
                        mRootView?.apply {
                            showError(throwable.toString())
                        }
                    })
        }

        disposable?.let { addSubscription(it) }


    }



}