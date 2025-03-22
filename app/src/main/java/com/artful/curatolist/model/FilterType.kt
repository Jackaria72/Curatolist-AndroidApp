package com.artful.curatolist.model

sealed class FilterType(val displayName: String) {
    object SelectFilter : FilterType("Select Filter Type")
    object Classification : FilterType("Classification")
    object Technique : FilterType("Technique")
    object Medium: FilterType("Medium")

    companion object {
        val filterTypes = listOf(SelectFilter, Classification, Technique, Medium)
    }
}