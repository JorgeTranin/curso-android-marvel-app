package com.jorge.core.data.response

data class CharacterResponse(
    val description: String,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val thumbnailRespose: ThumbnailRespose
)