package com.moonfleet.spacex.ui

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moonfleet.spacex.ui.TestExtensions.bindNewActivity
import com.moonfleet.spacex.ui.TestExtensions.checkText
import com.moonfleet.spacex.ui.TestExtensions.click
import com.moonfleet.spacex.ui.TestExtensions.clickText
import com.moonfleet.spacex.ui.TestExtensions.getString
import com.moonfleet.spacex.feature.launchregistry.R
import com.moonfleet.spacex.feature.launchregistry.ui.view.LaunchesActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class LaunchesActivityTest : BaseUITest() {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityRule: ActivityScenarioRule<LaunchesActivity> = ActivityScenarioRule(LaunchesActivity::class.java)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testLaunchesList() {
        bindNewActivity()
        checkText(getString(R.string.mission))
    }

    @Test
    fun testFilterDialog() {
        bindNewActivity()
        click(R.id.action_filter)
        click(R.id.checkbox_year)
        clickText(getString(R.string.apply))
        checkText(getString(R.string.no_launches))
    }


}