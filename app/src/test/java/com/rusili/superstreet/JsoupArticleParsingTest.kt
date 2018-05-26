package com.rusili.superstreet

import org.jsoup.Jsoup
import org.junit.Assert
import org.junit.Test

import org.junit.Before
import java.io.File

class JsoupArticleParsingTest {
    private val inputMain = File("/Users/rusi.li/Documents/SuperStreet/app/src/test/resources/json/.html")
    private val doc = Jsoup.parse(inputMain, "UTF-8", null)

    @Before
    fun setup(){}


    @Test
    fun `Test article title parsing`(){

    }

    @Test
    fun `Test article body parsing`(){

    }

    @Test
    fun `Test article author parsing`(){

    }

    @Test
    fun `Test article timestamp parsing`(){

    }

    @Test
    fun `Test article magazine parsing`(){

    }

    @Test
    fun `Test article type parsing`(){

    }

    @Test
    fun `Test article images parsing`(){

    }

    @Test
    fun `Test complete article parsing`(){

    }
}
