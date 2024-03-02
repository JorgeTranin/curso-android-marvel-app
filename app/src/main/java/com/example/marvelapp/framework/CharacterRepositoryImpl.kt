package com.example.marvelapp.framework

import androidx.paging.PagingSource
import com.example.marvelapp.framework.pagin.CharacteresPaginSource
import com.example.marvelapp.framework.response.DataWrapperResponse
import com.jorge.core.data.repository.CharacterRemoteDataSource
import com.jorge.core.data.repository.CharacterRepository
import com.jorge.core.domain.models.Character
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource<DataWrapperResponse>
): CharacterRepository {
    override fun getCharacter(query: String): PagingSource<Int, Character> {
        return CharacteresPaginSource(remoteDataSource, query)
    }
}