package com.randomdroids.moviesinfo.ui

import com.randomdroids.moviesinfo.usecases.GetMovieDataUseCase
import com.randomdroids.moviesinfo.data.repository.MoviesInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class MainActivityModule {
    @Provides
    @ViewModelScoped
    fun getMoviesDataUseCaseProvider(openMovieRepository: MoviesInfoRepository) =
        GetMovieDataUseCase(openMovieRepository)
}