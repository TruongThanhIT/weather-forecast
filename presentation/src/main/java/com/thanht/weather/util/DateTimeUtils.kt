package com.thanht.weather.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private val DATE_FORMAT =
    SimpleDateFormat("EEE, dd MMM yyyy", Locale.ENGLISH)
object DateTimeUtils {

    fun Long.formatDate(): String {
        try {
            return DATE_FORMAT.format(this.times(1000))
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""
    }
}