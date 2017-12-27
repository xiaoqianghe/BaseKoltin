package com.xiaoqianghe.koltin.basekoltin.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import com.xiaoqianghe.basekoltin.net.exception.ErrorStatus
import com.xiaoqianghe.basekoltin.utils.StatusBarUtil
import com.xiaoqianghe.koltin.basekoltin.R
import com.xiaoqianghe.koltin.basekoltin.base.BaseFragment
import com.xiaoqianghe.koltin.basekoltin.base.BaseFragmentAdapter
import com.xiaoqianghe.koltin.basekoltin.mvp.contract.HomeContract
import com.xiaoqianghe.koltin.basekoltin.mvp.contract.HotTabContract
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.TabInfoBean
import com.xiaoqianghe.koltin.basekoltin.mvp.presenter.HotTabPresenter
import com.xiaoqianghe.koltin.basekoltin.showToast
import kotlinx.android.synthetic.main.fragment_hot.*


/**
 *
 * Author：Wq
 * Date：2017/12/21 10:04
 * Description：//todo
 *
 *
 */
class HotFragment : BaseFragment(),HomeContract.View, HotTabContract.View {



    private val mPresenter by lazy { HotTabPresenter() }

    private var mTitle : String? =null

    private val mTabTitleList= ArrayList<String>()

    private val mFragmentList= ArrayList<Fragment>()

    companion object {
        fun getInstance(title:String) :HotFragment{

            val fragment =HotFragment()
            val bundle =Bundle()

            fragment.arguments=bundle
            fragment.mTitle=title

            return fragment
        }
    }

    override fun setTabInfo(tabInfoBean: TabInfoBean) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        tabInfoBean.tabInfo.tabList.mapTo(mTabTitleList) { it.name }
        tabInfoBean.tabInfo.tabList.mapTo(mFragmentList) { RankFragment.getInstance(it.apiUrl) }

        mViewPager.adapter = BaseFragmentAdapter(childFragmentManager,mFragmentList,mTabTitleList)
        mTabLayout.setupWithViewPager(mViewPager)

    }

    init {
        mPresenter.attachView(this)
    }



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

    override fun showError(errorMsg: String,errorCode:Int) {
        showToast(errorMsg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            multipleStatusView.showNoNetwork()
        } else {
            multipleStatusView.showError()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun lazyLoad() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    mPresenter.getTableInfo()

    }

    override fun initView() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        mLayoutStatusView = multipleStatusView
        //状态栏透明和间距处理
        StatusBarUtil.darkMode(activity)
        StatusBarUtil.setPaddingSmart(activity, toolbar)

    }

    override fun getLayoutId(): Int {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    return R.layout.fragment_hot

    }
}