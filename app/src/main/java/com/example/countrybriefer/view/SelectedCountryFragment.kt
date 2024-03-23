package com.example.countrybriefer.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.countrybriefer.R
import com.example.countrybriefer.databinding.FragmentSelectedCountryBinding
import com.example.countrybriefer.util.downloadFromUrl
import com.example.countrybriefer.util.placeholderProgressBar
import com.example.countrybriefer.viewmodel.SelectedCountryViewModel


class SelectedCountryFragment : Fragment() {

    private lateinit var viewModel: SelectedCountryViewModel
    private lateinit var binding: FragmentSelectedCountryBinding
    private var countryUid=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_selected_country, container, false)
        binding = FragmentSelectedCountryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUid = SelectedCountryFragmentArgs.fromBundle(it).countryUid
        }
        viewModel = ViewModelProvider(this).get(SelectedCountryViewModel:: class.java)
        viewModel.getDataFromRoom(countryUid)




        observeLiveData()
    }

    private fun observeLiveData(){



        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {country->
            country?.let {
                binding.countryName.text = country.countryName
                binding.countryCapital.text = country.countryCapital
                binding.countryCurrency.text = country.countryCurrency
                binding.countryLanguage.text = country.countryLanguage
                binding.countryRegion.text = country.countryRegion
                context?.let {
                    binding.countryImage.downloadFromUrl(country.imageUrl, placeholderProgressBar(it))
                }

            }

        })
    }


}