package com.xiaoqianghe.koltin.basekoltin.mvp.presenter

import com.xiaoqianghe.basekoltin.net.exception.ExceptionHandle
import com.xiaoqianghe.koltin.basekoltin.base.BasePresenter
import com.xiaoqianghe.koltin.basekoltin.mvp.contract.CategoryContract
import com.xiaoqianghe.koltin.basekoltin.mvp.model.CategoryModel


/**
 *
 * Author：Wq
 * Date：2017/12/26 16:28
 * Description：//todo
 *
 *
 */
class CategoryPresenter:BasePresenter<CategoryContract.View>(),CategoryContract.Presenter {

    private val categoryModel: CategoryModel by lazy {
        CategoryModel()
    }
    override fun getCategoryData() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        checkViewAttached()

        mRootView?.showLoading()

        val disposable =categoryModel.getCategoryData()
                .subscribe(
                        {categoryList ->
                            mRootView?.apply {

                                dismissLoading()
                                showCategory(categoryList)
                            }

                        },{t->

                    mRootView?.apply {
                        dismissLoading()
                        showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
                    }

                }

                )

        addSubscription(disposable)

    }
}