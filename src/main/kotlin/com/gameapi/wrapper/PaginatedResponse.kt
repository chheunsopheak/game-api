package com.gameapi.wrapper

import org.springframework.http.HttpStatus

data class PaginatedResponse<T>(
    val data: List<T>,
    val currentPage: Int,
    val pageSize: Int,
    val totalItems: Int,
    val totalPages: Int,
    val hasPreviousPage: Boolean,
    val hasNextPage: Boolean,
    val statusCode: Int,
    val message: String,
    val succeeded: Boolean
) {
    companion object {
        fun <T> success(
            data: List<T>,
            currentPage: Int,
            pageSize: Int,
            totalItems: Int,
            message: String = "Data retrieved successfully"
        ): PaginatedResponse<T> {
            val totalPages = if (pageSize > 0) ((totalItems + pageSize - 1) / pageSize) else 1
            return PaginatedResponse(
                data = data,
                currentPage = currentPage,
                pageSize = pageSize,
                totalItems = totalItems,
                totalPages = totalPages,
                hasPreviousPage = currentPage > 1,
                hasNextPage = currentPage < totalPages,
                statusCode = HttpStatus.OK.value(),
                message = message,
                succeeded = true
            )
        }

    }
}