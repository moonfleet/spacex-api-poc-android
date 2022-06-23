package com.moonfleet.spacex.launchregistry.usecase

import com.moonfleet.spacex.feature.launchregistry.model.Launch
import com.moonfleet.spacex.feature.launchregistry.ui.model.LaunchFilter
import com.moonfleet.spacex.feature.launchregistry.usecase.FilterLaunchesUseCase
import com.moonfleet.spacex.feature.launchregistry.year
import com.moonfleet.spacex.launchregistry.utils.TestUtils.timestamp
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class FilterLaunchesUseCaseTest {

    val useCase = FilterLaunchesUseCase()
    val mockLaunches = MutableList(51) { index ->
        mockk<Launch>() {
            every { success } returns (index % 2 == 0)
            every { dateUnix } returns timestamp(2000 + index)
        }
    }

    @Test
    fun `test null filter`() {
        assertEquals(mockLaunches, useCase(mockLaunches, null))
        assertNotEquals(mockLaunches.shuffled(), useCase(mockLaunches, null))
    }

    @Test
    fun `test years filter`() {
        val yearFilteredLaunches = useCase(mockLaunches, LaunchFilter(YEARS_MAP))
        assertEquals(YEARS_MAP.size, yearFilteredLaunches.size)
        yearFilteredLaunches.map { it.year() }.groupBy { it }.also {
            assertTrue(it.containsKey(2000))
            assertTrue(it.containsKey(2001))
            assertTrue(it.containsKey(2002))
            assertTrue(it.containsKey(2003))
            assertTrue(it.containsKey(2004))
            assertFalse(it.containsKey(2005))
        }
    }

    @Test
    fun `test successful filter`() {
        val successfulLaunches = useCase(
            mockLaunches,
            LaunchFilter(YEARS_MAP, true)
        )
        assertEquals(3, successfulLaunches.size)
        successfulLaunches.map { it.year() }.groupBy { it }.also {
            assertTrue(it.containsKey(2000))
            assertTrue(it.containsKey(2002))
            assertTrue(it.containsKey(2004))
            assertFalse(it.containsKey(2001))
            assertFalse(it.containsKey(2003))
        }
    }

    @Test
    fun `test unsuccessful filter`() {
        val successfulLaunches = useCase(
            mockLaunches,
            LaunchFilter(YEARS_MAP, false)
        )
        assertEquals(2, successfulLaunches.size)
        successfulLaunches.map { it.year() }.groupBy { it }.also {
            assertTrue(it.containsKey(2001))
            assertTrue(it.containsKey(2003))
            assertFalse(it.containsKey(2000))
            assertFalse(it.containsKey(2002))
            assertFalse(it.containsKey(2004))
        }
    }

    companion object {
        val YEARS_MAP = mapOf(
            2000 to true,
            2001 to true,
            2002 to true,
            2003 to true,
            2004 to true
        )
    }

}