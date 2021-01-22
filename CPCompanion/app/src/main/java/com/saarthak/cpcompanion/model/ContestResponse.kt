package com.saarthak.cpcompanion.model

data class ContestResponse(
    val contests: MutableList<Contest>,
    val totalCount: Int
)