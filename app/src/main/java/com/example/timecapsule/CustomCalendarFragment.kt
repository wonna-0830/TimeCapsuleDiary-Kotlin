package com.example.timecapsule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Calendar

class CustomCalendarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_custom_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.calendarRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 7) // 7일 기준

        // 예시 데이터: 일/월/년 기준으로 날짜 칸 만들어서 보여줄 리스트
        val diaryMap = mapOf(
            "2025-06-01" to "https://example.com/image1.jpg",
            "2025-06-04" to "https://example.com/image2.jpg"
        )

        val calendarList = generateCalendarCells(2025, 6, diaryMap)
        recyclerView.adapter = CalendarAdapter(calendarList)
    }

    fun generateCalendarCells(year: Int, month: Int, diaryMap: Map<String, String>): List<CalendarCell> {
        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, 1)

        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) // 1 = 일요일
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        val cellList = mutableListOf<CalendarCell>()

        // 앞쪽 빈칸 추가
        repeat(firstDayOfWeek - 1) {
            cellList.add(CalendarCell(null, null))
        }

        for (day in 1..daysInMonth) {
            val dateKey = String.format("%04d-%02d-%02d", year, month, day)
            val imageUrl = diaryMap[dateKey] // 있으면 사진 썸네일 URL
            cellList.add(CalendarCell(day, imageUrl))
        }

        return cellList
    }
}
