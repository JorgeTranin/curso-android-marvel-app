package com.example.marvelapp.framework.remote

import com.example.marvelapp.framework.MarvelApi
import com.example.marvelapp.framework.response.DataWrapperResponse
import com.jorge.core.data.repository.CharacterRemoteDataSource
import javax.inject.Inject

class RetrofitCharacterDataSource @Inject constructor(
    private val marvelApi: MarvelApi
): CharacterRemoteDataSource<DataWrapperResponse> {
    override suspend fun fetchCharacter(queris: Map<String, String>): DataWrapperResponse {
        
        return marvelApi.getCharacters(queris)
    }
}