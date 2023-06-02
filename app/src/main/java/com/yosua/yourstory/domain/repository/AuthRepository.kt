package com.yosua.yourstory.domain.repository

import com.yosua.yourstory.data.remote.request.LoginRequest
import com.yosua.yourstory.data.remote.request.RegisterRequest
import com.yosua.yourstory.data.remote.response.LoginResponse
import com.yosua.yourstory.domain.model.User
import com.yosua.yourstory.utils.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by Yosua on 18/05/2023
 */
interface AuthRepository {
    fun login(request: LoginRequest) : Flow<Result<User?>>
    fun register(request: RegisterRequest) : Flow<Result<User?>>
}