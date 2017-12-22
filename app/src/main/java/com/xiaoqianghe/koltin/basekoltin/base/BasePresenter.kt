package com.xiaoqianghe.koltin.basekoltin.base

import io.reactivex.disposables.CompositeDisposable


/**
 *
 * Author：Wq
 * Date：2017/12/21 15:30
 * Description：//todo
 *
 *
 */
open class BasePresenter <T : IBaseView> : IPresenter<T>{

    var mRootView : T? = null
        private set
    private var compositeDisposable = CompositeDisposable()


    override fun attachView(mRootView: T) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        this.mRootView=mRootView

    }

    override fun detachView() {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        mRootView=null

        if(compositeDisposable.isDisposed){
            compositeDisposable.clear()
        }
    }


    private val isViewAttached : Boolean
        get() = mRootView!=null



    fun checkViewAttached(){

        if(!isViewAttached) throw MvpViewNotAttachedException()
    }


    class MvpViewNotAttachedException internal constructor(): RuntimeException("Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter")



}


