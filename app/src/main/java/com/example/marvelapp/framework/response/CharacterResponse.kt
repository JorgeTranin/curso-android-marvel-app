package com.example.marvelapp.framework.response

import com.jorge.core.domain.models.Character

data class CharacterResponse(
    val description: String,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val thumbnailRespose: ThumbnailRespose
)
fun  CharacterResponse.toCharacterModel(): Character{
    return Character(
        name = this.name,
        imageUrl = "${this.thumbnailRespose.path}.${this.thumbnailRespose.extension}"
    )
}