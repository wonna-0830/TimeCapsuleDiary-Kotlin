package com.example.timecapsule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class PolaroidFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PolaroidAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recycler_polaroid, container, false) // 기존 레이아웃 재사용!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.polaroidRecyclerView) // recycler_polaroid.xml에 있는 RecyclerView ID
        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        // ✅ 어댑터 연결
        val dummyList = listOf(
            DiaryEntry("sample1.jpg", "😊", "기분 최고!", "2025-06-01", 0.0, 0.0),
            DiaryEntry("sample2.jpg", "😌", "혼자 걷는 산책길", "2025-06-03", 0.0, 0.0)
        )
        recyclerView.adapter = PolaroidAdapter(dummyList)
    }
}
