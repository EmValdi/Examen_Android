package com.example.kotlin.examen.data.network

import com.example.kotlin.examen.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CovidNetworkModule {
    private val gsonFactory: GsonConverterFactory = GsonConverterFactory.create()
    private val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("X-Api-Key", Constants.APIKEY)
                .build()
            chain.proceed(request)
        }
        .build()

    operator fun invoke(): CovidAPIService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonFactory)
            .build()
            .create(CovidAPIService::class.java)
    }
}