package com.randomdroids.moviesinfo.di

import com.randomdroids.moviesinfo.data.repository.MoviesInfoRepository
import com.randomdroids.moviesinfo.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun moviesInfoRepositoryProvider(
        remoteDataSource: RemoteDataSource
    ) = MoviesInfoRepository(remoteDataSource)
}