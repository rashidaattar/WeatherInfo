package com.demo.weatherinfo.di.module

import com.demo.weatherinfo.base.Constant
import com.demo.weatherinfo.data.remote.OpenWeatherAPI
import com.demo.weatherinfo.data.remote.RequestInterceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


/**
 * Created by Rashida on 3/31/20.
 *
 */
@Module
class NetworkModule {

    @Singleton
    @Provides
    @Named("okHTTP_client")
    fun provideNonCachedOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(RequestInterceptor())
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit( @Named("okHTTP_client") client: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit.Builder): OpenWeatherAPI {
        return retrofit.baseUrl(Constant.API.BASE_URL)
            .build()
            .create(OpenWeatherAPI::class.java)
    }


}