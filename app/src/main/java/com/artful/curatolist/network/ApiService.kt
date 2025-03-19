package com.artful.curatolist.network

import com.artful.curatolist.model.Artwork
import com.artful.curatolist.model.ResponsePage
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService
{
    @GET("art")
    suspend fun getArt(
        @Query("page") page: Int
    ): ResponsePage
}