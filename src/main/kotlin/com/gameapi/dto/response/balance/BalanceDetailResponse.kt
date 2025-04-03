package com.gameapi.dto.response.balance

import java.time.LocalDateTime

data class BalanceDetailResponse(
    val id: Long,
    val userId: Long,
    val balance: Double,
    val currency: String = "USD",
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?,
)
