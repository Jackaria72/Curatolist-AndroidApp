package com.artful.curatolist.network

import com.artful.curatolist.model.Artwork
import com.artful.curatolist.model.ResponsePage
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService
{
    @GET("art")
    suspend fun getArt(
        @Query("page") page: Int,
        @Query("source") source: String?,
        @Query("q") q: String?,
        @Query("sort") sort: String?,
        @Query("filter") filter: String?
    ): ResponsePage
}