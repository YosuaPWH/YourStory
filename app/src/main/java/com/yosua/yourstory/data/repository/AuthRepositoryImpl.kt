package com.yosua.yourstory.data.repository

import android.util.Log
import com.google.gson.Gson
import com.yosua.yourstory.data.remote.ApiAuth
import com.yosua.yourstory.data.remote.request.LoginRequest
import com.yosua.yourstory.data.remote.request.RegisterRequest
import com.yosua.yourstory.data.remote.response.ErrorResponse
import com.yosua.yourstory.data.remote.response.LoginResponse
import com.yosua.yourstory.domain.model.User
import com.yosua.yourstory.domain.repository.AuthRepository
import com.yosua.yourstory.domain.repository.UserDataStoreRepository
import com.yosua.yourstory.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Yosua on 18/05/2023
 */
class AuthRepositoryImpl(private val api: ApiAuth, private val dataStore: UserDataStoreRepository) : AuthRepository {

    override fun login(request: LoginRequest): Flow<Result<User?>> = flow {
        emit(Result.Loading)
        try {
            api.login(request).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val user = body?.data!!
                    Log.d("AuthRepo-Login", "Success: $body")
                    dataStore.setDataUser(user)
                    emit(Result.Success(user))
                } else {
                    val error = Gson().fromJson(it.errorBody()!!.charStream(), ErrorResponse::class.java)
                    emit(Result.Error(error.message))
                }
            }
        } catch (e: Exception) {
            Log.d("AuthRepo-Login", "Excep: $e")
            emit(Result.Error(e.message ?: "Terjadi Kesalahan"))
        }
    }

    override fun register(request: RegisterRequest): Flow<Result<User?>> = flow {
        emit(Result.Loading)
        try {
            api.register(request).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val user = body?.data
                    Log.d("AuthRepo-Register", "Success: $body")
                    emit(Result.Success(user))
                } else {
                    val error = Gson().fromJson(it.errorBody()!!.charStream(), ErrorResponse::class.java)
                    emit(Result.Error(error.message))
                }
            }
        } catch (e: Exception) {
            Log.d("AuthRepo-Register", "Excep: $e")
            emit(Result.Error(e.message ?: "Terjadi Kesalahan"))
        }
    }

    override fun getName(id: Int): String {
        TODO("Not yet implemented")
    }
}