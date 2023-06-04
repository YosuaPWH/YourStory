package com.yosua.yourstory.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yosua.yourstory.domain.repository.StoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: StoryRepository) : ViewModel() {

    fun getAllStories() = repo.getAllStories().asLiveData()

}