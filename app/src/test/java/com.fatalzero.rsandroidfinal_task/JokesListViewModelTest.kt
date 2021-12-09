package com.fatalzero.rsandroidfinal_task
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fatalzero.rsandroidfinal_task.data.repository.JokesListRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.junit.rules.TestRule
import org.junit.Rule
import retrofit2.Retrofit
import org.mockito.Mock
import com.fatalzero.rsandroidfinal_task.data.network.ApiService


@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi

class JokesListViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var retrofit: Retrofit

    @Mock
    private lateinit var apiHelper: ApiService

    @Mock
    lateinit var repository: JokesListRepositoryImpl
}
