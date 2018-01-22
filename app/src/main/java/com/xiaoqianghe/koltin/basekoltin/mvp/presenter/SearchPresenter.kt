package com.xiaoqianghe.koltin.basekoltin.mvp.presenter

import com.xiaoqianghe.basekoltin.net.exception.ExceptionHandle
import com.xiaoqianghe.koltin.basekoltin.base.BasePresenter
import com.xiaoqianghe.koltin.basekoltin.mvp.contract.SearchContract
import com.xiaoqianghe.koltin.basekoltin.mvp.model.SearchModel


/**
 *
 * Author：Wq
 * Date：2018/1/19 14:29
 * Description：//todo
 *
 *
 */
class SearchPresenter: BasePresenter<SearchContract.View>(),SearchContract.presenter{

    private var nextPageUrl : String? =null

    private val searchModel by lazy {
        SearchModel()
    }


    override fun querySearchData(words: String) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.


        checkViewAttached()
        mRootView?.apply {
            closeSoftKeyboard()
            showLoading()
        }
        addSubscription(disposable = searchModel.getSearchResultData(words)
                .subscribe({ issue ->
                    mRootView?.apply {
                        dismissLoading()
                        if (issue.count > 0 && issue.itemList.size > 0) {
                            nextPageUrl = issue.nextPageUrl
                            setSearchResult(issue)
                        } else
                            setEmptyView()
                    }
                }, { throwable ->
                    mRootView?.apply {
                        dismissLoading()
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable),ExceptionHandle.errorCode)
                    }
                })
        )

    }




    override fun requestHotWordData() {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        checkViewAttached()
        checkViewAttached()
        mRootView?.apply {
            closeSoftKeyboard()
            showLoading()
        }
        addSubscription(disposable = searchModel.requestHotData()
                .subscribe({ string ->
                    mRootView?.apply {
                        setHotWordData(string)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable),ExceptionHandle.errorCode)
                    }
                }))



    }



    override fun loadMoreData() {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        checkViewAttached()
        nextPageUrl?.let {
            addSubscription(disposable = searchModel.getLoadMoreData(it)
                    .subscribe({ issue ->
                        mRootView?.apply {
                            nextPageUrl = issue.nextPageUrl
                            setSearchResult(issue)
                        }
                    }, { throwable ->
                        mRootView?.apply {
                            //处理异常
                            showError(ExceptionHandle.handleException(throwable),ExceptionHandle.errorCode)
                        }
                    }))
        }



    }


}