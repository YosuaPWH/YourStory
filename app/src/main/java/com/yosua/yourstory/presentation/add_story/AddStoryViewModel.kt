package com.yosua.yourstory.presentation.add_story

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yosua.yourstory.domain.repository.StoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

/**
 * Created by Yosua on 31/05/2023
 */
@HiltViewModel
class AddStoryViewModel @Inject constructor(private val repo: StoryRepository) : ViewModel() {

    fun createStory(image: MultipartBody.Part?, description: RequestBody) =
        repo.createStory(image, description).asLiveData()
}