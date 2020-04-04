package com.demo.weatherinfo.ui.forecast

import android.location.Location
import android.os.Bundle
import com.demo.weatherinfo.R
import com.demo.weatherinfo.base.BaseActivity
import com.demo.weatherinfo.databinding.ActivityForecastBinding
import com.demo.weatherinfo.vm.ForeCastViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


/**
 * Created by Rashida on 4/4/20.
 *
 */
class ForeCastActivity :
    BaseActivity<ForeCastViewModel, ActivityForecastBinding>(ForeCastViewModel::class.java),
    HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun getLayoutRes(): Int {
        return R.layout.activity_forecast
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val location = intent.getParcelableExtra<Location>("location")
        location?.let {
            viewModel.loadCurrentWeatherByGeoCords(location.latitude, location.longitude, "metric")
        }
    }

    override fun initViewModel(viewModel: ForeCastViewModel) {
        binding.viewModel = viewModel
    }

    override fun observeViewModelData() {
    }

}