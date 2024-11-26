package com.example.kotlin.examen.data.network

import com.example.kotlin.examen.data.network.model.CovidObject

class CovidApiClient {
    private lateinit var api: CovidAPIService

    suspend fun getCovidDay(date:String): CovidObject?{
        api = CovidNetworkModule()
        return try{
            api.getDayData(date)
        }catch (e:java.lang.Exception){
            e.printStackTrace()
            null
        }
    }

}