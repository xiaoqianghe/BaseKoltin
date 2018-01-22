package com.xiaoqianghe.koltin.basekoltin.ui.activity

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.xiaoqianghe.basekoltin.utils.StatusBarUtil
import com.xiaoqianghe.koltin.basekoltin.Constants
import com.xiaoqianghe.koltin.basekoltin.R

import com.xiaoqianghe.koltin.basekoltin.base.BaseActivity
import com.xiaoqianghe.koltin.basekoltin.mvp.contract.CategoryDetailContract
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.CategoryBean
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean
import com.xiaoqianghe.koltin.basekoltin.mvp.presenter.CategoryDetailPresenter
import com.xiaoqianghe.koltin.basekoltin.mvp.presenter.CategoryPresenter
import com.xiaoqianghe.koltin.basekoltin.ui.adapter.CategoryDetailAdapter
import com.xiaoqianghe.koltin.basekoltin.utils.GlideUtils
import kotlinx.android.synthetic.main.activity_category_detail.*



/**
 *
 * Author：Wq
 * Date：2018/1/22 10:09
 * Description：//todo
 *
 *
 */
class CategoryDetailActivity : BaseActivity(),CategoryDetailContract.View{


    private val mPresenter by lazy {

        CategoryDetailPresenter()
    }

    private val mAdapter by lazy {

        CategoryDetailAdapter(this,itemList, R.layout.item_category_detail)
    }


    private var categoryData: CategoryBean? =null

    private var itemList=ArrayList<HomeBean.Issue.Item>()

    init {
        mPresenter.attachView(this)
    }

    private var loadingMore=false


    override fun showLoading() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    multipleStatusView.showLoading()

    }

    override fun dismissLoading() {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

     multipleStatusView.showContent()
    }

    override fun setCateDetailList(itemList: ArrayList<HomeBean.Issue.Item>) {
      //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.


        loadingMore = false
        mAdapter.addData(itemList)



    }

    override fun showError(errorMsg: String) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
      multipleStatusView.showError()

    }

    override fun initStart() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initView() {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        // 加载headerImage
//        GlideApp.with(this)
//                .load(categoryData?.headerImage)
//                .placeholder(R.color.color_darker_gray)
//                .into(imageView)


        GlideUtils.load(this, categoryData?.headerImage,imageView)
        tv_category_desc.text ="#${categoryData?.description}#"

        collapsing_toolbar_layout.title = categoryData?.name
        collapsing_toolbar_layout.setExpandedTitleColor(Color.WHITE) //设置还没收缩时状态下字体颜色
        collapsing_toolbar_layout.setCollapsedTitleTextColor(Color.BLACK) //设置收缩后Toolbar上字体的颜色


        mRecyclerView
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mAdapter
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

        //状态栏透明和间距处理
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, toolbar)



    }

    override fun initData() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    categoryData=intent.getSerializableExtra(Constants.BUNDLE_CATEGORY_DATA) as CategoryBean

    }

    override fun start() {
      //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    mPresenter.getCategoryDetailList(categoryData!!.id)

    }

    override fun initLayoutId(): Int {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    return R.layout.activity_category_detail
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}