package com.rusili.superstreet.previewlist

import android.annotation.SuppressLint
import androidx.annotation.VisibleForTesting
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

@SuppressLint("SimpleDateFormat")
private val dateFormat = SimpleDateFormat("MMM dd, yyyy")

private const val STRING_TODAY = "Today"
private const val STRING_YESTERDAY = "Yesterday"

class DateHelper {

    fun formatToMMMDDYYY(date: Date): String =
        dateFormat.format(date)

    fun getDateDifferenceString(
        todaysDate: Date,
        articleDate: Date
    ): String =
        getDateDifference(todaysDate, articleDate).let {
            when (it) {
                DateDiffWrapper(0, TimePeriod.Day) -> return STRING_TODAY
                DateDiffWrapper(1, TimePeriod.Day) -> return STRING_YESTERDAY
                else -> {
                    var dateDiffString = it.length.toString() + " " + it.period.name
                    if (it.length > 1) {
                        dateDiffString += "s"
                    }
                    return dateDiffString.toLowerCase() + " ago"
                }
            }
        }

    @VisibleForTesting
    fun getDateDifference(
        todaysDate: Date,
        articleDate: Date
    ): DateDiffWrapper {
        val diffInMillis = todaysDate.time - articleDate.time
        val diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);

        return when {
            diffInDays < 7 -> DateDiffWrapper(diffInDays, TimePeriod.Day)
            diffInDays < 30 -> DateDiffWrapper(diffInDays / 7, TimePeriod.Week)
            diffInDays < 365 -> DateDiffWrapper(diffInDays / 30, TimePeriod.Month)
            else -> DateDiffWrapper(diffInDays / 365, TimePeriod.Year)
        }
    }

    data class DateDiffWrapper(
        val length: Long,
        val period: TimePeriod
    )
}
