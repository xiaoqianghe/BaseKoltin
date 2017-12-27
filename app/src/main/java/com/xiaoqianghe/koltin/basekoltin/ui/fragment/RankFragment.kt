package com.xiaoqianghe.koltin.basekoltin.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.xiaoqianghe.basekoltin.net.exception.ErrorStatus
import com.xiaoqianghe.koltin.basekoltin.R
import com.xiaoqianghe.koltin.basekoltin.base.BaseFragment
import com.xiaoqianghe.koltin.basekoltin.mvp.contract.RankContract
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean
import com.xiaoqianghe.koltin.basekoltin.mvp.presenter.RankPresenter
import com.xiaoqianghe.koltin.basekoltin.showToast
import com.xiaoqianghe.koltin.basekoltin.ui.adapter.CategoryDetailAdapter
import kotlinx.android.synthetic.main.fragment_rank.*


/**
 *
 * Author：Wq
 * Date：2017/12/26 17:57
 * Description：//todo
 *
 *
 */
class RankFragment: BaseFragment(),RankContract.View {


    private val mPresenter by lazy {

        RankPresenter()
    }

    private val mAdapter by lazy { CategoryDetailAdapter(activity, itemList, R.layout.item_category_detail) }


    private var itemList=ArrayList<HomeBean.Issue.Item>()

    private var apiUrl: String? =null


    companion object {
        fun getInstance(apiUrl:String):RankFragment{
            val fragment =RankFragment()
            val bundle = Bundle()
            fragment.arguments=bundle
            fragment.apiUrl=apiUrl
            return fragment
        }
    }

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

    override fun setRankList(itemList: ArrayList<HomeBean.Issue.Item>) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        mAdapter.addData(itemList)

    }

    override fun showError(errorMsg: String, errorCode: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    showToast(errorMsg)

        if(errorCode==ErrorStatus.NETWORK_ERROR){

            multipleStatusView.showNoNetwork()

        }else{
            multipleStatusView.showError()
        }

    }

    override fun lazyLoad() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        if(!apiUrl.isNullOrEmpty()){
            mPresenter.requestRankList(this!!.apiUrl!!)
        }


    }

    override fun initView() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mAdapter

        mLayoutStatusView =multipleStatusView

    }

    override fun getLayoutId(): Int {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    return R.layout.fragment_rank
    }
}