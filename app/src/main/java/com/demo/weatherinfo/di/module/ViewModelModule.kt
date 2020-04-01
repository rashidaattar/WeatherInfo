package com.demo.weatherinfo.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.weatherinfo.di.ViewModelFactory
import com.demo.weatherinfo.di.key.ViewModelKey
import com.demo.weatherinfo.vm.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


/**
 * Created by Rashida on 4/1/20.
 *
 */
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory


    @IntoMap
    @Binds
    @ViewModelKey(HomeViewModel::class)
    abstract fun provideSplashFragmentViewModel(homeViewModel: HomeViewModel): ViewModel
}