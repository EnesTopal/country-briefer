package com.example.countrybriefer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.countrybriefer.R
import com.example.countrybriefer.model.Country
import com.example.countrybriefer.util.downloadFromUrl
import com.example.countrybriefer.util.placeholderProgressBar
import com.example.countrybriefer.view.GeneralCountriesDirections

//import kotlinx.android.synthetic.main.item_country.view.name
//import kotlinx.android.synthetic.main.item_country.view.region

class CountryAdapter (val countryList: ArrayList<Country>):RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(var view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_country,parent,false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        //Databinding way
        val nameTextView = holder.view.findViewById<TextView>(R.id.name)
        val regionTextView = holder.view.findViewById<TextView>(R.id.region)
        val imageView = holder.view.findViewById<ImageView>(R.id.imageView)
        nameTextView.text=countryList[position].countryName
        regionTextView.text=countryList[position].countryRegion

        holder.view.setOnClickListener {
            val action = GeneralCountriesDirections.actionGeneralCountriesToSelectedCountryFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)

        }

        imageView.downloadFromUrl(countryList[position].imageUrl, placeholderProgressBar(holder.view.context))

        //Lecturer's old way
        //holder.view.name.text = countryList[position].countryName
        //holder.view.region.text = countryList[position].countryRegion
    }

    fun updateCountryList(newCountryList: List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

}