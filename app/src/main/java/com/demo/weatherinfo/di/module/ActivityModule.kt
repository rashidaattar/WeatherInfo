package com.demo.weatherinfo.di.module

import com.demo.weatherinfo.di.scope.ActivityScope
import com.demo.weatherinfo.ui.current.CurrentWeatherActivity
import com.demo.weatherinfo.ui.forecast.ForeCastActivity
import com.demo.weatherinfo.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by Rashida on 3/31/20.
 *
 */
@Module
abstract class ActivityModule {

    /**
     * Injects [HomeActivity]
     *
     * @return an instance of [HomeActivity]
     */

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun homeActivity(): HomeActivity

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun foreCastActivity(): ForeCastActivity

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun currentWeatherActivity(): CurrentWeatherActivity
}