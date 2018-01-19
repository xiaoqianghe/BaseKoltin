package com.xiaoqianghe.koltin.basekoltin.mvp.presenter

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
    override fun querySearchData(words: String) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        checkViewAttached()

//        mRootView.apply {
//
//            closeSoftKeyboard()
//            showLoading()
//        }
//
//        addSubscription(disposable = searchModel.getSearchResultData(words)
//                .)

    }


    private var nextPageUrl : String? =null

    private val searchModel by lazy {
        SearchModel()
    }

    override fun requestHotWordData() {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        checkViewAttached()
        checkViewAttached()



    }



    override fun loadMoreData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}