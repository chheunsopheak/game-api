package com.gameapi.service.impl

import com.gameapi.dto.request.balance.UpdateBalanceRequest
import com.gameapi.dto.response.balance.BalanceDetailResponse
import com.gameapi.dto.response.balance.BalanceResponse
import com.gameapi.repository.balance.BalanceRepository
import com.gameapi.service.BalanceService
import com.gameapi.wrapper.ApiResponse
import com.gameapi.wrapper.network.RetrofitManager
import org.springframework.stereotype.Service

@Service
class BalanceServiceImpl(
    private val balanceRepository: BalanceRepository
) : BalanceService {

    override fun getBalance(id: Long): ApiResponse<BalanceDetailResponse> {

        val balance = balanceRepository.findById(id).orElse(null) ?: return ApiResponse.notFound("Balance not found")
        val response = BalanceDetailResponse(
            id = balance.id,
            userId = balance.user.id,
            balance = balance.balance,
            currency = balance.currency,
            createdAt = balance.createdAt,
            updatedAt = balance.updatedAt
        )
        return ApiResponse.success(response, "Balance retrieved successfully")
    }

    override fun getBalanceByUserId(userId: Long): ApiResponse<BalanceResponse> {
        val balance = balanceRepository.findByUserId(userId) ?: return ApiResponse.notFound("Balance not found")

        val response = BalanceResponse(
            id = balance.id,
            userId = balance.user.id,
            balance = balance.balance,
            currency = balance.currency,
            createdAt = balance.createdAt
        )
        return ApiResponse.success(response, "Balance retrieved successfully")
    }

    override fun updateBalance(request: UpdateBalanceRequest): ApiResponse<Long> {
        val balance = balanceRepository.findByUserId(request.userId) ?: return ApiResponse.notFound("Balance not found")

        balance.balance = request.balance
        balance.currency = request.currency
        val updatedBalance = balanceRepository.save(balance)
        return ApiResponse.success(updatedBalance.id, "Balance updated successfully")
    }
}