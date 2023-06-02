package com.yosua.yourstory.di

import com.yosua.yourstory.BuildConfig
import com.yosua.yourstory.data.remote.ApiAuth
import com.yosua.yourstory.data.repository.AuthRepositoryImpl
import com.yosua.yourstory.domain.repository.AuthRepository
import com.yosua.yourstory.domain.repository.UserDataStoreRepository
import com.yosua.yourstory.utils.Constants.AUTH_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Yosua on 18/05/2023
 */
@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor() = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    } else {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }

    @Provides
    @Singleton
    fun provideApiAuthInstance(loggingInterceptor: HttpLoggingInterceptor) : ApiAuth =
        Retrofit.Builder()
            .baseUrl(AUTH_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .build())
            .build()
            .create(ApiAuth::class.java)

    @Provides
    @Singleton
    fun provideAuthRepository(api: ApiAuth, dataStore: UserDataStoreRepository) : AuthRepository {
        return AuthRepositoryImpl(api, dataStore)
    }
}