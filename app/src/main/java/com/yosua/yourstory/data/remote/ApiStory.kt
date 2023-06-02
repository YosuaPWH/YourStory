package com.yosua.yourstory.data.remote

import com.yosua.yourstory.data.remote.response.StoryResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

/**
 * Created by Yosua on 19/05/2023
 */
interface ApiStory {

    @Multipart
    @POST("story-create/{id}")
    suspend fun createStory(
        @Path("id") id: Int,
        @Part image: MultipartBody.Part? = null,
        @Part("description") description: RequestBody? = null
    ) : Response<StoryResponse>
}