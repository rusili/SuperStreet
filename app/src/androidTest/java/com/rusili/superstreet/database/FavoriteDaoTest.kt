package com.rusili.superstreet.database

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeEach
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rusili.superstreet.common.models.Flag
import com.rusili.superstreet.common.models.Footer
import com.rusili.superstreet.common.models.Header
import com.rusili.superstreet.common.models.Magazine
import com.rusili.superstreet.common.models.Type
import com.rusili.superstreet.common.models.footer.Author
import com.rusili.superstreet.common.models.header.HeaderImage
import com.rusili.superstreet.common.models.header.Title
import com.rusili.superstreet.database.favorites.FavoriteEntity
import com.rusili.superstreet.database.favorites.FavoriteEntityMapper
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import com.rusili.superstreet.previewlist.domain.CardSize
import io.reactivex.functions.Predicate
import org.junit.Rule
import org.junit.Test
import java.util.Date

class FavoriteDaoTest {
    @Rule val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var testSubject: AppDatabase

    private val articlePreviewModel = ArticlePreviewModel(
        Flag(
            Magazine.SuperStreet,
            Type.Feature
        ),
        Header(
            Title(
                "Title",
                "TitleHref"
            ),
            HeaderImage(
                "HeaderImageTitle",
                "HeaderImageUrl"
            ),
            "Desc"
        ),
        Footer(
            Author(
                "Auth",
                "AuthorHref"
            ),
            Date()
        ),
        CardSize.Large
    )

    @BeforeEach
    fun setup() {
        testSubject = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @Test
    fun `Given`() {
        // Given
        val entity = FavoriteEntityMapper.fromPreviewArticleModel(articlePreviewModel)
        testSubject.favoriteDao().addFavorite(entity)

        // When
        val result = testSubject.favoriteDao().getAllFavorites().test()

        // Then
        result.assertValue(object : Predicate<FavoriteEntity> {
            override fun test(t: FavoriteEntity): Boolean =
                t.header.title.value.equals(articlePreviewModel.header.title.value)
        })
    }

    @AfterAll
    fun teardown() {
        testSubject.close()
    }
}
