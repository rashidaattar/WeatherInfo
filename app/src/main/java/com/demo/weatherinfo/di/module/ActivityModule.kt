package com.demo.weatherinfo.di.module

import com.demo.weatherinfo.MainActivity
import com.demo.weatherinfo.di.scope.ActivityScope
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
     * Injects [MainActivity]
     *
     * @return an instance of [MainActivity]
     */

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    internal abstract fun homeActivity(): HomeActivity
}