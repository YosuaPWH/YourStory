package com.yosua.yourstory.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yosua.yourstory.data.remote.request.LoginRequest
import com.yosua.yourstory.data.remote.request.RegisterRequest
import com.yosua.yourstory.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Yosua on 18/05/2023
 */
@HiltViewModel
class AuthViewModel @Inject constructor(private val repo: AuthRepository) : ViewModel() {

    fun login(request: LoginRequest) = repo.login(request).asLiveData()

    fun register(request: RegisterRequest) = repo.register(request).asLiveData()
}