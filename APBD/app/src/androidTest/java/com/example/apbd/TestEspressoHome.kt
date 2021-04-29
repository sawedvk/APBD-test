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
class EspressoHome{

    @Rule @JvmField
    var activityTestRule = ActivityTestRule(Home::class.java)

    @Test
    fun click_Home_Button(){
        Espresso.onView(ViewMatchers.withId(R.id.imageView)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.homelayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun click_My_PLan_Button(){
        Espresso.onView(ViewMatchers.withId(R.id.imageView2)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.planName)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun click_History_Button(){
        Espresso.onView(ViewMatchers.withId(R.id.imageView6)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.historylayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun click_Settings_Button(){
        Espresso.onView(ViewMatchers.withId(R.id.imageView7)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.settingslayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }



}