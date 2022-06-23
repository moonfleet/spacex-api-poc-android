package com.moonfleet.spacex.coreui.navigation

import android.app.Activity
import android.content.Intent

open class Coordinator {

    fun open(activity: Activity, intent: Intent) {
        activity.startActivity(intent)
    }

    fun openAndFinish(activity: Activity, intent: Intent) {
        open(activity, intent)
        activity.finish()
    }
}