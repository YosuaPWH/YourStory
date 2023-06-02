package com.yosua.yourstory.domain.repository

import com.yosua.yourstory.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Created by Yosua on 18/05/2023
 */
interface UserDataStoreRepository {

    suspend fun setDataUser(user: User)
    suspend fun getDataUser() : Flow<User?>
    suspend fun logout() : Boolean
}