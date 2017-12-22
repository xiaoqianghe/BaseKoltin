package com.xiaoqianghe.koltin.basekoltin.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 *
 * Author：Wq
 * Date：2017/12/20 14:58
 * Description：//todo
 *
 *
 */
abstract class BaseActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(initLayoutId())
        initData()
        initView()

        initStart()



        initListener()




    }


    abstract fun initStart()

    abstract fun initListener()

    abstract fun initView()

    abstract fun initData()

    abstract fun initLayoutId(): Int


}