package com.moonfleet.spacex.launchregistry.utils

import java.util.*

object TestUtils {

    fun timestamp(year: Int) = (Calendar.getInstance().also {
        it.set(Calendar.YEAR, year)
        it.set(Calendar.DAY_OF_YEAR, 32)
        it.set(Calendar.HOUR_OF_DAY, 23)
        it.set(Calendar.MINUTE, 59)
    }.timeInMillis / 1000L).toInt()

    val ARTICLE_URL = "articleurl"
    val WEBCAST_URL = "webcasturl"
    val WIKI_URL = "wikiurl"

}