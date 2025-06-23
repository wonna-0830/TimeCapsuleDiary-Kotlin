package com.example.timecapsule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import java.time.LocalDate

class PolaroidFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PolaroidAdapter
    private lateinit var dateText: TextView

    private var currentYear = LocalDate.now().year
    private var currentMonth = LocalDate.now().monthValue

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recycler_polaroid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.polaroidRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        dateText = view.findViewById(R.id.dateText)
        val btnPrev = view.findViewById<TextView>(R.id.prevMonth)
        val btnNext = view.findViewById<TextView>(R.id.nextMonth)

        btnPrev.setOnClickListener {
            if (currentMonth == 1) {
                currentMonth = 12
                currentYear -= 1
            } else {
                currentMonth -= 1
            }
            loadDummyData()
        }

        btnNext.setOnClickListener {
            if (currentMonth == 12) {
                currentMonth = 1
                currentYear += 1
            } else {
                currentMonth += 1
            }
            loadDummyData()
        }

        loadDummyData()
    }

    private fun loadDummyData() {
        dateText.text = "${currentYear}년 ${currentMonth}월"

        // 👉 월에 따라 더미 리스트 다르게 만들기
        val dummyList = when (currentMonth) {
            6 -> listOf(
                DiaryEntry("sample1.jpg", "😊", "기분 최고!", "2025-06-01", 0.0, 0.0),
                DiaryEntry("sample2.jpg", "😌", "혼자 걷는 산책길", "2025-06-03", 0.0, 0.0)
            )
            5 -> listOf(
                DiaryEntry("sample3.jpg", "🌸", "벚꽃구경", "2025-05-05", 0.0, 0.0),
                DiaryEntry("sample4.jpg", "🎵", "음악회 다녀옴", "2025-05-21", 0.0, 0.0)
            )
            else -> listOf(
                DiaryEntry("default.jpg", "📷", "아직 기록이 없어요!", "$currentYear-$currentMonth-01", 0.0, 0.0)
            )
        }

        adapter = PolaroidAdapter(dummyList)
        recyclerView.adapter = adapter
    }
}
