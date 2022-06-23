package com.moonfleet.spacex.coreui.base

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    fun snackbar(text: String) {
        (activity as BaseActivity).snackbar(text)
    }

    fun toBusy() {
        (activity as BaseActivity).toBusy()
    }

    fun toIdle() {
        (activity as BaseActivity).toIdle()
    }

}