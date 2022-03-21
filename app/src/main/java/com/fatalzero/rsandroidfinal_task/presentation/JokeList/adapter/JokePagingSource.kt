package com.fatalzero.rsandroidfinal_task.presentation.JokeList.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fatalzero.rsandroidfinal_task.data.network.ApiService.Companion.MAX_PAGE_SIZE
import com.fatalzero.rsandroidfinal_task.domain.usecase.IJokesListUseCase
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class JokePagingSource (private val jokesListUseCase: IJokesListUseCase) :
    PagingSource<Int, Joke>() {
    override fun getRefreshKey(state: PagingState<Int, Joke>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Joke> {
        val page = params.key ?: 1
        val range = "${page * MAX_PAGE_SIZE - (MAX_PAGE_SIZE - 1)}-${page * MAX_PAGE_SIZE}"
        return try {
            val response = jokesListUseCase.execute(MAX_PAGE_SIZE, range)

            LoadResult.Page(
                response, prevKey = if (page == 1) null else page + 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}
