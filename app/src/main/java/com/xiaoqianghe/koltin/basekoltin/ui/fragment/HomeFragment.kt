package com.xiaoqianghe.koltin.basekoltin.ui.fragment

import android.app.ActivityManager
import android.os.Bundle
import android.support.v4.media.session.PlaybackStateCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.hazz.kotlinmvp.net.exception.ErrorStatus

import com.orhanobut.logger.Logger
import com.scwang.smartrefresh.header.MaterialHeader
import com.xiaoqianghe.koltin.basekoltin.R
import com.xiaoqianghe.koltin.basekoltin.base.BaseActivity
import com.xiaoqianghe.koltin.basekoltin.base.BaseFragment
import com.xiaoqianghe.koltin.basekoltin.mvp.contract.HomeContract
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean
import com.xiaoqianghe.koltin.basekoltin.mvp.presenter.HomePresenter
import com.xiaoqianghe.koltin.basekoltin.showToast
import com.xiaoqianghe.koltin.basekoltin.ui.adapter.HomeAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*


/**
 *
 * Author：Wq
 * Date：2017/12/21 10:02
 * Description：//todo
 *
 *
 */
class HomeFragment : BaseFragment(),HomeContract.View {

    private val mPresenter by lazy {

       HomePresenter()
    }


    private var mTitle: String? =null

    private var num: Int =1


    private var mHomeAdapter : HomeAdapter? =null

    private var loadingMore =false

    private var isRefresh =false

    private var mMaterialHeader: MaterialHeader? =null


    companion object {
        fun  getInstance(title: String) :HomeFragment{

            val fragment =HomeFragment()
            val bundle =Bundle()

            fragment.arguments=bundle
            fragment.mTitle=title
            return fragment
        }
    }



    private val simpleDateFormat by lazy {

        SimpleDateFormat("- MMM. dd, 'Brunch' -", Locale.ENGLISH)

    }




    /**
     *
     * @todo :
     *
     * */
    private val linearLayoutManager by lazy {


        LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
    }


    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setHomeData(homeBean: HomeBean) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Logger.d(homeBean)

        // Adapter
        mHomeAdapter = HomeAdapter(activity, homeBean.issueList[0].itemList)
        //设置 banner 大小
        mHomeAdapter?.setBannerSize(homeBean.issueList[0].count)

        mRecyclerView.adapter = mHomeAdapter
        mRecyclerView.layoutManager = linearLayoutManager
        mRecyclerView.itemAnimator = DefaultItemAnimator()

    }

    override fun setMoreData(itemList: ArrayList<HomeBean.Issue.Item>) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        loadingMore = false
        mHomeAdapter?.addItemData(itemList)

    }

    override fun showError(msg: String, errorCode: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        showToast(msg)

        if(errorCode == ErrorStatus.NETWORK_ERROR){
            mLayoutStatusView?.showNoNetwork()
        }else{

            mLayoutStatusView?.showError()

        }



    }

    override fun lazyLoad() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.


    }

    override fun initView() {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        mPresenter.attachView(this)

        mRefreshLayout.setEnableHeaderTranslationContent(true)

        mRefreshLayout.setOnRefreshListener {
            isRefresh=true
            mPresenter.requestHomeData(num)
        }

        mMaterialHeader = mRefreshLayout.refreshHeader as MaterialHeader?
        //打开下拉刷新区域块背景:
        mMaterialHeader?.setShowBezierWave(true)
        //设置下拉刷新主题颜色
        mRefreshLayout.setPrimaryColorsId(R.color.color_light_black, R.color.color_title_bg)

        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){


            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val currentVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()

                if (currentVisibleItemPosition == 0) {
                    //背景设置为透明
                    toolbar.setBackgroundColor(getColor(R.color.color_translucent))
                    iv_search.setImageResource(R.mipmap.ic_action_search_white)
                    tv_header_title.text = ""
                } else {
                    if (mHomeAdapter?.mData!!.size > 1) {
                        toolbar.setBackgroundColor(getColor(R.color.color_title_bg))
                        iv_search.setImageResource(R.mipmap.ic_action_search_black)
                        val itemList = mHomeAdapter!!.mData
                        val item = itemList[currentVisibleItemPosition + mHomeAdapter!!.bannerItemSize - 1]
                        if (item.type == "textHeader") {
                            tv_header_title.text = item.data?.text
                        } else {
                            tv_header_title.text = simpleDateFormat.format(item.data?.date)
                        }
                    }
                }


            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if(newState == RecyclerView.SCROLL_STATE_IDLE){

                    val childCount = mRecyclerView.childCount
                    val itemCount =mRecyclerView.layoutManager.itemCount

                    val firstVisibleItem=(mRecyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                    if(!loadingMore){
                        loadingMore=true

                        mPresenter.loadMoreData()
                    }
                }
            }
        })







    }

    override fun getLayoutId(): Int {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        return R.layout.fragment_home

    }


    fun getColor(colorId: Int): Int {
        return resources.getColor(colorId)
    }
}