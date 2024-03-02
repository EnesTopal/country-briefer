package com.example.countrybriefer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countrybriefer.model.Country

class SelectedCountryViewModel: ViewModel() {

    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(){

        val country = Country("Turkey","Asia","Ankara","Try","Turkish","www.ss.com")
        countryLiveData.value = country
    }

}