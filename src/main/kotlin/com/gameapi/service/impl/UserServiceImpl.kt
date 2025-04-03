package com.gameapi.service.impl

import com.gameapi.dto.request.user.UserLoginRequest
import com.gameapi.dto.request.user.UserRegisterRequest
import com.gameapi.dto.response.balance.BalanceResponse
import com.gameapi.dto.response.user.UserDetailResponse
import com.gameapi.dto.response.user.UserResponse
import com.gameapi.model.balance.Balance
import com.gameapi.model.user.User
import com.gameapi.repository.balance.BalanceRepository
import com.gameapi.repository.user.UserRepository
import com.gameapi.service.UserService
import com.gameapi.wrapper.ApiResponse
import com.gameapi.wrapper.PaginatedResponse
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val balanceRepository: BalanceRepository,
) : UserService {
    override fun registerUser(request: UserRegisterRequest): ApiResponse<String> {
        if (request.email.isBlank() || request.password.isBlank()) {
            return ApiResponse.error(HttpStatus.BAD_REQUEST.value(), "Username and password cannot be empty")
        }
        if (userRepository.findByUsername(request.email) != null) {
            return ApiResponse.error(HttpStatus.CONFLICT.value(), "Username already exists")
        } else if (userRepository.findByEmail(request.email) != null) {
            return ApiResponse.error(HttpStatus.CONFLICT.value(), "Email already exists")
        }
        val user = User(
            email = request.email,
            password = request.password,
            name = request.name,
            username = request.email,
            dateOfBirth = request.dateOfBirth,
            role = "USER",
            isActive = true,
            country = request.country,
            preferredLanguage = request.preferredLanguage,
            accountCreated = LocalDateTime.now(),
            lastLogin = LocalDateTime.now(),
            isBanned = false,
            banReason = null,
            experiencePoints = 0,
            level = 1,
            avatarUrl = request.avatarUrl,
            bio = null,
            resetToken = null,
            resetTokenExpiry = null
        )
        val savedUser = userRepository.save(user)

        val balance = Balance(
            user = savedUser,
            balance = request.balance,
            currency = "USD",
        )
        balanceRepository.save(balance)

        return ApiResponse.success(savedUser.id.toString(), "User registered successfully")
    }

    override fun loginUser(request: UserLoginRequest): ApiResponse<String> {
        val user = userRepository.findByUsername(request.username) ?: return ApiResponse.error(
            HttpStatus.UNAUTHORIZED.value(), "Invalid username or password"
        )
        if (user.password != request.password) {
            return ApiResponse.error(HttpStatus.UNAUTHORIZED.value(), "Invalid username or password")
        }
        if (!user.isActive) {
            return ApiResponse.error(HttpStatus.FORBIDDEN.value(), "User is banned")
        }
        return ApiResponse.success("User logged in successfully")
    }

    override fun deleteUser(userId: Long): ApiResponse<String> {
        val user = userRepository.findById(userId).orElse(null) ?: return ApiResponse.error(
            HttpStatus.NOT_FOUND.value(), "User not found"
        )
        if (user.isBanned) {
            return ApiResponse.error(HttpStatus.FORBIDDEN.value(), "User is banned")
        }
        if (user.isActive) {
            return ApiResponse.error(HttpStatus.FORBIDDEN.value(), "User is active")
        }
        userRepository.delete(user)
        return ApiResponse.success("User deleted successfully")
    }

    override fun getUserById(userId: Long): ApiResponse<UserDetailResponse> {
        val user = userRepository.findById(userId).orElse(null) ?: return ApiResponse.error(
            HttpStatus.NOT_FOUND.value(), "User not found"
        )
        val userDetail = UserDetailResponse(
            id = user.id,
            username = user.username,
            email = user.email,
            displayName = user.name,
            accountCreated = user.accountCreated.toString(),
            lastLogin = user.lastLogin?.toString(),
            isActive = user.isActive,
            isBanned = user.isBanned,
            banReason = user.banReason,
            role = user.role,
            experiencePoints = user.experiencePoints,
            level = user.level,
            avatarUrl = user.avatarUrl,
            bio = user.bio,
            country = user.country,
            preferredLanguage = user.preferredLanguage,
            dateOfBirth = user.dateOfBirth,
            balance = balanceRepository.findByUserId(user.id)
                ?.let {
                    BalanceResponse(
                        id = it.id,
                        userId = it.user.id,
                        balance = it.balance,
                        currency = it.currency,
                        createdAt = it.createdAt
                    )
                }
        )
        return ApiResponse.success(userDetail, "User retrieved successfully")
    }

    override fun getAllUsers(
        pageNumber: Int, pageSize: Int, searchString: String?
    ): PaginatedResponse<UserResponse> {

        val pageable = PageRequest.of(pageNumber - 1, pageSize)
        val userPage = userRepository.findAll(pageable)
        val userResponses = userPage.content.filter { user ->
            searchString == null || user.username.contains(searchString, ignoreCase = true) || user.email.contains(
                searchString,
                ignoreCase = true
            ) || user.name?.contains(searchString, ignoreCase = true)!! || user.country?.contains(
                searchString,
                ignoreCase = true
            )!! || user.preferredLanguage?.contains(searchString, ignoreCase = true)!!
        }.sortedByDescending { it.id }.map { user ->
            UserResponse(
                id = user.id,
                username = user.username,
                email = user.email,
                name = user.name,
                accountCreated = user.accountCreated.toString(),
                lastLogin = user.lastLogin?.toString(),
                isActive = user.isActive,
                avatarUrl = user.avatarUrl,
                level = user.level,
                balance = balanceRepository.findByUserId(user.id)?.balance,
            )
        }
        val data = PaginatedResponse.success(
            data = userResponses,
            currentPage = pageNumber,
            pageSize = pageSize,
            totalItems = userPage.totalElements.toInt()
        )
        return data;
    }
}