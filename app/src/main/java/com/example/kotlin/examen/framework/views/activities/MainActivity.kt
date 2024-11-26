package com.example.kotlin.examen.framework.views.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.examen.R
import com.example.kotlin.examen.data.network.model.CovidObjectItem
import com.example.kotlin.examen.databinding.ActivityMainBinding
import com.example.kotlin.examen.framework.adapters.CountryAdapter
import com.example.kotlin.examen.framework.viewmodel.MainViewModel
import java.io.File

class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter : CountryAdapter = CountryAdapter()
    lateinit var date: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        initializeBinding()
        initializeObservers()
        initializeListeners()

    }

    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initializeObservers() {
        viewModel.covidObjectLiveData.observe(this){ covidObject ->
            val mexicoData = covidObject!!.find { it.country == "Mexico" }
            mexicoData?.let {
                binding.cases.text = it.cases.total.toString()
                binding.newcases.text = it.cases.new.toString()
            }
            setUpRecyclerView(covidObject!!)
        }

        viewModel.toastMessage.observe(this) { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initializeListeners() {

        binding.datebutton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Mostrar el DatePickerDialog
            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    // Actualizar el TextView con la fecha seleccionada
                    val formattedDate = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay)
                    date = formattedDate
                    viewModel.getCovidList(formattedDate)
                },
                year, month, day
            )
            datePickerDialog.show()
        }
    }

    /**
     * Recibe una lista con los datos de la API para generar el recyclerview
     */


    private fun setUpRecyclerView(dataForList:ArrayList<CovidObjectItem>){
        binding.RVCovid.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false)
        binding.RVCovid.layoutManager = linearLayoutManager
        adapter.CovidAdapter(dataForList,this)
        binding.RVCovid.adapter = adapter
    }

}
