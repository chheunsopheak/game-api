package com.gameapi.dto.response.post

data class PostResponse(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)
