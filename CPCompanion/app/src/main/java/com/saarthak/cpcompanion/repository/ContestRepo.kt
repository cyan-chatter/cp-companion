package com.saarthak.cpcompanion.repository

import com.saarthak.cpcompanion.api.RetrofitInstance

class ContestRepo {
    suspend fun getContestDetails(pgNo: Int) = RetrofitInstance.api.getContestDetails(pgNo)
}