package com.saarthak.cpcompanion.model

import com.google.gson.annotations.SerializedName

data class ContestResponse(
    @SerializedName("data")
    val contests: MutableList<Contest>,
    @SerializedName("total_size")
    val totalCount: Int
)