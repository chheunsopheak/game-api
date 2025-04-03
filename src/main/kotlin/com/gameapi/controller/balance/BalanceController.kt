package com.gameapi.controller.balance

import com.gameapi.config.ApiConfig
import com.gameapi.dto.request.balance.UpdateBalanceRequest
import com.gameapi.dto.response.balance.BalanceDetailResponse
import com.gameapi.dto.response.balance.BalanceResponse
import com.gameapi.service.BalanceService
import com.gameapi.wrapper.ApiResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(ApiConfig.BASE_URL_ADMIN)
class BalanceController(private val balanceService: BalanceService) {

    @RequestMapping("balance/{id}")
    fun getBalance(@PathVariable id: Long): ApiResponse<BalanceDetailResponse> {
        return balanceService.getBalance(id)
    }

    @RequestMapping("balance/user/{userId}")
    fun getBalanceByUserId(@PathVariable userId: Long): ApiResponse<BalanceResponse> {
        return balanceService.getBalanceByUserId(userId)
    }

    @PutMapping("balance")
    fun updateBalance(@RequestBody request: UpdateBalanceRequest): ApiResponse<Long> {
        return balanceService.updateBalance(request)
    }
}