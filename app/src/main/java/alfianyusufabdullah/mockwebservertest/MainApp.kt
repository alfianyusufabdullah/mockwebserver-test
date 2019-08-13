package alfianyusufabdullah.mockwebservertest

import android.app.Application
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import androidx.core.text.set
import androidx.core.text.toSpannable
import com.androidnetworking.AndroidNetworking

open class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(this.applicationContext)
    }
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun String.makeUsernameBold(): Spannable {
    val startIndex = indexOfFirst { it.toString() == "'" }
    val endIndex = indexOfLast { it.toString() == "'" }

    val hasBold = toSpannable()
    hasBold[(startIndex + 1)..endIndex] = StyleSpan(Typeface.BOLD)
    hasBold[(startIndex + 1)..endIndex] = ForegroundColorSpan(Color.parseColor("#00574B"))
    return hasBold
}