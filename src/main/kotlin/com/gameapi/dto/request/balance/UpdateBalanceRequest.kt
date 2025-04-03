package com.gameapi.dto.request.balance

import com.gameapi.model.balance.Balance

data class UpdateBalanceRequest(
    val id: Long,
    val userId: Long,
    var balance: Double,
    var currency: String = "USD",
)
