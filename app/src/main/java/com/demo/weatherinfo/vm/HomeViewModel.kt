package com.demo.weatherinfo.vm

import android.Manifest
import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.weatherinfo.base.BaseViewModel
import com.demo.weatherinfo.data.model.ForecastResponse
import com.demo.weatherinfo.data.model.ListItem
import com.demo.weatherinfo.data.remote.NetworkBoundResource
import com.demo.weatherinfo.data.remote.OpenWeatherAPI
import com.demo.weatherinfo.data.remote.Resource
import com.demo.weatherinfo.ui.current.CurrentWeatherActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import javax.inject.Inject


/**
 * Created by Rashida on 4/1/20.
 *
 */

class HomeViewModel @Inject constructor(var context: Context, var weatherAPI: OpenWeatherAPI) :
    BaseViewModel() {

    var permissionDisposable: Disposable? = null
    var permissionLiveData = MutableLiveData<Boolean>()
    var cityNames = ObservableField<String>()
    var cityWeatherLiveData = MutableLiveData<List<ListItem>>()
    var errorLiveData = MutableLiveData<String>()
    var getCities = MutableLiveData<ArrayList<String>>()


    fun requestForPermission() {
        permissionDisposable?.dispose() //removing any previous instace of permissions data
        permissionDisposable = RxPermissions.getInstance(context).requestEach(
            *arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
            .subscribe { permission ->
                if (permission.granted) {
                    //get location
                    permissionLiveData.postValue(true)
                } else if (permission.shouldShowRequestPermissionRationale) {
                    errorLiveData.postValue("Location permission is required to see current weather")
                } else {
                    permissionLiveData.postValue(false)
                }
            }
    }

    fun cityButtonClicked() {
        Timber.d("Button clicked ${cityNames.get()}")
        val cities = cityNames.get()?.trim()?.split(",")
        cities?.let {
            if (cities.size > 7 || cities.size < 3) {
                errorLiveData.postValue("MAX 7 cities and MIN 3 cities required")
            } else {
                getCities.postValue(cities as ArrayList<String>)
            }
        }
    }

}

