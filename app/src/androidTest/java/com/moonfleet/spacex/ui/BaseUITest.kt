package com.moonfleet.spacex.ui

import androidx.test.espresso.IdlingPolicies
import com.moonfleet.spacex.ui.TestExtensions.unbindActivities
import org.junit.After
import org.junit.Before
import java.util.concurrent.TimeUnit

open class BaseUITest {

    @Before
    open fun setUp() {
        IdlingPolicies.setMasterPolicyTimeout(50, TimeUnit.SECONDS)
        IdlingPolicies.setIdlingResourceTimeout(50, TimeUnit.SECONDS)
    }

    @After
    open fun tearDown() {
        unbindActivities()
    }
}