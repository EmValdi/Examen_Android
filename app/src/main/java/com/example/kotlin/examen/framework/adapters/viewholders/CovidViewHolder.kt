package com.example.kotlin.examen.framework.adapters.viewholders

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.examen.data.network.model.CovidObjectItem
import com.example.kotlin.examen.databinding.ItemCountryBinding

class CovidViewHolder(private val binding: ItemCountryBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CovidObjectItem, context: Context){
        binding.countryName.text = item.country
        binding.cases.text = item.cases.total.toString()
        binding.newcases.text = item.cases.new.toString()
    }
}