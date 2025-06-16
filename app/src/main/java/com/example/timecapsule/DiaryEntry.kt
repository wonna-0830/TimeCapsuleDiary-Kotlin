package com.example.timecapsule

data class DiaryEntry(
    val imageUrl: String,
    val emotion: String,
    val summary: String,
    val date: String,
    val latitude: Double,
    val longitude: Double
)

