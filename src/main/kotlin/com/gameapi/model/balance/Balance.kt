package com.gameapi.model.balance

import com.fasterxml.jackson.annotation.JsonIgnore
import com.gameapi.model.user.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "balances")
data class Balance(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,

    @Column(name = "balance", nullable = false) var balance: Double = 0.0,

    @Column(name = "currency", nullable = false) var currency: String = "USD",

    @Column(name = "created_at", nullable = false) val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false) val updatedAt: LocalDateTime? = LocalDateTime.now(),

    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id", nullable = false) @JsonIgnore val user: User,
)
