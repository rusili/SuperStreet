package com.rusili.superstreet.ui

import com.rusili.superstreet.previewlist.DateHelper
import com.rusili.superstreet.previewlist.TimePeriod
import org.junit.Assert
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class DateHelperTest {
    private val todaysDate = Date(1531590471199)
    private val testSubject = DateHelper()

    @Test
    fun `Test formatToMMDDYYY method`() {
        // Given:
        val format = SimpleDateFormat("dd/MM/yyyy")
        val date = format.parse("21/12/2012")

        // When:
        val formatted = testSubject.formatToMMMDDYYY(date)

        // Then:
        Assert.assertEquals(formatted, "Dec 21, 2012")
    }

    @Test
    fun `Test getDateDifference method return days`() {
        // Given:
        val format = SimpleDateFormat("dd/MM/yyyy")
        val articleDate = format.parse("10/7/2018")

        // When:
        val days = testSubject.getDateDifference(todaysDate, articleDate)

        // Then:
        Assert.assertEquals(days, DateHelper.DateDiffWrapper(4, TimePeriod.Day))
    }

    @Test
    fun `Test getDateDifference method return weeks`() {
        // Given:
        val format = SimpleDateFormat("dd/MM/yyyy")
        val articleDate = format.parse("20/6/2018")

        // When:
        val days = testSubject.getDateDifference(todaysDate, articleDate)

        // Then:
        Assert.assertEquals(days, DateHelper.DateDiffWrapper(3, TimePeriod.Week))
    }

    @Test
    fun `Test getDateDifference method return months`() {
        // Given:
        val format = SimpleDateFormat("dd/MM/yyyy")
        val articleDate = format.parse("20/3/2018")

        // When:
        val days = testSubject.getDateDifference(todaysDate, articleDate)

        // Then:
        Assert.assertEquals(days, DateHelper.DateDiffWrapper(3, TimePeriod.Month))
    }

    @Test
    fun `Test getDateDifference method return years`() {
        // Given:
        val format = SimpleDateFormat("dd/MM/yyyy")
        val articleDate = format.parse("20/5/2015")

        // When:
        val days = testSubject.getDateDifference(todaysDate, articleDate)

        // Then:
        Assert.assertEquals(days, DateHelper.DateDiffWrapper(3, TimePeriod.Year))
    }
}