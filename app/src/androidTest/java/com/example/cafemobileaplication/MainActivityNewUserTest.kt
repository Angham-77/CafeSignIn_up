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
class MainActivityNewUserTest {
    @Before
    fun setup() {
        ActivityScenario.launch(MainActivityNewUser::class.java).moveToState(Lifecycle.State.RESUMED)
    }
   /* @Test
    fun testRegister_Successful() {
        val fullname = "test"
        val email = "test@gmail.com"
        val PhoneNo = "098766789"
        val isActive = 1
        val username = "test"
        val password = "test"
        onView(withId(R.id.editTextNewUserName)).perform(typeText(username))
        onView(withId(R.id.editTextNewUserPassword)).perform(typeText(password))

        onView(withId(R.id.editTextFullName)).perform(typeText(fullname))
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText(email))
        onView(withId(R.id.editTextPhoneNo)).perform(typeText(PhoneNo))
        onView(withId(R.id.radioGroupActivity)).perform(typeText(isActive.toString()))
        closeSoftKeyboard()
        onView(withId(R.id.buttonSave)).perform(click())
        onView(withId(R.id.textViewMessage)).check(matches(withText("User name is already exist")))*/
   // }

}