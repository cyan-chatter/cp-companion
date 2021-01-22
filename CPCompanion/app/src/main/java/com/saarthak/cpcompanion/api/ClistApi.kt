package com.saarthak.cpcompanion.api

import com.saarthak.cpcompanion.model.ContestResponse
import com.saarthak.cpcompanion.util.Constants.Companion.API_KEY
import com.saarthak.cpcompanion.util.Constants.Companion.USERNAME
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ClistApi {

    @GET("/api/v1/contest/")
    suspend fun getContestDetails(
        @Query("pgNo") pgNo: Int = 1,
        @Query("username") userName: String = USERNAME,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<ContestResponse>

}