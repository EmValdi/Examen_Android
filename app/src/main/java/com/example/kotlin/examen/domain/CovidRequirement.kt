package com.example.kotlin.examen.domain

import com.example.kotlin.examen.data.network.model.CovidObject
import com.example.kotlin.examen.data.repositories.CovidRepository

class CovidRequirement {
    private val repository = CovidRepository()

    suspend operator fun invoke(
        date:String
    ): CovidObject? = repository.getCovidList(date)
}