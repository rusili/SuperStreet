package com.rusili.superstreet.ui.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

@SuppressLint("SimpleDateFormat")
private val dateFormat = SimpleDateFormat("MMM dd, yyyy")

class DateHelper {

    companion object {
        fun formatToMMMDDYYY(date: Date): String =
                dateFormat.format(date)

        fun convertToNumOfDaysAgo(date: Date): Int {
            val today = Date()
            val diffInMillis = today.time - date.time
            return TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS).toInt();
        }
    }
}