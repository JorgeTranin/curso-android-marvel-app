package com.jorge.core.data.repository

import androidx.paging.PagingSource
import com.jorge.core.domain.models.Character

interface CharacterRepository {

    fun getCharacter(query: String): PagingSource<Int, Character>

}