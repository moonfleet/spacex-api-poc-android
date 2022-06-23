package com.moonfleet.spacex.launchregistry.usecase

import com.moonfleet.spacex.feature.launchregistry.model.Launch
import com.moonfleet.spacex.feature.launchregistry.model.Rocket
import com.moonfleet.spacex.feature.launchregistry.usecase.ConvertLaunchUseCase
import com.moonfleet.spacex.feature.launchregistry.year
import com.moonfleet.spacex.launchregistry.utils.TestUtils.ARTICLE_URL
import com.moonfleet.spacex.launchregistry.utils.TestUtils.WEBCAST_URL
import com.moonfleet.spacex.launchregistry.utils.TestUtils.WIKI_URL
import com.moonfleet.spacex.launchregistry.utils.TestUtils.timestamp
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class ConvertUseCaseTest {

    val useCaseDateFormat = ConvertLaunchUseCase(DATE_FORMAT_DATE)
    val useCaseTimeFormat = ConvertLaunchUseCase(DATE_FORMAT_TIME)

    val mockLaunchPast = mockk<Launch>(relaxed = true) {
        every { links.article } returns ARTICLE_URL
        every { links.webcast } returns WEBCAST_URL
        every { links.wikipedia } returns WIKI_URL
        every { dateUnix } returns TIMESTAMP_PAST
    }

    val mockLaunchFuture = mockk<Launch>(relaxed = true) {
        every { dateUnix } returns TIMESTAMP_FUTURE
    }

    val mockRocket = mockk<Rocket>() {
        every { name } returns ROCKET_NAME
        every { type } returns ROCKET_TYPE
    }

    @Test
    fun `test launch year`() {
        assertEquals(YEAR_PAST, mockLaunchPast.year())
    }

    @Test
    fun `test launch links conversion`() {
        val model = useCaseDateFormat(mockLaunchPast, mockRocket, ROCKET_TEMPLATE, "", "")
        assertEquals(ARTICLE_URL, model.article)
        assertEquals(WEBCAST_URL, model.webcast)
        assertEquals(WIKI_URL, model.wikipedia)
    }

    @Test
    fun `test launch date in past conversion`() {
        val model = useCaseDateFormat(mockLaunchPast, mockRocket, ROCKET_TEMPLATE, DAYS_TITLE_SINCE, DAYS_TITLE_FROM)
        assertEquals(DATE_FORMATTED_PAST, model.timestamp)
        assertEquals(DAYS_TITLE_SINCE, model.daysDiffTitle)
        assertTrue(model.daysDiff >= 0)

    }

    @Test
    fun `test launch date in future conversion`() {
        val model = useCaseDateFormat(mockLaunchFuture, mockRocket, ROCKET_TEMPLATE, DAYS_TITLE_SINCE, DAYS_TITLE_FROM)
        assertEquals(DATE_FORMATTED_FUTURE, model.timestamp)
        assertEquals(DAYS_TITLE_FROM, model.daysDiffTitle)
        assertTrue(model.daysDiff >= 0)

    }

    @Test
    fun `test launch time conversion`() {
        val model = useCaseTimeFormat(mockLaunchPast, mockRocket, ROCKET_TEMPLATE, "", "")
        assertEquals(TIME_FORMATTED, model.timestamp)
    }

    @Test
    fun `test launch rocket conversion`() {
        val model = useCaseDateFormat(mockLaunchPast, mockRocket, ROCKET_TEMPLATE, "", "")
        assertEquals("${mockRocket.name}${mockRocket.type}", model.rocket)
    }

    companion object {
        val DATE_FORMAT_DATE = SimpleDateFormat("ddMMyy", Locale.getDefault())
        val DATE_FORMAT_TIME = SimpleDateFormat("HHmm", Locale.getDefault())

        val ROCKET_NAME = "longrocketname"
        val ROCKET_TYPE = "baserockettype"

        val DAYS_TITLE_SINCE = "past"
        val DAYS_TITLE_FROM = "future"

        val ROCKET_TEMPLATE = "%s%s"

        val YEAR_PAST = 2006
        val YEAR_FUTURE = 2026
        val DATE_FORMATTED_PAST = "010206"
        val DATE_FORMATTED_FUTURE = "010226"
        val TIME_FORMATTED = "2359"

        val TIMESTAMP_PAST = timestamp(YEAR_PAST)
        val TIMESTAMP_FUTURE = timestamp(YEAR_FUTURE)
    }

}