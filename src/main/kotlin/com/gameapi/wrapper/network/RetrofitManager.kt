package com.gameapi.wrapper.network

import jakarta.inject.Inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager @Inject constructor() {

    @Inject
    lateinit var retrofit: Retrofit

    fun <T> createClient(service: Class<T>): T = retrofit.create(service)

    fun createService(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl) // Dynamically set base URL here
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
