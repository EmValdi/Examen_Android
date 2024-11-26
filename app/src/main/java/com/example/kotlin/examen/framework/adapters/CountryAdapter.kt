package com.example.kotlin.examen.framework.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.examen.data.network.model.CovidObjectItem
import com.example.kotlin.examen.databinding.ItemCountryBinding
import com.example.kotlin.examen.framework.adapters.viewholders.CovidViewHolder

class CountryAdapter: RecyclerView.Adapter<CovidViewHolder>() {
    lateinit var context: Context
    var data:ArrayList<CovidObjectItem> = ArrayList()

    fun CovidAdapter(basicData : ArrayList<CovidObjectItem>, context: Context){
        this.data = basicData
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CovidViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CovidViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item,context)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}