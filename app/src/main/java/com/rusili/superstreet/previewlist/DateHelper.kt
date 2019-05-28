package com.rusili.superstreet.previewlist

import androidx.annotation.VisibleForTesting
import java.util.Date
import java.util.concurrent.TimeUnit

private const val STRING_TODAY = "Today"
private const val STRING_YESTERDAY = "Yesterday"

private const val DAYS_PER_WEEK = 7
private const val DAYS_PER_MONTH = 30
private const val DAYS_PER_YEAR = 365

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
            diffInDays < DAYS_PER_WEEK -> DateDiffWrapper(diffInDays, TimePeriod.Day)
            diffInDays < DAYS_PER_MONTH -> DateDiffWrapper(diffInDays / DAYS_PER_WEEK, TimePeriod.Week)
            diffInDays < DAYS_PER_YEAR -> DateDiffWrapper(diffInDays / DAYS_PER_MONTH, TimePeriod.Month)
            else -> DateDiffWrapper(diffInDays / DAYS_PER_YEAR, TimePeriod.Year)
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
