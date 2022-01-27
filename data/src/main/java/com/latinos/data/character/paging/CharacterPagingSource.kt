package com.latinos.data.character.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.latinos.data.character.mapper.toCharacterModelList
import com.latinos.domain.characters.model.CharacterModel
import com.latinos.services.remote.services.charaters.CharactersService
import retrofit2.HttpException
import java.io.IOException

class CharacterPagingSource(
    private val charactersService: CharactersService,
) : PagingSource<Int, CharacterModel>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        return try {

            val nextPageNumber = params.key ?: 1
            val response = charactersService.getPagedCharacters(
                offset = nextPageNumber.toString(),
                limit = 50,
            ).body() ?: run { return LoadResult.Error(Throwable()) }

            val nextPage =
                if (response.data.offset < response.data.total) response.data.offset + 50 else null

            LoadResult.Page(
                data = response.data.results.toCharacterModelList(),
                prevKey = null, // Only paging forward.
                nextKey = nextPage
            )
        } catch (e: IOException) {
            // IOException for network failures.
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(e)
        }
    }
}