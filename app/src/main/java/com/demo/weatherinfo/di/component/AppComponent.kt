package com.demo.weatherinfo.di.component

import android.app.Application
import com.demo.weatherinfo.WeatherInfo
import com.demo.weatherinfo.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/**
 * Created by Rashida on 3/31/20.
 *
 */


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        ApplicationModule::class,
        NetworkModule::class,
        ViewModelModule::class
    ]
)

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(weatherInfo: WeatherInfo)
}
