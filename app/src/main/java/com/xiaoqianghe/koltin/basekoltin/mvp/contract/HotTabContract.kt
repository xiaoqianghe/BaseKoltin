package com.xiaoqianghe.koltin.basekoltin.mvp.contract

import com.xiaoqianghe.koltin.basekoltin.base.IBaseView
import com.xiaoqianghe.koltin.basekoltin.base.IPresenter
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.TabInfoBean


/**
 *
 * Author：Wq
 * Date：2017/12/26 14:21
 * Description：//todo
 *
 *
 */
interface  HotTabContract {

    interface View : IBaseView{


        fun setTabInfo(tabInfoBean: TabInfoBean)

        fun showError(errorMsg:String,errorCode: Int)
    }


    interface  Presenter: IPresenter<View>{

        fun  getTableInfo()
    }
}