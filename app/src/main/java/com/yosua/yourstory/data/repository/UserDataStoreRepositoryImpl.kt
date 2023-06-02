package com.yosua.yourstory.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.yosua.yourstory.domain.model.User
import com.yosua.yourstory.domain.repository.UserDataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Yosua on 18/05/2023
 */
class UserDataStoreRepositoryImpl(private val userDataStore: DataStore<Preferences>) :
    UserDataStoreRepository {

    private val name = stringPreferencesKey("name")
    private val email = stringPreferencesKey("email")
    private val id = stringPreferencesKey("id")

    override suspend fun setDataUser(user: User) {
        userDataStore.edit { pref ->
            pref[name] = user.name
            pref[id] = user.id.toString()
            pref[email] = user.email
        }
        Log.d("useruser", user.toString())
    }

    override suspend fun getDataUser(): Flow<User?> {
        return userDataStore.data.map { pref ->
            val name = pref[name]
            val id = pref[id]
            val email = pref[email]

            if (name.isNullOrEmpty() || id.isNullOrEmpty() || email.isNullOrEmpty()) {
                null
            } else {
                User(name = name, email = email, id = id.toInt())
            }
        }
    }

    override suspend fun logout(): Boolean {
        return try {
            userDataStore.edit { pref ->
                pref.clear()
            }
            true
        } catch (e: Exception) {
            false
        }
    }
}