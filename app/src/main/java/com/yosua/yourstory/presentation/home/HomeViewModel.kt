package com.yosua.yourstory.presentation.home

import androidx.lifecycle.ViewModel
import com.yosua.yourstory.domain.repository.StoryRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repo: StoryRepository) : ViewModel() {


}