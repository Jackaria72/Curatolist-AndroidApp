package com.artful.curatolist.network

import com.artful.curatolist.model.Artwork
import retrofit2.http.GET

interface ApiService
{
    @GET("art")
    suspend fun getArt(): List<Artwork>
}