package com.xiaoqianghe.koltin.basekoltin.ui.fragment

import android.os.Bundle
import com.xiaoqianghe.koltin.basekoltin.R
import com.xiaoqianghe.koltin.basekoltin.base.BaseFragment
import com.xiaoqianghe.koltin.basekoltin.mvp.contract.CategoryContract
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.CategoryBean
import com.xiaoqianghe.koltin.basekoltin.mvp.presenter.CategoryPresenter


/**
 *
 * Author：Wq
 * Date：2017/12/26 15:31
 * Description：//todo
 *
 *
 */
class CategoryFragment :BaseFragment(),CategoryContract.View {

    private val mPresenter by lazy {
        CategoryPresenter()
    }

    private var mTitle : String? =null

    private val categoryList =ArrayList<CategoryBean>()


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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showCategory(categorys: ArrayList<CategoryBean>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(handleException: String, errorCode: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun lazyLoad() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutId(): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        return R.layout.fragment_category
    }
}