package com.artful.curatolist.repository

import com.artful.curatolist.model.ResponsePage
import com.artful.curatolist.network.ApiService

class CuratolistRepository(private val apiService: ApiService) {
    suspend fun getArt(page: Int, q: String?): ResponsePage {
        return apiService.getArt(page, q)
    }
}