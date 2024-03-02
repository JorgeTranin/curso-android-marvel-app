package com.jorge.core.data.repository

interface CharacterRemoteDataSource<T> {
    suspend fun fetchCharacter(queris: Map<String, String>): T
}