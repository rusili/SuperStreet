package com.rusili.superstreet

import com.rusili.superstreet.ui.util.DateHelper
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
    fun `Test convertToNumOfDaysAgo method`(){
        // Given:
        val format = SimpleDateFormat("dd/MM/yyyy")
        val date = format.parse("10/6/2018")

        // When:
        val days = DateHelper.convertToNumOfDaysAgo(date)

        // Then:
        Assert.assertEquals(days, 5)
    }
}