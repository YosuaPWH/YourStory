package com.yosua.yourstory.data.remote.response

import com.yosua.yourstory.domain.model.Story

/**
 * Created by Yosua on 03/06/2023
 */
data class AllStoriesResponse(
    val status: Int,
    val message: String,
    val data: List<Story>
)
