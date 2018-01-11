package com.xiaoqianghe.koltin.basekoltin.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.xiaoqianghe.basekoltin.net.exception.ErrorStatus
import com.xiaoqianghe.koltin.basekoltin.R
import com.xiaoqianghe.koltin.basekoltin.base.BaseFragment
import com.xiaoqianghe.koltin.basekoltin.mvp.contract.FollowContract
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean
import com.xiaoqianghe.koltin.basekoltin.mvp.presenter.FollowPresenter
import com.xiaoqianghe.koltin.basekoltin.showToast
import com.xiaoqianghe.koltin.basekoltin.ui.adapter.FollowAdapter
import kotlinx.android.synthetic.main.layout_recyclerview.*


/**
 *
 * Author：Wq
 * Date：2017/12/26 15:30
 * Description：//todo
 *
 *
 */
class FollowFragment :BaseFragment(),FollowContract.View {

    private var  mTitle : String? =null


    private var  itemList=ArrayList<HomeBean.Issue.Item>()


    private val mPresenter : FollowPresenter by lazy {

        FollowPresenter()
    }

    private val mFollowAdapter by lazy {

        FollowAdapter(activity,itemList)


    }

    companion object {
        fun getInstance(title:String):FollowFragment{


            val fragment =FollowFragment()
            val bundle=Bundle()

            fragment.arguments=bundle
            fragment.mTitle=title

            return fragment
        }
    }

    /**
     * 是否加载更多
     */
    private var loadingMore = false

    init {
        mPresenter.attachView(this)
    }

    override fun showLoading() {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        multipleStatusView.showLoading()
    }

    override fun dismissLoading() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        multipleStatusView.showContent()

    }

    override fun setFollowInfo(issue: HomeBean.Issue) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        loadingMore = false
        itemList = issue.itemList
        mFollowAdapter.addData(itemList)

    }

    override fun showError(errorMsg: String, errorCode: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        showToast(errorMsg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            multipleStatusView.showNoNetwork()
        } else {
            multipleStatusView.showError()
        }

    }

    override fun lazyLoad() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        mPresenter.requestFollowList()

    }

    override fun initView() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mFollowAdapter
        //实现自动加载
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val itemCount = mRecyclerView.layoutManager.itemCount
                val lastVisibleItem = (mRecyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (!loadingMore && lastVisibleItem == (itemCount - 1)) {
                    loadingMore = true
                    mPresenter.loadMoreData()
                }
            }
        })

        mLayoutStatusView = multipleStatusView

    }

    override fun getLayoutId(): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return R.layout.layout_recyclerview
    }

}