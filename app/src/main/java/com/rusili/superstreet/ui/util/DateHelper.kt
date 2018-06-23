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

        fun getDateDifferenceString(date: Date): String {
            val dateDiff = getDateDifference(date)

            var dateDiffString = dateDiff.length.toString() + " " + dateDiff.period.name
            if (dateDiff.length > 1) {
                dateDiffString += "s"
            }
            return dateDiffString + "ago"
        }

        fun getDateDifference(date: Date): DateDiffWrapper {
            val today = Date()
            val diffInMillis = today.time - date.time
            val diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);

            return when {
                diffInDays < 7 -> DateDiffWrapper(diffInDays, TimePeriod.DAY)
                diffInDays < 30 -> DateDiffWrapper(diffInDays / 7, TimePeriod.WEEK)
                diffInDays < 365 -> DateDiffWrapper(diffInDays / 30, TimePeriod.MONTH)
                else -> DateDiffWrapper(diffInDays / 365, TimePeriod.YEAR)
            }
        }
    }

    data class DateDiffWrapper(val length: Long,
                               val period: TimePeriod)
}