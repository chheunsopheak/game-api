package com.gameapi.service

import com.gameapi.dto.request.balance.BalanceRequest
import com.gameapi.dto.request.balance.UpdateBalanceRequest
import com.gameapi.dto.response.balance.BalanceDetailResponse
import com.gameapi.dto.response.balance.BalanceResponse
import com.gameapi.wrapper.ApiResponse

interface BalanceService {
    fun getBalance(id: Long): ApiResponse<BalanceDetailResponse>
    fun getBalanceByUserId(userId: Long): ApiResponse<BalanceResponse>
    fun updateBalance(request: UpdateBalanceRequest): ApiResponse<Long>
}