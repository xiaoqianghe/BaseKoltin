package com.xiaoqianghe.koltin.basekoltin.mvp.contract

import com.xiaoqianghe.koltin.basekoltin.base.IBaseView
import com.xiaoqianghe.koltin.basekoltin.base.IPresenter
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean


/**
 *
 * Author：Wq
 * Date：2018/1/19 14:17
 * Description：//todo
 *
 *
 */
interface SearchContract {

    interface  View : IBaseView{
        //设置热门关键词的数据
        fun setHotWordData(String :ArrayList<String>)

        //设置搜索关键词返回的结果

        fun setSearchResult(issue: HomeBean.Issue)


        //关掉软键盘
        fun closeSoftKeyboard()

        //设置空View
        fun  setEmptyView()

        //
        fun showError(errorMsg: String,errorCode:Int)

    }

    interface  presenter :IPresenter<View>{

        //获取热门关键字数据
        fun requestHotWordData()

        // 查询搜索
        fun querySearchData(words:String)


        //加载更多
        fun loadMoreData()
    }
}