package com.gameapi.controller.user

import com.gameapi.config.ApiConfig
import com.gameapi.dto.request.user.UserLoginRequest
import com.gameapi.dto.request.user.UserRegisterRequest
import com.gameapi.dto.response.user.UserDetailResponse
import com.gameapi.dto.response.user.UserResponse
import com.gameapi.service.UserService
import com.gameapi.wrapper.ApiResponse
import com.gameapi.wrapper.PaginatedResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(ApiConfig.BASE_URL_ADMIN)
class UserController(private val userService: UserService) {

    @PostMapping("register")
    fun register(@RequestBody request: UserRegisterRequest): ApiResponse<String> {
        return userService.registerUser(request)
    }

    @PostMapping("login")
    fun login(@RequestBody request: UserLoginRequest): ApiResponse<String> {
        return userService.loginUser(request)
    }

    @GetMapping("user/{userId}")
    fun getUserById(@PathVariable userId: Long): ApiResponse<UserDetailResponse> {
        return userService.getUserById(userId)
    }

    @GetMapping("users")
    fun getAllUsers(
        @RequestParam(defaultValue = "1") pageNumber: Int, @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) searchString: String?
    ): PaginatedResponse<UserResponse> {
        val result = userService.getAllUsers(pageNumber, pageSize, searchString)
        return result
    }

    @DeleteMapping("user/{userId}")
    fun deleteUser(@PathVariable userId: Long): ApiResponse<String> {
        return userService.deleteUser(userId)
    }

}