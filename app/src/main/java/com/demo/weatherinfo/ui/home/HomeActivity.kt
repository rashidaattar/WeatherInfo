package com.demo.weatherinfo.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.demo.weatherinfo.R
import com.demo.weatherinfo.base.BaseActivity
import com.demo.weatherinfo.databinding.ActivityHomeBinding
import com.demo.weatherinfo.ui.current.CurrentWeatherActivity
import com.demo.weatherinfo.ui.forecast.ForeCastActivity
import com.demo.weatherinfo.vm.HomeViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Rashida on 3/31/20.
 *
 */
class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>(HomeViewModel::class.java),
    HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    private var fusedLocationClient: FusedLocationProviderClient? = null


    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun getLayoutRes() = R.layout.activity_home

    override fun initViewModel(viewModel: HomeViewModel) {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        viewModel.permissionLiveData.observe(this, Observer { granted ->
            if (granted) {
                getLastKnownLocation()
            } else {
                //show dialog here
            }
        })

        observeViewModelData()
    }

    override fun observeViewModelData() {
        viewModel.getCities.observe(this, Observer {
            val intent = Intent(this, CurrentWeatherActivity::class.java)
            intent.putExtra("cityList", it)
            startActivity(intent)
        })

        viewModel.errorLiveData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    /**
     * call this method for receive location
     * get location and give callback when successfully retrieve
     * function itself check location permission before access related methods
     *
     */
    fun getLastKnownLocation() {
        fusedLocationClient?.lastLocation
            ?.addOnSuccessListener { location ->
                // use your location object
                // get latitude , longitude and other info from this
                Timber.d("latitude : $location.latitude")
                val intent = Intent(this, ForeCastActivity::class.java)
                intent.putExtra("location", location)
                startActivity(intent)
            }

    }

}