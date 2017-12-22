package com.xiaoqianghe.koltin.basekoltin.ui.fragment

import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import com.xiaoqianghe.koltin.basekoltin.base.BaseActivity
import com.xiaoqianghe.koltin.basekoltin.base.BaseFragment
import com.xiaoqianghe.koltin.basekoltin.mvp.contract.HomeContract


/**
 *
 * Author：Wq
 * Date：2017/12/21 10:02
 * Description：//todo
 *
 *
 */
class HomeFragment : BaseFragment(),HomeContract.View {

    private val mPresenter by lazy {}


    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setHomeData(homeBean: HomeBean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setMoreData(itemList: ArrayList<HomeBean.Issue.Item>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(msg: String, errorCode: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun lazyLoad() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}