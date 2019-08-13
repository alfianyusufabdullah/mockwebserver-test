package alfianyusufabdullah.mockwebservertest

import alfianyusufabdullah.mockwebservertest.main.MainActivity
import alfianyusufabdullah.mockwebservertest.network.UserDataSource
import alfianyusufabdullah.mockwebservertest.util.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    private lateinit var mockResponseSuccess: MockResponse
    private lateinit var mockResponseEmpty: MockResponse
    private lateinit var mockResponseError: MockResponse

    private val mockWebServer = MockWebServer()
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {
        mockWebServer.start()
        UserDataSource.baseUrl = mockWebServer.url("/").toString()

        mockResponseSuccess = context.createResponse(localResponsePath = "response_success.json", responseCode = 200)
        mockResponseEmpty = context.createResponse(localResponsePath = "response_success_empty.json", responseCode = 200)
        mockResponseError = context.createResponse(responseCode = 505)
    }

    @After
    fun stop(){
        mockWebServer.shutdown()
    }

    @Test
    fun test_search_should_be_success() {
        mockWebServer.enqueue(mockResponseSuccess)

        R.id.et_search.typeText("alfian")
        R.id.btn_search.click()

        R.id.user_list.isDisplayed()
    }

    @Test
    fun test_search_should_be_success_but_result_is_empty() {
        mockWebServer.enqueue(mockResponseEmpty)

        val textExpected = String.format(context.getString(R.string.empty_query), "alfian")

        R.id.et_search.typeText("alfian")
        R.id.btn_search.click()

        R.id.tv_error.isDisplayed()
        R.id.tv_error.matchesWith(textExpected)
    }

    @Test
    fun test_search_should_be_failed() {
        mockWebServer.enqueue(mockResponseError)

        val textExpected = "Something error dude"

        R.id.et_search.typeText("alfian")
        R.id.btn_search.click()

        R.id.tv_error.isDisplayed()
        R.id.tv_error.matchesWith(textExpected)
    }

    @Test
    fun test_search_with_empty_query() {
        val textExpected = "Username harus diisi terlebih dahulu"

        R.id.btn_search.click()

        R.id.tv_error.isDisplayed()
        R.id.tv_error.matchesWith(textExpected)
    }
}