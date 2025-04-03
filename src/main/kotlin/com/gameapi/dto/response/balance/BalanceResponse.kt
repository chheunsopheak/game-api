package com.gameapi.dto.response.balance

import com.gameapi.model.balance.Balance
import java.time.LocalDateTime

data class BalanceResponse(
    val id: Long,
    val userId: Long,
    val balance:Double?,
    val currency: String = "USD",
    val createdAt: LocalDateTime,
)
