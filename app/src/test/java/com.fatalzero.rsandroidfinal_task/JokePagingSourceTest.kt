package com.fatalzero.rsandroidfinal_task

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.fatalzero.rsandroidfinal_task.data.mapper.JokeMapper
import com.fatalzero.rsandroidfinal_task.data.network.model.JokeNet
import com.fatalzero.rsandroidfinal_task.domain.usecase.IJokesListUseCase
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.presentation.JokeList.adapter.JokePagingSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class JokePagingSourceTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var jokesListUseCase: IJokesListUseCase

    private lateinit var jokePagingSource: JokePagingSource

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        jokePagingSource = JokePagingSource(jokesListUseCase)
    }

    @Test
    fun `reviews paging source load`() {
        testCoroutineRule.runBlockingTest {
            val jokeMapper = JokeMapper()
            val jokes = mutableListOf<Joke>().apply {
                addAll(
                    JokeMapper.mapJsonContainerToListJoke(
                        mutableListOf<JokeNet>().apply {
                            repeat(5) {
                                add(JokeGenerator.getFakeJoke(it))
                            }
                        })
                )
            }
            given(jokesListUseCase.execute(anyInt(), anyString())).willReturn(jokes)
            assertEquals(
                PagingSource.LoadResult.Page(jokes, null, 2),
                jokePagingSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = 1, loadSize = 1, placeholdersEnabled = false
                    )
                )
            )
        }
    }
}
