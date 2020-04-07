package com.demo.weatherinfo.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.demo.weatherinfo.JUnitTestHelper
import com.demo.weatherinfo.vm.HomeViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


/**
 * Created by Rashida on 4/5/20.
 *
 */
@RunWith(JUnit4::class)
class HomeViewModelTest : JUnitTestHelper() {

    @MockK
    lateinit var context: Context

    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    override fun before() {
        MockKAnnotations.init(this)
        homeViewModel = HomeViewModel(context)
    }

    override fun after() {
    }

    @Test
    fun `check if city names has value`() {
        homeViewModel.cityNames.set("Dubai,London,New York")

        Assert.assertEquals("Dubai,London,New York", homeViewModel.cityNames.get())
    }

    @Test
    fun `check if proper error msg sent when names less than 3`(){

        homeViewModel.cityNames.set("Dubai,London")
        homeViewModel.cityButtonClicked()
        Assert.assertEquals("MAX 7 cities and MIN 3 cities required",homeViewModel.errorLiveData.value)
    }

    @Test
    fun `check if proper error msg sent when more than 7`(){

        homeViewModel.cityNames.set("Dubai,London,Abu Dhabi,New York,Sharjah,Beirut,Mumbai,Amman")
        homeViewModel.cityButtonClicked()
        Assert.assertEquals("MAX 7 cities and MIN 3 cities required",homeViewModel.errorLiveData.value)
    }


    @Test
    fun `successfull response when cities meet the constraint`(){

        homeViewModel.cityNames.set("Dubai,London,Abu Dhabi,New York,Sharjah")
        homeViewModel.cityButtonClicked()
        Assert.assertEquals(arrayListOf("Dubai","London","Abu Dhabi","New York","Sharjah"),homeViewModel.getCities.value)
    }


}