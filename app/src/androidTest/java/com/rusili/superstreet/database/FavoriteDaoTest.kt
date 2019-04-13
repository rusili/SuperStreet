package com.rusili.superstreet.database

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import com.rusili.superstreet.common.models.Flag
import com.rusili.superstreet.common.models.Footer
import com.rusili.superstreet.common.models.Header
import com.rusili.superstreet.common.models.Magazine
import com.rusili.superstreet.common.models.Type
import com.rusili.superstreet.common.models.footer.Author
import com.rusili.superstreet.common.models.header.HeaderImage
import com.rusili.superstreet.common.models.header.Title
import com.rusili.superstreet.database.favorites.FavoriteEntity
import com.rusili.superstreet.database.favorites.FavoriteModelMapper
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import com.rusili.superstreet.previewlist.domain.CardSize
import io.reactivex.functions.Predicate
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Date
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class FavoriteDaoTest {
    @Rule @JvmField val instantTaskExecutorRule = InstantTaskExecutorRule()

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
                "http://image.superstreetonline.com/f/170287891+w660+h440+q80+re0+cr1+ar0+st0/2018-lexus-lc-500-hks-exhaust.jpg"
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

    @Before
    fun setup() {
        testSubject = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @Test
    fun getUsersWhenNoUserInserted() {
        testSubject.favoriteDao()
            .getAllFavorites()
            .test()
            .assertNoValues()
            .assertNoErrors()
    }

    @Test
    fun Test() {
        // Given
        val entity = FavoriteModelMapper().from(articlePreviewModel)
        testSubject.favoriteDao()
            .addFavorite(entity)
            .blockingAwait()

        // When
        testSubject.favoriteDao()
            .getAllFavorites()
            .test()
            .assertValue(entity)
    }

    @After
    fun teardown() {
        testSubject.close()
    }
}
