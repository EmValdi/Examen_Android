package com.example.kotlin.examen.data.network

import android.util.Log
import com.example.kotlin.examen.data.network.model.CovidObject

class CovidApiClient {
    private lateinit var api: CovidAPIService

    /**
     * Utiliza el API service para enviar la fecha como parametro
     * en la url y recuperar la información
     */

    suspend fun getCovidDay(date:String): CovidObject?{
        Log.d("Antes de Api:","Enviando: $date")
        api = CovidNetworkModule()
        return try{
            api.getDayData(date)
        }catch (e:java.lang.Exception){
            e.printStackTrace()
            null
        }
    }

}