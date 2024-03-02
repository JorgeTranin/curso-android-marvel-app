package com.example.marvelapp.framework.response

data class DataContainerResponse(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val characterResponses: List<CharacterResponse>,
    val total: Int
)