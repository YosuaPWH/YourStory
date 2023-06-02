package com.yosua.yourstory.data.remote

import com.yosua.yourstory.data.remote.request.LoginRequest
import com.yosua.yourstory.data.remote.request.RegisterRequest
import com.yosua.yourstory.data.remote.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Yosua on 18/05/2023
 */
interface ApiAuth {

    @POST("login")
    suspend fun login(
        @Body login: LoginRequest
    ) : Response<LoginResponse>

    @POST("register")
    suspend fun register(
        @Body register: RegisterRequest
    ) : Response<LoginResponse>
}