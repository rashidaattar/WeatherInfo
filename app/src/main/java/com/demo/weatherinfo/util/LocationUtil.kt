package com.demo.weatherinfo.util

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.SettingNotFoundException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import timber.log.Timber
import java.util.*


/**
 * Created by Rashida on 4/1/20.
 *
 */
class LocationUtil {
//    private var locationManager: LocationManager? = null
//    private var isGPSEnabled = false
//
//    private var locationTimer: Timer? = null
//    private val LOCATION_DUBAI = "Dubai"
//    private val LAST_KNOWN_LOCATION_DELAY = 5000 //5 seconds
//
//    private val LOCATION_MIN_TIME = 0
//    private val LOCAITON_MIN_DISTANCE = 0
//
//    private lateinit var fusedLocationClient: FusedLocationProviderClient
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        // ...
//
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//    }
//
//
//    /**
//     * utility method to return if location is enabled or not
//     *
//     * @param context
//     * @return true if location enabled else false
//     */
//    fun isLocationEnabled(context: Context?): Boolean {
//        if (context == null) return false
//        val locationMode: Int
//        locationMode = try {
//            Settings.Secure.getInt(
//                context.contentResolver,
//                Settings.Secure.LOCATION_MODE
//            )
//        } catch (e: SettingNotFoundException) {
//            e.printStackTrace()
//            return false
//        }
//        return locationMode != Settings.Secure.LOCATION_MODE_OFF
//    }
//
//    internal class GetLastLocation : TimerTask() {
//        override fun run() {
//            Timber.d("GetLastLocation is running")
//            locationManager.removeUpdates(this)
//            var networkLocation: Location? = null
//            var gpsLocation: Location? = null
//            if (isGPSEnabled) gpsLocation =
//                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
//
//            if (gpsLocation != null) {
//                locationResult.gotLocation(gpsLocation)
//                return
//            }
//
//            locationResult.gotLocation(null)
//        }
//    }
//
//    /**
//     * Method to get the real user location
//     *
//     * @param context
//     * @param result
//     * @return
//     */
//    @SuppressLint("MissingPermission")
//    private fun getLocation(
//        context: Context
//    ): Boolean {
//        if (locationManager == null) locationManager = context
//            .getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        // exceptions will be thrown if provider is not permitted.
//        try {
//            isGPSEnabled = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
//        } catch (ex: Exception) {
//            Timber.e(ex)
//        }
//        // don't start listeners if no provider is enabled
//        if (!isGPSEnabled) return false
//        if (isGPSEnabled) {
//
//            locationManager!!.getLastKnownLocation( locationManager?.getProvider(LocationManager.GPS_PROVIDER))
//        }
//        locationTimer = Timer()
//        locationTimer?.schedule(GetLastLocation(), LAST_KNOWN_LOCATION_DELAY.toLong())
//        return true
//    }
//
//    override fun onLocationChanged(location: Location?) {
//        locationTimer?.cancel();
//        locationManager?.removeUpdates(this);
//        locationManager?.removeUpdates(locationListenerNetwork);
//    }
//
//    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
//    }
//
//    override fun onProviderEnabled(provider: String?) {
//    }
//
//    override fun onProviderDisabled(provider: String?) {
//    }
//
//
//    fun getLocation():Location{
//
//    }
}