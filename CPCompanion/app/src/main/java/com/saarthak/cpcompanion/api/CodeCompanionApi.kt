package com.saarthak.cpcompanion.api

import com.saarthak.cpcompanion.model.ContestResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CodeCompanionApi {

    @GET("/contests")
    suspend fun getContestDetails(
        @Query("page") pgNo: Int = 1,
    ): Response<ContestResponse>

}