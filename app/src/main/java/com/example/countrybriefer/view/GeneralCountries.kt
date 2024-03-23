package com.example.countrybriefer.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.countrybriefer.R
import com.example.countrybriefer.adapter.CountryAdapter
import com.example.countrybriefer.databinding.ActivityMainBinding
import com.example.countrybriefer.databinding.FragmentGeneralCountriesBinding
import com.example.countrybriefer.model.Country
import com.example.countrybriefer.viewmodel.GeneralCountriesViewModel



class GeneralCountries : Fragment() {
    private lateinit var viewModel: GeneralCountriesViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())
    private lateinit var binding: FragmentGeneralCountriesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding = FragmentGeneralCountriesBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        //binding = DataBindingUtil.setContentView(this,R.layout.fragment_general_countries)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View=inflater.inflate(R.layout.fragment_general_countries, container, false)
        binding = FragmentGeneralCountriesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel= ViewModelProvider(this).get(GeneralCountriesViewModel::class.java)
        viewModel.refreshData()

        val countryError: TextView = binding.swipeRefreshLayout.findViewById(R.id.countryError)
        val countryList: RecyclerView = binding.swipeRefreshLayout.findViewById(R.id.countryList)
        val countryLoading: ProgressBar = binding.swipeRefreshLayout.findViewById(R.id.countryLoading)

        countryList.layoutManager = LinearLayoutManager(context)
        countryList.adapter = countryAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            countryList.visibility = View.GONE
            countryError.visibility = View.GONE
            countryLoading.visibility = View.VISIBLE
            viewModel.refreshFromAPI()
            binding.swipeRefreshLayout.isRefreshing = false
        }
        observeLiveData()

    }


    fun observeLiveData(){
        val countryError: TextView = binding.swipeRefreshLayout.findViewById(R.id.countryError)
        val countryList: RecyclerView = binding.swipeRefreshLayout.findViewById(R.id.countryList)
        val countryLoading: ProgressBar = binding.swipeRefreshLayout.findViewById(R.id.countryLoading)

        viewModel.countries.observe(viewLifecycleOwner, Observer {countries ->
            countries?.let{
                countryList.visibility= View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }

        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if(it){
                    countryError.visibility = View.VISIBLE
                    countryList.visibility = View.GONE
                }
                else{
                    countryError.visibility = View.GONE
                }
            }
        } )

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let{
                if (it){
                    countryLoading.visibility = View.VISIBLE
                    countryList.visibility= View.GONE
                    countryError.visibility = View.GONE
                }
                else{
                    countryLoading.visibility = View.GONE
                }
            }
        } )

    }

}