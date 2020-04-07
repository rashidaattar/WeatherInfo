package com.demo.weatherinfo.vm

import android.Manifest
import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.demo.weatherinfo.base.BaseViewModel
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.Disposable
import timber.log.Timber
import java.util.*
import javax.inject.Inject


/**
 * Created by Rashida on 4/1/20.
 *
 */

class HomeViewModel @Inject constructor(var context: Context) :
    BaseViewModel() {

    var permissionDisposable: Disposable? = null
    var permissionLiveData = MutableLiveData<Boolean>()
    var cityNames = ObservableField<String>()
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
            if (it.size > 7 || it.size < 3) {
                errorLiveData.postValue("MAX 7 cities and MIN 3 cities required")
            } else {
                getCities.postValue(cities as ArrayList<String>)
            }
        }
    }

}

