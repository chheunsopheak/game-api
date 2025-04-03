package com.gameapi.model.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.gameapi.model.balance.Balance
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,

    @Column(unique = true, nullable = false) val username: String,

    @Column(nullable = false) val password: String,

    @Column(unique = true, nullable = false) val email: String,

    @Column(name = "display_name") val name: String?,

    @Column(name = "account_created", nullable = false) val accountCreated: LocalDateTime = LocalDateTime.now(),

    @Column(name = "last_login") val lastLogin: LocalDateTime?,

    @Column(name = "is_active", nullable = false) val isActive: Boolean,

    @Column(name = "is_banned", nullable = false) val isBanned: Boolean,

    @Column(name = "ban_reason") val banReason: String?,

    @Column(nullable = false) val role: String = "USER",

    @Column(name = "experience_points", nullable = false) val experiencePoints: Int = 0,

    @Column(nullable = false) val level: Int = 1,

    @Column(name = "avatar_url") val avatarUrl: String?,

    @Column(nullable = true) val bio: String?,

    @Column(nullable = true) val country: String?,

    @Column(name = "preferred_language", nullable = true) val preferredLanguage: String?,

    @Column(name = "date_of_birth", nullable = true) val dateOfBirth: String?,

    @Column(name = "reset_token") val resetToken: String?,

    @Column(name = "reset_token_expiry") val resetTokenExpiry: String?,

    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JsonIgnore
    val balance: Balance? = null
)
