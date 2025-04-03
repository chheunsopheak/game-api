package com.gameapi.repository.balance

import com.gameapi.model.balance.Balance
import org.springframework.data.jpa.repository.JpaRepository

interface BalanceRepository : JpaRepository<Balance, Long> {
    fun findByUserId(userId: Long): Balance?
}