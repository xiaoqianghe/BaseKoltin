package com.xiaoqianghe.koltin.basekoltin.mvp.contract

import com.xiaoqianghe.koltin.basekoltin.base.BasePresenter
import com.xiaoqianghe.koltin.basekoltin.base.IBaseView
import com.xiaoqianghe.koltin.basekoltin.base.IPresenter
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean


/**
 *
 * Author：Wq
 * Date：2017/12/26 17:38
 * Description：//todo
 *
 *
 */
interface  RankContract {

    interface View :IBaseView{
        fun setRankList(itemList:ArrayList<HomeBean.Issue.Item>)
        fun showError(errorMsg:String,errorCode:Int)
    }

    interface Presenter : IPresenter<View> {

        fun requestRankList(apiUrl:String)
    }

}