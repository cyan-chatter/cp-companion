package com.saarthak.cpcompanion.model


import com.google.gson.annotations.SerializedName

data class Meta(
    val limit: String,
    val next: String,
    val offset: String,
    val previous: String,
    @SerializedName("total_count")
    val totalCount: String
)