package com.yosua.yourstory.presentation.account

import androidx.lifecycle.ViewModel
import com.yosua.yourstory.domain.repository.UserDataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val repo: UserDataStoreRepository) : ViewModel() {

    suspend fun logout() = repo.logout()

}