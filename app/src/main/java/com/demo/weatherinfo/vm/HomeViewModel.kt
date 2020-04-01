package com.demo.weatherinfo.vm

import android.Manifest
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.weatherinfo.base.BaseViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import java.util.*
import javax.inject.Inject


/**
 * Created by Rashida on 4/1/20.
 *
 */

class HomeViewModel @Inject constructor(var context: Context) : BaseViewModel() {

    var permissionDisposable: Disposable? = null
    var permissionLiveData = MutableLiveData<Boolean>()

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
                    //
                } else {
                    //
                }
            }
    }

    fun getCityFromLatLong(location: Location?){
        // Got last known location. In some rare situations this can be null.
        location?.let {
            val gcd = Geocoder(context, Locale.getDefault())
            val addresses: List<Address> =
                gcd.getFromLocation(it.latitude, it.longitude, 1)
            if (addresses.size > 0) {
                System.out.println("city here :" + addresses[0].getLocality())
            }

        }}


}