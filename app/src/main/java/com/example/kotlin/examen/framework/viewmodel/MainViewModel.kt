package com.example.kotlin.examen.framework.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.examen.data.network.model.CovidObject
import com.example.kotlin.examen.domain.CovidRequirement
import com.example.kotlin.examen.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val covidRequirement = CovidRequirement()
    val covidObjectLiveData = MutableLiveData<CovidObject?>()

    private val _toastMessage = MutableLiveData<String?>()
    val toastMessage: LiveData<String?> get() = _toastMessage

    fun getCovidList(date: String){
        viewModelScope.launch(Dispatchers.IO) {
            val result: CovidObject? = covidRequirement(date)
            CoroutineScope(Dispatchers.Main).launch {
                if(result!!.isEmpty()){
                    _toastMessage.value = "No hay datos para este dia."
                }
                covidObjectLiveData.postValue(result)
            }
        }
    }
}