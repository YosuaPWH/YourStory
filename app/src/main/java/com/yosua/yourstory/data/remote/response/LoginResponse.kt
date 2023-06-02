package com.yosua.yourstory.data.remote.response

import com.yosua.yourstory.domain.model.User

/**
 * Created by Yosua on 18/05/2023
 */
data class LoginResponse(
    val status: Int,
    val message: String,
    val data: User?
)
