package com.gameapi.dto.response.user

import com.gameapi.dto.response.balance.BalanceResponse

data class UserDetailResponse(
    val id: Long,
    val username: String,
    val email: String,
    val displayName: String?,
    val accountCreated: String,
    val lastLogin: String?,
    val isActive: Boolean,
    val isBanned: Boolean,
    val banReason: String?,
    val role: String,
    val experiencePoints: Int,
    val level: Int,
    val avatarUrl: String?,
    val bio: String?,
    val country: String?,
    val preferredLanguage: String?,
    val dateOfBirth: String?,
    val balance: BalanceResponse? = null
)