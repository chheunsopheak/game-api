package com.gameapi.service

import com.gameapi.dto.request.user.UserLoginRequest
import com.gameapi.dto.request.user.UserRegisterRequest
import com.gameapi.dto.response.user.UserDetailResponse
import com.gameapi.dto.response.user.UserResponse
import com.gameapi.wrapper.ApiResponse
import com.gameapi.wrapper.PaginatedResponse

interface UserService {
    fun registerUser(request: UserRegisterRequest): ApiResponse<String>
    fun loginUser(request: UserLoginRequest): ApiResponse<String>
    fun deleteUser(userId: Long): ApiResponse<String>
    fun getUserById(userId: Long): ApiResponse<UserDetailResponse>
    fun getAllUsers(pageNumber: Int, pageSize: Int, searchString: String?): PaginatedResponse<UserResponse>
}