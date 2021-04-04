package com.thanht.weather

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.thanht.data.cache.AppSharePref
import com.thanht.data.executor.PostExecutionThread
import com.thanht.domain.base.BaseResult
import com.thanht.domain.city.WeatherRepository
import com.thanht.domain.model.WeatherModel
import com.thanht.weather.home.list.FORECAST_DAY
import com.thanht.weather.home.list.HomeViewModel
import com.thanht.weather.home.list.UNITS
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private val mWeatherRepository: WeatherRepository = mock()
    private val mPostExecutionThread: PostExecutionThread = mock()
    private val mAppSharePref: AppSharePref = mock()

    private lateinit var sut: HomeViewModel

    @Before
    fun setUp() {
        whenever(mPostExecutionThread.io).doReturn(Dispatchers.IO)
        whenever(mPostExecutionThread.main).doReturn(Dispatchers.Main)
        whenever(mPostExecutionThread.default).doReturn(Dispatchers.Default)

        sut = HomeViewModel(mWeatherRepository, mAppSharePref, mPostExecutionThread)
    }

    @Test
    fun testCaseViewModelInited() {
        //given
        //when
        //then
        TestCase.assertNotNull(sut)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testCaseQueryWeatherTrim() {
        //given
        val query = "  a   "
        val queryFormatted = "a"
        //when
        sut.queryWeather(query)
        //then
        assertEquals(sut.queryStringLive.value, queryFormatted)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testCaseQueryWeatherNull() {
        //given
        val query = null
        val queryFormatted = ""
        //when
        sut.queryWeather(query)
        //then
        assertEquals(sut.queryStringLive.value, queryFormatted)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testCaseQueryWeatherSetValue() {
        //given
        val query = "saigon"
        //when
        sut.queryWeather(query)
        //then
        assertEquals(sut.queryStringLive.value, query)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testCaseQueryWeatherSameValue() {
        //given
        val firstQuery = "saigon"
        val secondQuery = "  saigon  "
        //when
        sut.queryWeather(firstQuery)
        sut.queryWeather(secondQuery)
        //then
        assertEquals(sut.queryStringLive.value, firstQuery)
    }

    @Test
    fun testCaseQueryWeatherSearch() {
        //given
        val query = "saigon"
        val result = emptyFlow<BaseResult<List<WeatherModel>>>()
        whenever(mWeatherRepository.getListWeather(query, FORECAST_DAY, mAppSharePref.appId, UNITS))
            .thenReturn(result)
        whenever(mWeatherRepository.getListWeather(query, FORECAST_DAY, mAppSharePref.appId, UNITS))
            .thenReturn(result)
        //when
        sut.queryWeather(query)
        //then
        assertEquals(
            mWeatherRepository.getListWeather(
                query,
                FORECAST_DAY,
                mAppSharePref.appId,
                UNITS
            ), result
        )
    }
}