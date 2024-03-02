package com.example.countrybriefer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countrybriefer.model.Country

class GeneralCountriesViewModel: ViewModel() {
    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        val country = Country("Turkey","Asia","Ankara","Try","Turkish","www.ss.com")
        val country2 = Country("Germany","Europe","Berlin","Eu","German","www.ss.com")
        val country3 = Country("United States of America","America","Wdc","Usd","English","www.ss.com")
        val countryList= arrayListOf<Country>(country,country2,country3)

        countries.value=countryList
        countryError.value= false
        countryLoading.value= false
    }
}