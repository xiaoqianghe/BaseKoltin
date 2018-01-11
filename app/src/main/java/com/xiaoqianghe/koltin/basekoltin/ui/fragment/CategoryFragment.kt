package com.xiaoqianghe.koltin.basekoltin.ui.fragment

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.xiaoqianghe.basekoltin.net.exception.ErrorStatus
import com.xiaoqianghe.basekoltin.net.exception.ExceptionHandle.Companion.errorMsg
import com.xiaoqianghe.basekoltin.utils.DisplayManager
import com.xiaoqianghe.koltin.basekoltin.R
import com.xiaoqianghe.koltin.basekoltin.base.BaseFragment
import com.xiaoqianghe.koltin.basekoltin.mvp.contract.CategoryContract
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.CategoryBean
import com.xiaoqianghe.koltin.basekoltin.mvp.presenter.CategoryPresenter
import com.xiaoqianghe.koltin.basekoltin.showToast
import com.xiaoqianghe.koltin.basekoltin.ui.adapter.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_category.*


/**
 *
 * Author：Wq
 * Date：2017/12/26 15:31
 * Description：//todo
 *
 *
 */
class CategoryFragment : BaseFragment(),CategoryContract.View {
    override fun lazyLoad() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        mPresenter.getCategoryData()
    }

    private val mPresenter by lazy {
        CategoryPresenter()
    }

    private val mAdapter by lazy { CategoryAdapter(activity, mCategoryList, R.layout.item_category) }


    private var mTitle : String? =null

    private var mCategoryList =ArrayList<CategoryBean>()


    companion object {
        fun  getInstance(titl: String):CategoryFragment{

            val fragment= CategoryFragment()

            val bundle=Bundle()

            fragment.arguments=bundle
            fragment.mTitle=titl
            return fragment

        }
    }


    override fun showLoading() {
      //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        multipleStatusView.showLoading()


    }

    override fun dismissLoading() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        multipleStatusView.showContent()

    }

    override fun showCategory(categorys: ArrayList<CategoryBean>) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        mCategoryList=categorys
        mAdapter.setData(mCategoryList)
    }

    override fun showError(handleException: String, errorCode: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        showToast(handleException)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            multipleStatusView.showNoNetwork()
        } else {
            multipleStatusView.showError()
        }

    }


    @Suppress("DEPRECATION")
    override fun initView() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        mPresenter.attachView(this)


        mLayoutStatusView = multipleStatusView

        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = GridLayoutManager(activity,2)
        mRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
                val position = parent.getChildPosition(view)
                val offset = DisplayManager.dip2px(2f)!!

                outRect.set(if (position % 2 == 0) 0 else offset, offset,
                        if (position % 2 == 0) offset else 0, offset)
            }

        })

        //状态栏透明和间距处理
//        StatusBarUtil.darkMode(activity)
//        StatusBarUtil.setPaddingSmart(activity, toolbar)
//        StatusBarUtil.setPaddingSmart(activity,mRecyclerView)

    }

    override fun getLayoutId(): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        return R.layout.fragment_category
    }



    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}