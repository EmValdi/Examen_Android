package com.example.kotlin.examen.domain

import com.example.kotlin.examen.data.network.model.CovidObject
import com.example.kotlin.examen.data.repositories.CovidRepository

class CovidRequirement {
    private val repository = CovidRepository()

    /**
     * Llama al repositorio y le da los datos de fecha para que este haga la llamada al
     * API
     */

    suspend operator fun invoke(
        date:String
    ): CovidObject? = repository.getCovidList(date)
}