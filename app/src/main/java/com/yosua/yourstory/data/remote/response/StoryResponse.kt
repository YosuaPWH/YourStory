package com.yosua.yourstory.data.remote.response

import com.yosua.yourstory.domain.model.Story

/**
 * Created by Yosua on 19/05/2023
 */
data class StoryResponse(
    val status: Int,
    val message: String,
    val data: Story
)
