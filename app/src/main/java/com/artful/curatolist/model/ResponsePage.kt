package com.artful.curatolist.model

data class ResponsePage(
    val pageInfo: PageInfo,
    val artwork: List<Artwork>
) {
    data class PageInfo(
        val chicagoTotal: Int,
        val chicagoPageTotal: Int,
        val harvardTotal: Int,
        val harvardPageTotal: Int,
        val combinedTotal: Int,
        val combinedPageTotal: Int
    )
}
