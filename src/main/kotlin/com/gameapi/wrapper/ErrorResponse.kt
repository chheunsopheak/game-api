package com.gameapi.wrapper

data class ErrorResponse(
    val message: String,
    val statusCode: Int,
    val success: Boolean

)
