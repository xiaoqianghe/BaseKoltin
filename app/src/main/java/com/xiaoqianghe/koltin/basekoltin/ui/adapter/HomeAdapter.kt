package com.xiaoqianghe.koltin.basekoltin.ui.adapter

import android.content.Context
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import com.hazz.kotlinmvp.view.recyclerview.ViewHolder
import com.hazz.kotlinmvp.view.recyclerview.adapter.CommonAdapter


/**
 *
 * Author：Wq
 * Date：2017/12/22 10:25
 * Description：//todo
 *
 *
 */
class HomeAdapter (context: Context,data: ArrayList<HomeBean.Issue.Item>) :CommonAdapter<HomeBean.Issue.Item>(context,data,-1){
    override fun bindData(holder: ViewHolder, data: HomeBean.Issue.Item, position: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}