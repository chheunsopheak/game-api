package com.gameapi.exception

import com.gameapi.wrapper.ApiResponse
import com.gameapi.wrapper.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    /**
     * Handles Not Found exceptions (404)
     */
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException): ApiResponse<ErrorResponse> {
        logger.error("Not Found Exception: ${ex.message}")
        return ApiResponse.notFound(ex.message.toString())
    }

    /**
     * Handles Bad Request exceptions (400)
     */
    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(ex: BadRequestException): ApiResponse<ErrorResponse> {
        logger.warn("Bad Request: ${ex.message}")
        return ApiResponse.badRequest(ex.message.toString())
    }

    /**
     * Handles Unauthorized exceptions (401)
     */
    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorizedException(ex: UnauthorizedException): ApiResponse<ErrorResponse> {
        logger.warn("Unauthorized Access: ${ex.message}")
        return ApiResponse.error(HttpStatus.UNAUTHORIZED.value(), ex.message.toString())
    }

    /**
     * Handles Forbidden access exceptions (403)
     */
    @ExceptionHandler(ForbiddenException::class)
    fun handleForbiddenException(ex: ForbiddenException): ApiResponse<ErrorResponse> {
        logger.warn("Forbidden Access: ${ex.message}")
        return ApiResponse.error(HttpStatus.FORBIDDEN.value(), ex.message.toString())
    }

    /**
     * Handles Conflict exceptions (409)
     */
    @ExceptionHandler(ConflictException::class)
    fun handleConflictException(ex: ConflictException): ApiResponse<ErrorResponse> {
        logger.warn("Conflict Error: ${ex.message}")
        return ApiResponse.error(HttpStatus.CONFLICT.value(), ex.message.toString())
    }

    /**
     * Handles generic exceptions (500)
     */
    @ExceptionHandler(Exception::class)
    fun handleGeneralException(ex: InternalServerException): ApiResponse<ErrorResponse> {
        logger.error("Unexpected error occurred: ${ex.message}")
        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.message.toString())
    }
}
