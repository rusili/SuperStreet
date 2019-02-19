package com.rusili.superstreet.article.di

import com.rusili.superstreet.article.data.ArticleRepositoryImpl
import com.rusili.superstreet.article.domain.ArticleRepository
import com.rusili.superstreet.article.domain.ArticleUsecaseImpl
import com.rusili.superstreet.article.ui.ArticleUsecase
import com.rusili.superstreet.article.ui.ArticleViewModelFactory
import com.rusili.superstreet.jsoup.di.JsoupModule
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [JsoupModule::class])
abstract class ArticleModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        protected fun provideArticleViewModelFactory(usecase: ArticleUsecase) =
            ArticleViewModelFactory(usecase)
    }

    @Binds
    abstract protected fun provideArticleUsecase(usecaseImpl: ArticleUsecaseImpl): ArticleUsecase

    @Binds
    abstract protected fun provideArticleRepository(repositoryImpl: ArticleRepositoryImpl): ArticleRepository
}
