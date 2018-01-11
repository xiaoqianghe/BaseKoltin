package com.xiaoqianghe.koltin.basekoltin.ui.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.xiaoqianghe.koltin.basekoltin.MyApplication
import com.xiaoqianghe.koltin.basekoltin.R
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.CategoryBean
import com.xiaoqianghe.koltin.basekoltin.utils.GlideUtils
import com.xiaoqianghe.koltin.basekoltin.view.recyclerview.ViewHolder
import com.xiaoqianghe.koltin.basekoltin.view.recyclerview.adapter.CommonAdapter


/**
 *
 * Author：Wq
 * Date：2017/12/26 17:11
 * Description：//todo
 *
 *
 */
class CategoryAdapter(mContext: Context, categoryList: ArrayList<CategoryBean>, layoutId: Int) :
        CommonAdapter<CategoryBean>(mContext, categoryList, layoutId) {



    private var textTypeface: Typeface?=null

    init {
        textTypeface = Typeface.createFromAsset(MyApplication.context.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
    }
    /**
     * 设置新数据
     */
    fun setData(categoryList: ArrayList<CategoryBean>){
        mData.clear()
        mData = categoryList
        notifyDataSetChanged()
    }
    /**
     * 绑定数据
     */
    override fun bindData(holder: ViewHolder, data: CategoryBean, position: Int) {
        holder.setText(R.id.tv_category_name, "#${data.name}")
        //设置方正兰亭细黑简体
        holder.getView<TextView>(R.id.tv_category_name).typeface = textTypeface

        holder.setImagePath(R.id.iv_category, object : ViewHolder.HolderImageLoader(data.bgPicture) {
            override fun loadImage(iv: ImageView, path: String) {
//                GlideApp.with(mContext)
//                        .load(path)
//                        .placeholder(R.color.color_darker_gray)
//                        .transition(DrawableTransitionOptions().crossFade())
//                        .thumbnail(0.5f)
//                        .into(iv)

                GlideUtils.load(mContext,path,iv)
            }
        })

        holder.setOnItemClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
//                val intent = Intent(mContext as Activity,CategoryDetailActivity::class.java)
//                intent.putExtra(Constants.BUNDLE_CATEGORY_DATA,data)
//                mContext.startActivity(intent)
            }
        })
    }
}