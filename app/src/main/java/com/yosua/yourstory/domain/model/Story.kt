package com.yosua.yourstory.domain.model

/**
 * Created by Yosua on 19/05/2023
 */
data class Story(
    val id: Int,
    val id_user: Int,
    val name: String,
    val image: String? = null,
    val description: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null
)
