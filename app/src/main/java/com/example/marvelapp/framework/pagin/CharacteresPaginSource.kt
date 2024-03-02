package com.example.marvelapp.framework.pagin

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvelapp.framework.response.DataWrapperResponse
import com.example.marvelapp.framework.response.toCharacterModel
import com.jorge.core.data.repository.CharacterRemoteDataSource
import com.jorge.core.domain.models.Character

class CharacteresPaginSource(
    private val remoteDataSource: CharacterRemoteDataSource<DataWrapperResponse>,
    private val query: String
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
       return try {
            val offset = params.key ?: 0

            val queries = hashMapOf(
                "offset" to offset.toString()
            )
            if (query.isNotEmpty()) {
                queries["nameStartWith"] = query
            }

            val response = remoteDataSource.fetchCharacter(queries)
            val responseOffSet = response.data.offset
            val limit = response.data.total

            LoadResult.Page(
                data = response.data.results.map { it.toCharacterModel() },
                prevKey = null,
                nextKey = if (responseOffSet < limit) {
                    responseOffSet + LIMIT
                } else null
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?. prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)

        }
    }

    companion object {
        private const val LIMIT = 20
    }
}