package com.example.countrybriefer.service

import com.example.countrybriefer.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {

    //https://raw.githubusercontent.com/

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries():Single<List<Country>>
}