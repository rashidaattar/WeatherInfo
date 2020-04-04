package com.demo.weatherinfo.vm

import androidx.lifecycle.MutableLiveData
import com.demo.weatherinfo.base.BaseViewModel
import com.demo.weatherinfo.data.model.ListItem
import com.demo.weatherinfo.data.remote.OpenWeatherAPI
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import javax.inject.Inject


/**
 * Created by Rashida on 4/4/20.
 *
 */
class CurrentWeatherViewModel @Inject constructor(var weatherAPI: OpenWeatherAPI) :
    BaseViewModel() {

    var cityWeatherLiveData = MutableLiveData<List<ListItem>>()
    var errorLiveData = MutableLiveData<String>()

    fun getCurrentWeatherOfCities(cities: List<String>) {

        var observablesList = arrayListOf<Observable<ListItem>>()
        if (cities.size > 0) {
            for (city in cities) {
                observablesList.add(weatherAPI.getWeatherPerCity(city))
            }
        }
        var observer = Observable.zip(observablesList, Arrays::asList).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                run {
                    if (data.size > 0)
                        cityWeatherLiveData.postValue(data as List<ListItem>)
                }
            }, { t ->
                run {
                    Timber.d("exception is ${t.localizedMessage}")
                    errorLiveData.postValue(t.localizedMessage)
                }
            })

    }
}