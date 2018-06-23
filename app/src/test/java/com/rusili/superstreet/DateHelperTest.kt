package com.rusili.superstreet

import com.rusili.superstreet.ui.util.DateHelper
import com.rusili.superstreet.ui.util.TimePeriod
import org.junit.Assert
import org.junit.Test
import java.text.SimpleDateFormat

class DateHelperTest {

    @Test
    fun `Test formatToMMDDYYY method`(){
        // Given:
        val format = SimpleDateFormat("dd/MM/yyyy")
        val date = format.parse("21/12/2012")

        // When:
        val formatted = DateHelper.formatToMMMDDYYY(date)

        // Then:
        Assert.assertEquals(formatted, "Dec 21, 2012")
    }

    @Test
    fun `Test getDateDifference method return days`(){
        // Given:
        val format = SimpleDateFormat("dd/MM/yyyy")
        val date = format.parse("10/6/2018")

        // When:
        val days = DateHelper.getDateDifference(date)

        // Then:
        Assert.assertEquals(days, DateHelper.DateDiffWrapper(5, TimePeriod.DAY))
    }

    @Test
    fun `Test getDateDifference method return weeks`(){
        // Given:
        val format = SimpleDateFormat("dd/MM/yyyy")
        val date = format.parse("20/5/2018")

        // When:
        val days = DateHelper.getDateDifference(date)

        // Then:
        Assert.assertEquals(days, DateHelper.DateDiffWrapper(3, TimePeriod.WEEK))
    }

    @Test
    fun `Test getDateDifference method return months`(){
        // Given:
        val format = SimpleDateFormat("dd/MM/yyyy")
        val date = format.parse("20/3/2018")

        // When:
        val days = DateHelper.getDateDifference(date)

        // Then:
        Assert.assertEquals(days, DateHelper.DateDiffWrapper(2, TimePeriod.MONTH))
    }

    @Test
    fun `Test getDateDifference method return years`(){
        // Given:
        val format = SimpleDateFormat("dd/MM/yyyy")
        val date = format.parse("20/5/2015")

        // When:
        val days = DateHelper.getDateDifference(date)

        // Then:
        Assert.assertEquals(days, DateHelper.DateDiffWrapper(3, TimePeriod.YEAR))
    }
}