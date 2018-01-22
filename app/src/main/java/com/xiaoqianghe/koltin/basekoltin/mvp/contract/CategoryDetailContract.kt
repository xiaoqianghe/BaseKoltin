package com.xiaoqianghe.koltin.basekoltin.mvp.contract

import com.xiaoqianghe.koltin.basekoltin.base.IBaseView
import com.xiaoqianghe.koltin.basekoltin.base.IPresenter
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean


/**
 *
 * Author：Wq
 * Date：2018/1/22 10:11
 * Description：//todo
 *
 *
 */
interface CategoryDetailContract {

    interface  View : IBaseView{
        fun setCateDetailList(itemList:ArrayList<HomeBean.Issue.Item>)
        fun showError(errorMsg:String)
    }



    interface Presenter: IPresenter<View> {

        fun getCategoryDetailList(id:Long)

        fun loadMoreData()
    }




}