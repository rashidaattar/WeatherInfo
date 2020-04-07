package com.demo.weatherinfo.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.demo.weatherinfo.JUnitTestHelper
import com.demo.weatherinfo.data.model.DayTimeModel
import com.demo.weatherinfo.data.model.ForecastResponse
import com.demo.weatherinfo.data.remote.OpenWeatherAPI
import com.demo.weatherinfo.data.remote.Resource
import com.demo.weatherinfo.data.remote.Status
import com.demo.weatherinfo.vm.ForeCastViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


/**
 * Created by Rashida on 4/6/20.
 *
 */
@RunWith(JUnit4::class)
class ForeCastViewModelTest :JUnitTestHelper(){

    lateinit var foreCastViewModel: ForeCastViewModel

    @MockK
    lateinit var weatherAPI:OpenWeatherAPI

    @get:Rule
    val rule = InstantTaskExecutorRule()


    private val getValidForeCastresponse by lazy {

        getObjectFromJson("mockJson/city_weather_response.json",ForecastResponse::class.java)
    }

    @Before
    override fun before() {
        MockKAnnotations.init(this)
        foreCastViewModel = ForeCastViewModel(weatherAPI)    }

    override fun after() {
    }


    @Test
    fun `check if success response is being sent for Mountain View`(){
        var forecastResponseLiveData= MutableLiveData<Resource<ForecastResponse>>()
        forecastResponseLiveData.value= Resource(Status.SUCCESS,getValidForeCastresponse,"")
        Assert.assertEquals("Mountain View",
            getValidForeCastresponse.city?.name)
    }

    @Test
    fun `check function to parse response to get 5days weather for every 3hrs`(){
        getValidForeCastresponse.list?.let { return foreCastViewModel.getListForAdapter(it) }
        Assert.assertEquals(5,foreCastViewModel.forecastDaywiseLiveData.value?.size)
    }

}