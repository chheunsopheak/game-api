package com.gameapi.dto.request.balance

import org.jetbrains.annotations.NotNull

data class BalanceRequest(
    @NotNull
    val userId: Long,
    @NotNull
    val amount: Double,
    val currency: String = "USD",
)
