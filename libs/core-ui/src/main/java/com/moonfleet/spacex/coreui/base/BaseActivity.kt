package com.moonfleet.spacex.coreui.base

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.moonfleet.spacex.coreui.test.IdlingResourceFactory

open class BaseActivity: AppCompatActivity() {

    val idlingResource = IdlingResourceFactory.produce(this::class.java.simpleName)

    fun snackbar(text: String) {
        Snackbar.make(findViewById(android.R.id.content), text, Snackbar.LENGTH_SHORT).show()
    }

    fun openUrl(uri: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(uri))
        intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            snackbar("No viewer found for URL: $uri")
        }

    }

    fun toBusy() {
        idlingResource?.increment()
    }

    fun toIdle() {
        idlingResource?.let {
            while (!idlingResource.isIdleNow) {
                idlingResource.decrement()
            }
        }
    }

}