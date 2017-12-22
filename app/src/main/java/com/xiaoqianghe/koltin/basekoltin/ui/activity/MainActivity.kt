package com.xiaoqianghe.koltin.basekoltin.ui.activity

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.hazz.kotlinmvp.mvp.model.bean.TabEntity
import com.xiaoqianghe.koltin.basekoltin.R
import com.xiaoqianghe.koltin.basekoltin.base.BaseActivity
import com.xiaoqianghe.koltin.basekoltin.ui.fragment.DiscoveryFragment
import com.xiaoqianghe.koltin.basekoltin.ui.fragment.HomeFragment
import com.xiaoqianghe.koltin.basekoltin.ui.fragment.HotFragment
import com.xiaoqianghe.koltin.basekoltin.ui.fragment.MineFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun initStart() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initListener() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initLayoutId(): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

       return R.layout.activity_main

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        if(savedInstanceState!=null){

            mIndex=savedInstanceState.getInt("currTabIndex")

        }

        initTab()
        tab_layout.currentTab=mIndex
        switchFragment(mIndex)
    }

    private fun switchFragment(mIndex: Int) {

        val transaction= supportFragmentManager.beginTransaction();

        hideFragments(transaction)

        when(mIndex){

            0
            ->if(mHomeFragment==null){

                //Fragment 为null 的情况下

            }else{

            }

        }



    }

    private fun hideFragments(transaction: FragmentTransaction?) {



    }


    private fun initTab() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        (0 until mTitles.size).mapTo(mTabEntites){

            TabEntity(mTitles[it],onSelectIds[it],onUnSelectIds[it])
        }

        tab_layout.setTabData(mTabEntites)
        tab_layout.setOnTabSelectListener(object :OnTabSelectListener{
            override fun onTabSelect(position: Int) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                switchFragment(position)

            }
            override fun onTabReselect(position: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

    }

    private val mTitles= arrayOf("每日精选","发现","热门","我的")

    private val onUnSelectIds= intArrayOf(R.mipmap.ic_home_normal,R.mipmap.ic_discovery_normal,R.mipmap.ic_hot_normal,R.mipmap.ic_mine_normal)

    private val onSelectIds= intArrayOf(R.mipmap.ic_home_selected,R.mipmap.ic_discovery_selected,R.mipmap.ic_hot_selected,R.mipmap.ic_mine_selected)


    private val mTabEntites=ArrayList<CustomTabEntity>()


    private var mHomeFragment : HomeFragment? =null
    private var mDiscoverFragment : DiscoveryFragment? =null
    private var mHotFragment : HotFragment? =null
    private var mMineFragment : MineFragment? =null

    private var mIndex =0

















}