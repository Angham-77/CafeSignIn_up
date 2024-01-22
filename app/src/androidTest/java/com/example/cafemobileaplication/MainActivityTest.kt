package com.example.cafemobileaplication

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java).moveToState(Lifecycle.State.RESUMED)
    }
    @Test
    fun testLogin_Successful() {
        val username = "test"
        val password = "test"
        onView(withId(R.id.editTextUserName)).perform(typeText(username))
        onView(withId(R.id.editTextPassword)).perform(typeText(password))
        closeSoftKeyboard()
        onView(withId(R.id.buttonSignin)).perform(click())
        onView(withId(R.id.textViewMessage)).check(matches(withText("User Not Found, Please try again")))
    }
}
