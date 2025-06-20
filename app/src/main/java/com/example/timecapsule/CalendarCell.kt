package com.example.timecapsule

data class CalendarCell(
    val day: Int?, // null이면 빈칸
    val imageUrl: String? // 해당 날짜에 올린 사진 (없으면 null)

)
