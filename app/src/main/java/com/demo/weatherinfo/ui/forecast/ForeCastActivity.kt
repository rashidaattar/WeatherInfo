package com.demo.weatherinfo.ui.forecast

import android.location.Location
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
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

    var adapter = ForeCastDayAdapter(this)

    override fun getLayoutRes(): Int {
        return R.layout.activity_forecast
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val location = intent.getParcelableExtra<Location>("location")

        binding.forecastList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.forecastList.adapter = adapter
        location?.let {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.loadCurrentWeatherByGeoCords(location.latitude, location.longitude, "metric")
                .observe(this,
                    Observer { data ->
                        supportActionBar?.title = data.data?.city?.name
                        data.data?.list?.let {
                            viewModel.getListForAdapter(it)
                        }
                    })
        }
    }

    override fun initViewModel(viewModel: ForeCastViewModel) {
        binding.viewModel = viewModel
    }

    override fun observeViewModelData() {
        viewModel.forecastDaywiseLiveData.observe(this, Observer {
            binding.progressBar.visibility = View.GONE
            adapter.setData(it)
        })
    }

}