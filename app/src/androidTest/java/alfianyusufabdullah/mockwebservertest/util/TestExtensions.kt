package alfianyusufabdullah.mockwebservertest.util

import android.content.Context
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import okhttp3.mockwebserver.MockResponse

fun Context.createResponse(localResponsePath: String = "", responseCode: Int): MockResponse {
    return MockResponse().apply {
        val response = if (localResponsePath.isNotEmpty()) {
            JSONReader.read(this@createResponse, localResponsePath)
        } else {
            ""
        }

        setBody(response)
        setResponseCode(responseCode)
    }
}

fun Int.typeText(stringToBeTyped: String) {
    Espresso.onView(ViewMatchers.withId(this)).perform(ViewActions.typeText(stringToBeTyped))
}

fun Int.isDisplayed() {
    Espresso.onView(ViewMatchers.withId(this)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
}

fun Int.click() {
    Espresso.onView(ViewMatchers.withId(this)).perform(ViewActions.click())
}

fun Int.matchesWith(expected: String) {
    Espresso.onView(ViewMatchers.withId(this))
        .check(ViewAssertions.matches(ViewMatchers.withText(expected)))
}