package com.artful.curatolist.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api : ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/curatolist/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}