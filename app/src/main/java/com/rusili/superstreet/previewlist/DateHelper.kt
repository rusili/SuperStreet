package com.rusili.superstreet.previewlist

import androidx.annotation.VisibleForTesting
import java.util.Date
import java.util.concurrent.TimeUnit

private const val STRING_TODAY = "Today"
private const val STRING_YESTERDAY = "Yesterday"

class DateHelper {

    fun getDateDifferenceString(
        todaysDate: Date,
        articleDate: Date
    ): String =
        getDateDifference(todaysDate, articleDate).let {
            when (it) {
                DateDiffWrapper(0, TimePeriod.Day) -> STRING_TODAY
                DateDiffWrapper(1, TimePeriod.Day) -> STRING_YESTERDAY
                else -> {
                    var value = it.length.toString() + " " + it.period.name
                    if (it.length > 1) {
                        value += "s"
                    }
                    value.toLowerCase() + " ago"
                }
            }
        }

    @VisibleForTesting
    fun getDateDifference(
        todaysDate: Date,
        articleDate: Date
    ): DateDiffWrapper {
        val diffInDays = TimeUnit.DAYS.convert(
            todaysDate.time - articleDate.time,
            TimeUnit.MILLISECONDS)

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

    enum class TimePeriod{
        Day,
        Week,
        Month,
        Year
    }
}
