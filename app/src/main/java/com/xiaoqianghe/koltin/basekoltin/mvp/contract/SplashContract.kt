package com.xiaoqianghe.koltin.basekoltin.mvp.contract

import com.xiaoqianghe.koltin.basekoltin.base.IBaseView
import com.xiaoqianghe.koltin.basekoltin.base.IPresenter


/**
 *
 * Author：Wq
 * Date：2018/3/30 10:01
 * Description：//todo
 *
 *
 */
interface SplashContract {

    interface View :IBaseView{

        fun showVersionData()

    }

    interface Presenter: IPresenter<View>{

        fun checkViewData()
    }
}