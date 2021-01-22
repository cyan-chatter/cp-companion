package com.saarthak.cpcompanion.repository

import com.saarthak.cpcompanion.api.RetrofitInstance

class ContestRepo {
    suspend fun getContestDetails(limit: Int) = RetrofitInstance.api.getContestDetails(limit)
}