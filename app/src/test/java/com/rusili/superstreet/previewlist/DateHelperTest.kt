package com.rusili.superstreet.previewlist

import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
import java.util.Date

class DateHelperTest {
    private val todaysDate = Date(1531590471199)
    private val testSubject = DateHelper()

    @Test
    fun `Given today, When getDateDifferenceString() is called, Then return "Today`(){
        // Given
        val articleDate = todaysDate

        // When
        val result = testSubject.getDateDifferenceString(todaysDate, articleDate)

        // Then
        result shouldEqual "Today"
    }

    @Test
    fun `Given 3 months ago, When getDateDifferenceString() is called, Then return "3 months ago`(){
        // Given
        val articleDate =  Date(1521590471199)

        // When
        val result = testSubject.getDateDifferenceString(todaysDate, articleDate)

        // Then
        result shouldEqual "3 months ago"
    }

    @Test
    fun `Test getDateDifference method return days`() {
        // Given:
        val format = SimpleDateFormat("dd/MM/yyyy")
        val articleDate = format.parse("10/7/2018")

        // When:
        val days = testSubject.getDateDifference(todaysDate, articleDate)

        // Then:
        days shouldEqual DateHelper.DateDiffWrapper(4, DateHelper.TimePeriod.Day)
    }

    @Test
    fun `Test getDateDifference method return weeks`() {
        // Given:
        val format = SimpleDateFormat("dd/MM/yyyy")
        val articleDate = format.parse("20/6/2018")

        // When:
        val days = testSubject.getDateDifference(todaysDate, articleDate)

        // Then:
        days shouldEqual DateHelper.DateDiffWrapper(3, DateHelper.TimePeriod.Week)
    }

    @Test
    fun `Test getDateDifference method return months`() {
        // Given:
        val format = SimpleDateFormat("dd/MM/yyyy")
        val articleDate = format.parse("20/3/2018")

        // When:
        val days = testSubject.getDateDifference(todaysDate, articleDate)

        // Then:
        days shouldEqual DateHelper.DateDiffWrapper(3, DateHelper.TimePeriod.Month)
    }

    @Test
    fun `Test getDateDifference method return years`() {
        // Given:
        val format = SimpleDateFormat("dd/MM/yyyy")
        val articleDate = format.parse("20/5/2015")

        // When:
        val days = testSubject.getDateDifference(todaysDate, articleDate)

        // Then:
        days shouldEqual DateHelper.DateDiffWrapper(3, DateHelper.TimePeriod.Year)
    }
}
