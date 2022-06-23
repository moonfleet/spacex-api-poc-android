package com.moonfleet.spacex.ui

import android.app.Activity
import android.view.View
import androidx.test.espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.moonfleet.spacex.coreui.base.BaseActivity
import org.hamcrest.Matcher
import org.hamcrest.Matchers

object TestExtensions {

    /**
     * Updates the IdlingRegistry to attach to the idling resource of the current Activity
     */
    fun bindNewActivity() {
        getPreviousActivity()?.let {
            IdlingRegistry.getInstance().unregister(it.idlingResource)
        }
        getCurrentActivity()?.let { it ->
            it.idlingResource?.let { resource ->
                IdlingRegistry.getInstance().unregister(resource)
                IdlingRegistry.getInstance().register(resource)
            }
        }
    }

    fun getPreviousActivity(): BaseActivity? {
        val previousActivity = arrayOfNulls<Activity>(1)
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            val allActivities = ActivityLifecycleMonitorRegistry.getInstance()
                .getActivitiesInStage(Stage.STOPPED)
            if (!allActivities.isEmpty()) {
                previousActivity[0] = allActivities.iterator().next()
            }
        }
        return previousActivity[0] as BaseActivity?
    }

    fun getCurrentActivity(): BaseActivity? {
        val currentActivity = arrayOfNulls<Activity>(1)
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            val allActivities = ActivityLifecycleMonitorRegistry.getInstance()
                .getActivitiesInStage(Stage.RESUMED)
            if (!allActivities.isEmpty()) {
                currentActivity[0] = allActivities.iterator().next()
            }
        }
        return currentActivity[0] as BaseActivity?
    }

    /**
     * Updates the IdlingRegistry to unbind to the idling resource of all resumed, stopped or destroyed Activities
     */
    fun unbindActivities(){
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            val resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED)
            val stoppedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.STOPPED)
            val destroyedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.DESTROYED)
            val allActivities = mutableListOf<Activity>()
            allActivities.addAll(resumedActivities)
            allActivities.addAll(stoppedActivities)
            allActivities.addAll(destroyedActivities)
            allActivities.forEach {
                IdlingRegistry.getInstance().unregister((it as BaseActivity).idlingResource)
            }
        }
    }

    fun pause(millis: Long) {
        Thread.sleep(millis)
    }

    fun checkText(text: String) = Espresso.onView(
        Matchers.allOf(
            ViewMatchers.withText(text),
            ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
        )
    ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    fun click(id: Int) = with(Espresso.onView(
        Matchers.allOf(
            ViewMatchers.withId(id),
            ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
        )
    )) {
        check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        perform(noConstraintsClick())
    }

    fun clickText(text: String) = with(Espresso.onView(
        Matchers.allOf(
            ViewMatchers.withText(text),
            ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
        )
    )) {
        check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        perform(ViewActions.click())
    }

    fun noConstraintsClick(): ViewAction = object : ViewAction {
        override fun getConstraints(): Matcher<View> = ViewMatchers.isEnabled()

        override fun getDescription(): String = ""

        override fun perform(uiController: UiController, view: View) {
            view.performClick()
        }
    }


    fun getString(id: Int) = InstrumentationRegistry.getInstrumentation().getTargetContext().getString(id)

}