package com.medibank.shop.di

import com.medibank.shop.repository.INewsRepository
import com.medibank.shop.repository.NewsRepositoryImpl
import com.medibank.shop.usecase.INewsUseCase
import com.medibank.shop.usecase.NewsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AstronautsModule {

    @Singleton
    @Binds
    abstract fun bindNewsRepo(
        spaceRepo: NewsRepositoryImpl
    ) : INewsRepository

    @Binds
    abstract fun bindAstronautsUseCase(
        austronautsUseCase: NewsUseCaseImpl
    ): INewsUseCase
}