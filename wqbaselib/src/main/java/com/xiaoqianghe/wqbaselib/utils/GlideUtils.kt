package com.xiaoqianghe.koltin.basekoltin.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.xiaoqianghe.koltin.basekoltin.R


/**
 *
 * Author：Wq
 * Date：2018/1/11 10:03
 * Description：//todo
 *
 *
 */
class GlideUtils {


    companion object{
        fun load(context: Context, url: String, imageView: ImageView, options: RequestOptions) {
            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .into(imageView)
        }

        fun load(context: Context, url: String?, imageView: ImageView) {
            val options = RequestOptions()
                    .placeholder(R.mipmap.iv_place_bg)
                    //DrawableTransitionOptions().crossFade()
//                    .transform(DrawableTransitionOptions.withCrossFade())
                    .error(R.mipmap.iv_err_bg)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)

            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .transition(DrawableTransitionOptions().crossFade())
                    .thumbnail(0.5f)
                    .into(imageView)
        }



        /**
         *
         * @todo：
         *
         * @params url  图片链接
         * @Params imageView 图片控件
         *          placeholder_resoourceId  默认图片的资源Id
         *          error_resourceId         报错的图片的资源Id
         *
         *
         *
         * */
        fun load(context: Context, url: String, imageView: ImageView,placeholder_resourceId:Int ,error_resourceId:Int) {
            val options = RequestOptions()
                    .placeholder(placeholder_resourceId)
                    .error(error_resourceId)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .into(imageView)
        }



    }



}