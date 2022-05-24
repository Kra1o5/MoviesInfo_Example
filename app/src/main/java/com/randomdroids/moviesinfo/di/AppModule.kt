package com.randomdroids.moviesinfo.di

import com.randomdroids.moviesinfo.data.server.MoviesInfoServerDataSource
import com.randomdroids.moviesinfo.data.server.MoviesInfoServerService
import com.randomdroids.moviesinfo.data.server.ServerConstants
import com.randomdroids.moviesinfo.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun okHttpProvider() =
        HttpLoggingInterceptor().run {
            level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
                val builder = chain.request().newBuilder()
                return@Interceptor chain.proceed(builder.build())
            }).build()
        }

    @Singleton
    @Provides
    fun retrofitProvider(okHttpClient: OkHttpClient): MoviesInfoServerService = Retrofit.Builder()
        .baseUrl(ServerConstants.URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .run {
            create(MoviesInfoServerService::class.java)
        }

    @Provides
    fun moviesinfoDataSourceProvider(moviesInfoServerService: MoviesInfoServerService): RemoteDataSource = MoviesInfoServerDataSource(moviesInfoServerService)
}