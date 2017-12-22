package com.xiaoqianghe.koltin.basekoltin.mvp.contract


import com.xiaoqianghe.koltin.basekoltin.base.IBaseView
import com.xiaoqianghe.koltin.basekoltin.base.IPresenter
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean


/**
 *
 * Author：Wq
 * Date：2017/12/21 14:09
 * Description：//todo
 *
 *
 */
interface HomeContract {

    interface  View : IBaseView{


        /**
         *
         * @todo
         * 设置第一次加载数据
         *
         * */
        fun setHomeData(homeBean: HomeBean)

        fun setMoreData(itemList:ArrayList<HomeBean.Issue.Item>)

        fun showError(msg: String,errorCode:Int)

    }

    interface  Presenter : IPresenter<View>{


        fun requestHomeData(num : Int)

        fun loadMoreData()



    }
}