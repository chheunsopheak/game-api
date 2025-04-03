package com.gameapi.wrapper

import org.springframework.http.HttpStatus

data class ApiResponse<T>(
    val data: T?,
    val message: String,
    val statusCode: Int,
    val success: Boolean
) {
    companion object {
        // Success variations
        fun <T> success(data: T?, message: String = "Operation successful"): ApiResponse<T> {
            return ApiResponse(data, message, HttpStatus.OK.value(), true)
        }

        // Error variations
        fun <T> error(statusCode: Int, message: String): ApiResponse<T> {
            return ApiResponse(null, message, statusCode, false)
        }

        fun <T> failed(statusCode: Int, message: String): ApiResponse<T> {
            return ApiResponse(null, message, statusCode, false)
        }

        fun <T> badRequest(message: String): ApiResponse<T> {
            return ApiResponse(null, message, HttpStatus.BAD_REQUEST.value(), false)
        }

        fun <T> notFound(message: String = "Resource not found"): ApiResponse<T> {
            return ApiResponse(null, message, HttpStatus.NOT_FOUND.value(), false)
        }

        fun <T> serverError(message: String = "Internal server error"): ApiResponse<T> {
            return ApiResponse(null, message, HttpStatus.INTERNAL_SERVER_ERROR.value(), false)
        }
    }
}