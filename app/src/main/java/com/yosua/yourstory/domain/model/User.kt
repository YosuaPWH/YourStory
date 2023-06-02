package com.yosua.yourstory.domain.model

/**
 * Created by Yosua on 18/05/2023
 */
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val image: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null
)
