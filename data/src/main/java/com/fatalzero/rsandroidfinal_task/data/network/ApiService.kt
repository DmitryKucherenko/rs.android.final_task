package com.fatalzero.rsandroidfinal_task.data.network

import com.fatalzero.rsandroidfinal_task.data.network.model.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/joke/ANY")
    suspend fun getResponse(
        @Query(AMOUNT) count: Int,
        @Query(RANGE) range: String
    ): Response

    companion object {
        private const val AMOUNT = "amount"
        private const val SORT = "sort"
        private const val RANGE = "idRange"
        const val MAX_PAGE_SIZE = 5
    }
}
