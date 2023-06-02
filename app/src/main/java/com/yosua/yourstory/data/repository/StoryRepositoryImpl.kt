package com.yosua.yourstory.data.repository

import android.util.Log
import com.google.gson.Gson
import com.yosua.yourstory.data.remote.ApiStory
import com.yosua.yourstory.data.remote.response.ErrorResponse
import com.yosua.yourstory.data.remote.response.StoryResponse
import com.yosua.yourstory.domain.model.Story
import com.yosua.yourstory.domain.repository.StoryRepository
import com.yosua.yourstory.domain.repository.UserDataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import com.yosua.yourstory.utils.Result

/**
 * Created by Yosua on 19/05/2023
 */
class StoryRepositoryImpl(
    private val api: ApiStory,
    private val dataStore: UserDataStoreRepository
) : StoryRepository {

    override fun createStory(
        fileImage: MultipartBody.Part,
        description: RequestBody
    ): Flow<Result<String>> = flow {
        emit(Result.Loading)
        try {
            dataStore.getDataUser().collect {
                if (it != null) {
                    api.createStory(it.id, fileImage, description).let { response ->
                        if (response.isSuccessful) {
                            val body = response.body()
                            val data = body?.data

                            Log.d("Create Story", "Success: $data")
                            emit(Result.Success(body!!.message))
                        } else {
                            val error = Gson().fromJson(
                                response.errorBody()!!.charStream(),
                                ErrorResponse::class.java
                            )
                            emit(Result.Error(error.message))
                        }
                    }
                } else {
                    emit(Result.Error("Akun tidak ditemukan"))
                }
            }
        } catch (e: Exception) {
            Log.d("StoryRepo-Upload", "Excep: $e")
            emit(Result.Error(e.message ?: "Terjadi Kesalahan"))
        }

    }
}