package com.xiaoqianghe.koltin.basekoltin.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.xiaoqianghe.basekoltin.utils.StatusBarUtil
import com.xiaoqianghe.koltin.basekoltin.R
import com.xiaoqianghe.koltin.basekoltin.base.BaseFragment
import com.xiaoqianghe.koltin.basekoltin.base.BaseFragmentAdapter
import com.xiaoqianghe.koltin.basekoltin.view.TabLayoutHelper

import kotlinx.android.synthetic.main.fragment_hot.*


/**
 *
 * Author：Wq
 * Date：2017/12/21 10:03
 * Description：//todo
 *
 *
 */
class DiscoveryFragment : BaseFragment() {

    private val tabList=ArrayList<String>()

    private val fragments=ArrayList<Fragment>()


    private var mTitle : String? =null


    companion object {
        fun getInstance(title:String): DiscoveryFragment{

            val fragment=DiscoveryFragment()
            val bundle=Bundle()

            fragment.arguments=bundle
            fragment.mTitle=title

            return fragment
        }
    }




    override fun lazyLoad() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initView() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        //状态栏透明和间距处理
        StatusBarUtil.darkMode(activity)
        StatusBarUtil.setPaddingSmart(activity,toolbar)

        tv_header_title.text = mTitle

        tabList.add("关注")
        tabList.add("分类")
        fragments.add(FollowFragment.getInstance("关注"))
        fragments.add(CategoryFragment.getInstance("分类"))

        /**
         * getSupportFragmentManager() 替换为getChildFragmentManager()
         */
        mViewPager.adapter = BaseFragmentAdapter(childFragmentManager,fragments,tabList)
        mTabLayout.setupWithViewPager(mViewPager)
        TabLayoutHelper.setUpIndicatorWidth(mTabLayout)
    }

    override fun getLayoutId(): Int {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    return R.layout.fragment_hot
    }
}