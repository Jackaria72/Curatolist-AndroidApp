package com.artful.curatolist.repository

import android.util.Log
import com.artful.curatolist.model.ResponsePage
import com.artful.curatolist.network.ApiService

class CuratolistRepository(private val apiService: ApiService) {
    suspend fun getArt(page: Int, search: String, source: String, filterType: String, filterValue: String, sort: String): ResponsePage {

        val sanitizedSearch = if(search.isEmpty()) null else source
        val sanitizedSource = if(source == "Select Source" || source == "both") null else source
        val sanitizedFilter = if(filterType.contains("Filter")) null else filterType
        val sanitizedFilterValue = if(filterValue.isEmpty() || filterValue.contains("Value")) null else filterValue
        val combinedFilter = if (sanitizedFilter.isNullOrEmpty() || sanitizedFilterValue.isNullOrEmpty()) null else "$sanitizedFilter:$sanitizedFilterValue"
        val sanitizedSort = if(sort == "default" || sort.contains("Sort")) null else sort

        Log.d("CuratolistRepository", "Sanitized Parameters: " +
                "source=$sanitizedSource, " +
                "filterType=$sanitizedFilter, " +
                "filterValue=$sanitizedFilterValue, " +
                "combinedFilter=$combinedFilter, " +
                "sort=$sanitizedSort"
        )

        return apiService.getArt(page, sanitizedSource, sanitizedSearch, sanitizedSort, combinedFilter)
    }
}