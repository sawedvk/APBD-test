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
class UserLogin{
    @Rule @JvmField

    var activityTestRule = ActivityTestRule(LoginPage::class.java)

    // Test 10
    @Test
    fun clickLogin (){
        onView(withId(R.id.editTextTextEmailAddress)).perform(ViewActions.typeText("test@gmail.com"))
        onView(withId(R.id.editTextTextPassword)).perform(ViewActions.typeText("1234"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.buttonRegisterGoogle2)).perform(ViewActions.click())


        onView(withId(R.id.home_activity)).check(matches(isDisplayed()))
    }
}