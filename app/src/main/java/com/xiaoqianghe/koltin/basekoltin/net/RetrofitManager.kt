package com.xiaoqianghe.koltin.basekoltin.net

import com.hazz.kotlinmvp.api.UriConstant
import com.hazz.kotlinmvp.utils.Preference
import com.xiaoqianghe.koltin.basekoltin.MyApplication
import com.xiaoqianghe.koltin.basekoltin.api.ApiService

import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


/**
 *
 * Author：Wq
 * Date：2017/12/21 15:53
 * Description：//todo
 *
 *
 */
object RetrofitManager {
    private var client: OkHttpClient? = null
    private var retrofit: Retrofit? = null
    val service : ApiService by lazy { getRetrofit()!!.create(ApiService::class.java)}

    private fun getRetrofit(): Retrofit? {
        if (retrofit == null) {
            synchronized(RetrofitManager::class.java) {
                //添加一个 log 拦截器
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                //设置 请求的缓存的大小跟位置
                val cacheFile = File(MyApplication.context.cacheDir, "cache")
                val cache = Cache(cacheFile, 1024 * 1024 * 50)
                client = OkHttpClient.Builder()
                        .addInterceptor(addQueryParameterIntercptor())
                        .addInterceptor(addHeaderInterceptor())
                        .addInterceptor(httpLoggingInterceptor)
                        .cache(cache)
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(60, TimeUnit.SECONDS)
                        .build()
                retrofit = Retrofit.Builder()
                        .baseUrl(UriConstant.BASE_URL)
                        .client(client)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
        }
        return retrofit
    }



    private fun addHeaderInterceptor(): Interceptor? {
        return Interceptor { chain ->
            val originRequest=chain.request()
            val requestBuilder=originRequest.newBuilder()
                    .header("token",token)
                    .method(originRequest.method(),originRequest.body())

            val request=requestBuilder.build()
            chain.proceed(request)
        }

    }

    private var token:String by Preference("token","")



    /**
     *
     * @todo 设置公共参数
     *
     * */
    private fun addQueryParameterIntercptor(): Interceptor? {
        return Interceptor { chain ->
            val originalRequest=chain.request()
            val request :Request
            val modifiedUrl = originalRequest.url().newBuilder()
                    .addQueryParameter("phoneSystem","")
                    .addQueryParameter("phoneModel","")
                    .build()
            request=originalRequest.newBuilder().url(modifiedUrl).build()
            chain.proceed(request)
        }
    }

}





