package com.xiaoqianghe.koltin.basekoltin.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.classic.common.MultipleStatusView


/**
 *
 * Author：Wq
 * Date：2017/12/20 15:31
 * Description：//todo
 *
 *
 */
abstract class BaseFragment: Fragment() {




    /**
     * 视图是否加载完毕
     */
    private var isViewPrepare = false
    /**
     * 数据是否加载过了
     */
    private var hasLoadData = false




    protected  var mLayoutStatusView: MultipleStatusView? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(getLayoutId(),null)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isViewPrepare = true
        initView()

        lazyLoadDataIfPrepared()


        //多种状态切换的View 重试点击事件

        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }

    private fun lazyLoadDataIfPrepared() {
        if (userVisibleHint && isViewPrepare && !hasLoadData) {
            lazyLoad()
            hasLoadData = true
        }
    }

    abstract fun lazyLoad()

    abstract fun initView()

    abstract fun getLayoutId(): Int





    open val mRetryClickListener: View.OnClickListener=View.OnClickListener {

        lazyLoad()
    }


    override fun onDestroy() {
        super.onDestroy()
    }



    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared()
        }
    }

}

