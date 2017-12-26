package com.xiaoqianghe.koltin.basekoltin.mvp.contract

import com.xiaoqianghe.koltin.basekoltin.base.IBaseView
import com.xiaoqianghe.koltin.basekoltin.base.IPresenter
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.CategoryBean


/**
 *
 * Author：Wq
 * Date：2017/12/26 16:18
 * Description：//todo
 *
 *
 */
interface CategoryContract {

    interface View : IBaseView{

        fun showCategory(categorys:ArrayList<CategoryBean>)

        fun showError(handleException: String, errorCode: Int)
    }

    interface  Presenter : IPresenter<View>{

        /**
         * 获取分类的信息
         */
        fun getCategoryData()

    }
}