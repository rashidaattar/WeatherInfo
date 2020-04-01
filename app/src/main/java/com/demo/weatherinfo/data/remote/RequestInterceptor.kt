package com.demo.weatherinfo.data.remote

import com.demo.weatherinfo.base.Constant
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Rashida on 3/31/20.
 *
 */
@Singleton
class RequestInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url
            .newBuilder()
            .addQueryParameter(Constant.API.API_KEY_QUERY, Constant.API.API_KEY_VALUE)
            .build()
        val request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
