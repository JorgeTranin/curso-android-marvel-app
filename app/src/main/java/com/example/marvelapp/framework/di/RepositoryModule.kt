package com.example.marvelapp.framework.di

import com.example.marvelapp.framework.CharacterRepositoryImpl
import com.example.marvelapp.framework.remote.RetrofitCharacterDataSource
import com.example.marvelapp.framework.response.DataWrapperResponse
import com.jorge.core.data.repository.CharacterRemoteDataSource
import com.jorge.core.data.repository.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindCharacterRepository(repositoryImpl: CharacterRepositoryImpl): CharacterRepository

    @Binds
    fun bindRemoteDataSource(dataSource: RetrofitCharacterDataSource
    ): CharacterRemoteDataSource<DataWrapperResponse>


}