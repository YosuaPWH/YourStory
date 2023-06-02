package com.yosua.yourstory.di

import com.yosua.yourstory.data.remote.ApiStory
import com.yosua.yourstory.data.repository.StoryRepositoryImpl
import com.yosua.yourstory.domain.repository.StoryRepository
import com.yosua.yourstory.domain.repository.UserDataStoreRepository
import com.yosua.yourstory.utils.Constants
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
 * Created by Yosua on 19/05/2023
 */
@Module
@InstallIn(SingletonComponent::class)
object StoryModule {

    @Provides
    @Singleton
    fun provideApiStoryInstance(loggingInterceptor: HttpLoggingInterceptor) : ApiStory =
        Retrofit.Builder()
            .baseUrl(Constants.STORY_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build())
            .build()
            .create(ApiStory::class.java)

    @Provides
    @Singleton
    fun provideStoryRepository(api: ApiStory, dataStore: UserDataStoreRepository) : StoryRepository {
        return StoryRepositoryImpl(api, dataStore)
    }

}