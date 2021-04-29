package com.example.apbd

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BootUpTest{
    @Rule @JvmField

    var activityTestRule = ActivityTestRule(MainActivity::class.java)

// Test 5
    @Test
    fun openLogin(){
        onView(withId(R.id.buttonLogin)).perform(ViewActions.click())
        onView(withId(R.id.Login)).check(matches(isDisplayed()))
    }
// Test 6
    @Test
    fun openRegister(){
        onView(withId(R.id.buttonRegister)).perform(ViewActions.click())
        onView(withId(R.id.Register_Page)).check(matches(isDisplayed()))
    }
}