package com.lawencon.movieapp.data

import com.lawencon.movieapp.BuildConfig
import com.lawencon.movieapp.data.base.BaseNetwork
import com.lawencon.movieapp.data.interceptor.ContentTypeInterceptor
import okhttp3.OkHttpClient

abstract class AbstractNetwork<T>(): BaseNetwork<T>() {

    override fun getBaseUrl(): String = BuildConfig.BASE_URL

    override fun okHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        builder.addInterceptor(ContentTypeInterceptor())
        return super.okHttpClientBuilder(builder)
    }

}