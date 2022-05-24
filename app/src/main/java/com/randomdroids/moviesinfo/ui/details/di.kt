package com.randomdroids.moviesinfo.ui.details

import com.randomdroids.moviesinfo.data.repository.MoviesInfoRepository
import com.randomdroids.moviesinfo.usecases.GetMovieDetailsDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DetailsModule {
    @Provides
    @ViewModelScoped
    fun getMoviesDetailsDataUseCaseProvider(moviesInfoRepository: MoviesInfoRepository) =
        GetMovieDetailsDataUseCase(moviesInfoRepository)
}