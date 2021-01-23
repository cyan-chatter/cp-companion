package com.saarthak.cpcompanion.model

data class Contest(
    val date: String,
    val duration: Int,
    val page: Int,
    val phase: String,
    val platform: String,
    val start: String,
    val title: String,
    val url: String
)