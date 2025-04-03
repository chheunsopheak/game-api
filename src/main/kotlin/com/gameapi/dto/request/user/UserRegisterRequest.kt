package com.gameapi.dto.request.user

import com.gameapi.model.balance.Balance

data class UserRegisterRequest(
    val password: String,
    val email: String,
    val name: String?,
    val dateOfBirth: String?,
    val country: String?,
    val preferredLanguage: String?,
    val avatarUrl: String?,
    val balance: Double,
)
