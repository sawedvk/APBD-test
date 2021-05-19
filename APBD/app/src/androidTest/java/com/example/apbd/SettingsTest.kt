package com.example.apbd

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SettingsTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(Settings::class.java)

// Test 7
    @Test
    fun click_Save_Button() {
        Espresso.onView(ViewMatchers.withId(R.id.button2)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.home_activity)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}