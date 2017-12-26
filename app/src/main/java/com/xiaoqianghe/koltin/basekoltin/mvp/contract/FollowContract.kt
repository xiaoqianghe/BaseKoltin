package com.xiaoqianghe.koltin.basekoltin.mvp.contract

import com.xiaoqianghe.koltin.basekoltin.base.IBaseView
import com.xiaoqianghe.koltin.basekoltin.base.IPresenter
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean


/**
 *
 * Author：Wq
 * Date：2017/12/26 15:35
 * Description：//todo
 *
 *
 */
interface FollowContract {

    interface View : IBaseView{

        /**
         *
         */
        fun setFollowInfo(issue: HomeBean.Issue)

        fun showError(errorMsg:String,errorCode: Int)




    }

    interface  Presenter :IPresenter<View>{
        fun requestFollowList()

        fun loadMoreData()
    }
}