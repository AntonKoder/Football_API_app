package com.example.footballapiapp.screens.countries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.footballapiapp.APP_ACTIVITY
import com.example.footballapiapp.R
import com.example.footballapiapp.models.ui.CountryUI
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou


class CountriesAdapter : RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {

    private var listCountries = emptyList<CountryUI>()

    fun setData(list: List<CountryUI>) {
        listCountries = list
        notifyDataSetChanged()
    }

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = itemView.findViewById<TextView>(R.id.country_name)
        val code = itemView.findViewById<TextView>(R.id.country_code)
        val flag = itemView.findViewById<ImageView>(R.id.country_flag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.name.text = listCountries[position].name
        holder.code.text = listCountries[position].code
        if (
            listCountries[position].flag != null
        ) {
            GlideToVectorYou.justLoadImage(APP_ACTIVITY, listCountries[position].flag?.toUri(), holder.flag)
        }
    }


    override fun getItemCount(): Int {
        return listCountries.size
    }
}