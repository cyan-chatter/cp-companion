package com.saarthak.cpcompanion.model


import com.google.gson.annotations.SerializedName

data class Contest(
    val duration: String,
    val end: String,
    val event: String,
    val href: String,
    val id: Int,
    val resource: String,
    val start: String
)