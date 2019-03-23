package com.rusili.superstreet.common.body

import com.nhaarman.mockito_kotlin.mock
import com.rusili.superstreet.common.models.Flag
import com.rusili.superstreet.common.models.Footer
import com.rusili.superstreet.common.models.Header
import org.junit.jupiter.api.Test

class ArticleHeaderTest {

    @Test
    fun `Given valid ArticleHeader, When getViewType() is called, Then return 0`(){
        // Given
        val header = mock<Header>()
        val footer = mock<Footer>()
        val flag = mock<Flag>()

        // When
        val result = ArticleHeader(0, header, footer, flag)

        // Then
        result.getViewType() shouldEqual 0
    }
}
