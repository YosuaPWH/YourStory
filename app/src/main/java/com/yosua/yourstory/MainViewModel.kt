package com.yosua.yourstory

import androidx.lifecycle.ViewModel
import com.yosua.yourstory.domain.repository.UserDataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Yosua on 01/06/2023
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val dataStore: UserDataStoreRepository) :
    ViewModel() {

    suspend fun getUser() = dataStore.getDataUser()
}