package com.xiaoqianghe.koltin.basekoltin.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.xiaoqianghe.koltin.basekoltin.R
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        tv_msg.text="点我辣啊  点我辣"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        login.setOnClickListener(this)







    }
}