package com.artful.curatolist.model

data class SearchQueries(
    val search: String,
    val source: String,
    val filterType: String,
    val filterValue: String,
    val sort: String
)
