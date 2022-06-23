package com.moonfleet.spacex.coreui.test

import androidx.test.espresso.idling.CountingIdlingResource
import com.moonfleet.spacex.coreui.BuildConfig

object IdlingResourceFactory {

    fun produce(tag: String) : CountingIdlingResource? {
        if (BuildConfig.DEBUG) {
            return CountingIdlingResource(tag)
        } else {
            return null
        }
    }

}