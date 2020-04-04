package com.demo.weatherinfo.ui.current

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.weatherinfo.R
import com.demo.weatherinfo.base.BaseActivity
import com.demo.weatherinfo.databinding.ActivityCurrentWeatherBinding
import com.demo.weatherinfo.vm.CurrentWeatherViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


/**
 * Created by Rashida on 4/4/20.
 *
 */

class CurrentWeatherActivity : BaseActivity<CurrentWeatherViewModel, ActivityCurrentWeatherBinding>
    (CurrentWeatherViewModel::class.java), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    var adapter=CurrentWeatherAdapter()

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun getLayoutRes(): Int {
        return R.layout.activity_current_weather
    }

    override fun initViewModel(viewModel: CurrentWeatherViewModel) {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val citiesList = intent.getStringArrayListExtra("cityList")
        binding.weatherList.layoutManager=LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.weatherList.adapter=adapter
        viewModel.getCurrentWeatherOfCities(citiesList)
        binding.progressBar.visibility= View.VISIBLE
    }

    override fun observeViewModelData() {
        viewModel.cityWeatherLiveData.observe(this, Observer {
            binding.progressBar.visibility= View.GONE
            adapter.setData(it)
        })
    }

}