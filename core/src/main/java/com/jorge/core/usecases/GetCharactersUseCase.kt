package com.jorge.core.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jorge.core.data.repository.CharacterRepository
import com.jorge.core.domain.models.Character
import com.jorge.core.usecases.base.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCharactersUseCase {
    operator fun invoke(params: GetCharactersParams): Flow<PagingData<Character>>

    data class GetCharactersParams(val query: String, val pagingConfig: PagingConfig)
}

class GetCharactersUseCaseImpl @Inject constructor(
    private val charactersRepository: CharacterRepository
) : PagingUseCase<GetCharactersUseCase.GetCharactersParams, Character>(),
    GetCharactersUseCase {

    override fun createFlowObservable(params: GetCharactersUseCase.GetCharactersParams): Flow<PagingData<Character>> {
        val pagingSource = charactersRepository.getCharacter(params.query)
        return Pager(config = params.pagingConfig) {
            pagingSource
        }.flow
    }
}