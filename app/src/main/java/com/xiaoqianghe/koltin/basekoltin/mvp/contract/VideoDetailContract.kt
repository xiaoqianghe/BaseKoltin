package com.xiaoqianghe.koltin.basekoltin.mvp.contract

import com.xiaoqianghe.koltin.basekoltin.base.IBaseView
import com.xiaoqianghe.koltin.basekoltin.base.IPresenter
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean


/**
 *
 * Author：Wq
 * Date：2017/12/25 14:43
 * Description：//todo
 *
 *
 */
interface VideoDetailContract {

    interface  View : IBaseView{


        fun setVideo(url : String)

        fun setVideoInfo(itemInfo : HomeBean.Issue.Item)

        fun setBackground(url: String)



        fun setRecentRelatedVideo(itemList : ArrayList<HomeBean.Issue.Item>)


        fun setErrorMsg(errorMsg:String)
    }

    interface  Presenter: IPresenter<View>{

        fun loadVideoInfo(itemInfo: HomeBean.Issue.Item)

        fun  requestRelateVideo(id : Long)



    }
}