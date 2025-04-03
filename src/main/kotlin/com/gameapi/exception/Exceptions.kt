package com.gameapi.exception

open class Exception(message: String) : RuntimeException(message)

class NotFoundException(message: String) : Exception(message)
class BadRequestException(message: String) : Exception(message)
class UnauthorizedException(message: String) : Exception(message)
class ForbiddenException(message: String) : Exception(message)
class ConflictException(message: String) : Exception(message)
class InternalServerException(message: String) : Exception(message)
