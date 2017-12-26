package com.xiaoqianghe.koltin.basekoltin.mvp.presenter

import com.xiaoqianghe.basekoltin.net.exception.ExceptionHandle
import com.xiaoqianghe.koltin.basekoltin.base.BasePresenter
import com.xiaoqianghe.koltin.basekoltin.mvp.contract.HomeContract
import com.xiaoqianghe.koltin.basekoltin.mvp.model.HomeModel
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean





/**
 *
 * Author：Wq
 * Date：2017/12/21 14:38
 * Description：//todo
 *
 *
 */
class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter {

    private var bannerHomeBean : HomeBean? = null

    private var nextPageUrl : String? =null


    private val homeModel : HomeModel by lazy {


        HomeModel()
    }






    override fun requestHomeData(num: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        checkViewAttached()
        mRootView?.showLoading()

        val disposable = homeModel.requestHomeData(num)
                .flatMap({ homeBean ->

                    //过滤掉 Banner2(包含广告,等不需要的 Type), 具体查看接口分析
                    val bannerItemList = homeBean.issueList[0].itemList

                    bannerItemList.filter { item ->
                        item.type=="banner2"|| item.type=="horizontalScrollCard"
                    }.forEach{ item ->
                        //移除 item
                        bannerItemList.remove(item)
                    }

                    bannerHomeBean = homeBean //记录第一页是当做 banner 数据


                    //根据 nextPageUrl 请求下一页数据
                    homeModel.loadMoreData(homeBean.nextPageUrl)
                })

                .subscribe({ homeBean->
                    mRootView?.apply {
                        dismissLoading()

                        nextPageUrl = homeBean.nextPageUrl
                        //过滤掉 Banner2(包含广告,等不需要的 Type), 具体查看接口分析
                        val newBannerItemList = homeBean.issueList[0].itemList

                        newBannerItemList.filter { item ->
                            item.type=="banner2"||item.type=="horizontalScrollCard"
                        }.forEach{ item ->
                            //移除 item
                            newBannerItemList.remove(item)
                        }
                        // 重新赋值 Banner 长度
                        bannerHomeBean!!.issueList[0].count = bannerHomeBean!!.issueList[0].itemList.size

                        //赋值过滤后的数据 + banner 数据
                        bannerHomeBean?.issueList!![0].itemList.addAll(newBannerItemList)

                        setHomeData(bannerHomeBean!!)

                    }

                }, { t ->
                    mRootView?.apply {
                        dismissLoading()
                        showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
                    }
                })

        addSubscription(disposable)
    }

    override fun loadMoreData() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val disposable = nextPageUrl?.let {
            homeModel.loadMoreData(it)
                    .subscribe({ homeBean->
                        mRootView?.apply {
                            //过滤掉 Banner2(包含广告,等不需要的 Type), 具体查看接口分析
                            val newItemList = homeBean.issueList[0].itemList

                            newItemList.filter { item ->
                                item.type=="banner2"||item.type=="horizontalScrollCard"
                            }.forEach{ item ->
                                //移除 item
                                newItemList.remove(item)
                            }

                            nextPageUrl = homeBean.nextPageUrl
                            setMoreData(newItemList)
                        }

                    },{ t ->
                        mRootView?.apply {
                            showError(ExceptionHandle.handleException(t),ExceptionHandle.errorCode)
                        }
                    })


        }
        if (disposable != null) {
            addSubscription(disposable)
        }

    }
}