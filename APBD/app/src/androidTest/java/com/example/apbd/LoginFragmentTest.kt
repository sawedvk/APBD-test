package com.example.apbd

import androidx.test.espresso.Espresso.closeSoftKeyboard
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

class RegisterCheck(){
    @Rule @JvmField

    var activityTestRule = ActivityTestRule(ConfirmRegis::class.java)

    @Test
    fun clickConfirmRegister (){
        onView(withId(R.id.editTextNumber6)).perform(ViewActions.typeText("1234"))

        onView(withId(R.id.buttonRegister3)).perform(ViewActions.click())
        onView(withId(R.id.Login)).check(matches(isDisplayed()))
    }
}