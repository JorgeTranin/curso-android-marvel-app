package com.example.marvelapp.framework.response

data class CharacterResponse(
    val description: String,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val thumbnailRespose: ThumbnailRespose
)