package com.gameapi.dto.response.user

data class UserResponse(
    val id: Long,
    val username: String,
    val email: String,
    val name: String?,
    val avatarUrl: String?,
    val level: Int,
    val accountCreated: String,
    val lastLogin: String?,
    val isActive: Boolean,
    val balance: Double? = 0.0
)
