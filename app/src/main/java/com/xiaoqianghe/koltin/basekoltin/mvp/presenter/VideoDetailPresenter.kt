package com.xiaoqianghe.koltin.basekoltin.mvp.presenter

import android.app.Activity
import com.xiaoqianghe.basekoltin.net.exception.ExceptionHandle
import com.xiaoqianghe.basekoltin.utils.DisplayManager
import com.xiaoqianghe.basekoltin.utils.NetworkUtil
import com.xiaoqianghe.koltin.basekoltin.MyApplication
import com.xiaoqianghe.koltin.basekoltin.base.BasePresenter
import com.xiaoqianghe.koltin.basekoltin.dataFormat
import com.xiaoqianghe.koltin.basekoltin.mvp.contract.VideoDetailContract
import com.xiaoqianghe.koltin.basekoltin.mvp.model.VideoDetailModel
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean
import com.xiaoqianghe.koltin.basekoltin.showToast


/**
 *
 * Author：Wq
 * Date：2017/12/25 14:57
 * Description：//todo
 *
 *
 */
class VideoDetailPresenter : BasePresenter<VideoDetailContract.View>(),VideoDetailContract.Presenter{



    private val videoDetailModel : VideoDetailModel by lazy {

        VideoDetailModel()
    }

    override fun loadVideoInfo(itemInfo: HomeBean.Issue.Item) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.



        val playInfo = itemInfo.data?.playInfo

        val netType=NetworkUtil.isWifi(MyApplication.context)

        checkViewAttached()

        if(playInfo!!.size>1){
            //当前是wife的环境下选择高清视频
            if(netType){

                for(i in playInfo){

                    if(i.type=="high"){

                        var  playUrl=i.url

                        mRootView!!.setVideo(playUrl)

                        break

                    }

                }

            }else{

                for (i in playInfo){
                    if(i.type=="normal"){

                        var playUrl=i.url

                        mRootView!!.setVideo(playUrl)
                        //

                        //Todo 待完善
                        (mRootView as Activity).showToast("本次消耗${(mRootView as Activity)
                                .dataFormat(i.urlList[0].size)}流量")
                        break



                    }
                }
            }

        }else{
            mRootView!!.setVideo(itemInfo.data.playUrl)

        }


        //设置背景
        val backgroundUrl = itemInfo.data.cover.blurred + "/thumbnail/${DisplayManager.getScreenHeight()!! - DisplayManager.dip2px(250f)!!}x${DisplayManager.getScreenWidth()}"
        backgroundUrl.let { mRootView?.setBackground(it) }

        mRootView?.setVideoInfo(itemInfo)







    }

    override fun requestRelateVideo(id: Long) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        mRootView?.showLoading()

        val  disposable= videoDetailModel.requestRelatedData(id)
                .subscribe({

                    issue ->
                    mRootView?.apply {
                        dismissLoading()
                        setRecentRelatedVideo(issue.itemList)
                    }
                }, { t ->
                    mRootView?.apply {
                        dismissLoading()
                        setErrorMsg(ExceptionHandle.handleException(t))
                    }
                })

        addSubscription(disposable)

    }
}