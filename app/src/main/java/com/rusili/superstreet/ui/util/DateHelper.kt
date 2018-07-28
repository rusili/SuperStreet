package com.rusili.superstreet.ui.util

import android.annotation.SuppressLint
import androidx.annotation.VisibleForTesting
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

@SuppressLint("SimpleDateFormat")
private val dateFormat = SimpleDateFormat("MMM dd, yyyy")

// TODO: Inject this class
class DateHelper {

    companion object {
        fun formatToMMMDDYYY(date: Date): String =
                dateFormat.format(date)

        fun getDateDifferenceString(todaysDate: Date,
                                    articleDate: Date): String {
            val dateDiff = getDateDifference(todaysDate, articleDate)

            var dateDiffString = dateDiff.length.toString() + " " + dateDiff.period.name
            if (dateDiff.length > 1) {
                dateDiffString += "s"
            }
            return dateDiffString.toLowerCase() + " ago"
        }

        fun getDateDifference(todaysDate: Date,
                                      articleDate: Date): DateDiffWrapper {
            val diffInMillis = todaysDate.time - articleDate.time
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