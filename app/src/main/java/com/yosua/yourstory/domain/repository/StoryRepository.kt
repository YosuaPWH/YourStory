package com.yosua.yourstory.domain.repository

import com.yosua.yourstory.data.remote.response.AllStoriesResponse
import com.yosua.yourstory.data.remote.response.StoryResponse
import com.yosua.yourstory.domain.model.Story
import com.yosua.yourstory.utils.Result
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

/**
 * Created by Yosua on 18/05/2023
 */
interface StoryRepository {

    fun createStory(fileImage: MultipartBody.Part?, description: RequestBody) : Flow<Result<String>>

    fun getAllStories() : Flow<Result<List<Story>>>

}