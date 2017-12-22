package com.xiaoqianghe.koltin.basekoltin.mvp.presenter

import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import com.xiaoqianghe.koltin.basekoltin.base.BaseFragment
import com.xiaoqianghe.koltin.basekoltin.base.BasePresenter
import com.xiaoqianghe.koltin.basekoltin.mvp.contract.HomeContract


/**
 *
 * Author：Wq
 * Date：2017/12/21 14:38
 * Description：//todo
 *
 *
 */
class HomePresenter :BasePresenter<HomeContract.View>(),HomeContract.Presenter {

    private var bannerHomeBean : HomeBean? = null

    private var nextPagerUrl : String? =null






    override fun requestHomeData(num: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadMoreData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}