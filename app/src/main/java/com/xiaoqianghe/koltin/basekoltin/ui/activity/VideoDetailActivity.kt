package com.xiaoqianghe.koltin.basekoltin.ui.activity

import com.xiaoqianghe.koltin.basekoltin.base.BaseActivity
import com.xiaoqianghe.koltin.basekoltin.mvp.contract.VideoDetailContract
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean


/**
 *
 * Author：Wq
 * Date：2017/12/25 15:38
 * Description：//todo
 *
 *
 */
class VideoDetailActivity :BaseActivity(),VideoDetailContract.View {

    companion object {
        val IMG_TRANSITION = "IMG_TRANSITION"
        val TRANSITION = "TRANSITION"
    }


    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setVideo(url: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setVideoInfo(itemInfo: HomeBean.Issue.Item) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setBackground(url: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initStart() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setRecentRelatedVideo(itemList: ArrayList<HomeBean.Issue.Item>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initListener() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setErrorMsg(errorMsg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}