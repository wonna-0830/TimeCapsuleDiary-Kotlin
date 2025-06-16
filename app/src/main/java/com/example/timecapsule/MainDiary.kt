package com.example.timecapsule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainDiary : AppCompatActivity() {

    private lateinit var fabMenuLayout: LinearLayout
    private lateinit var fabMain: TextView // 만약 TextView 기반 버튼일 경우
    // private lateinit var fabMain: FloatingActionButton // 진짜 FAB일 경우 이걸로

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maindiary)

        val container = findViewById<FrameLayout>(R.id.containerView)

// 먼저 기존 뷰 지우고
        container.removeAllViews()

// RecyclerView 레이아웃 inflate 해서 추가
        val inflater = LayoutInflater.from(this)
        val recyclerView = inflater.inflate(R.layout.recycler_polaroid, container, false) as RecyclerView

// LayoutManager 설정 (크기 다양하게 할 거면 Staggered 추천)
        recyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

// 데이터 연결
        val dummyList = listOf(
            DiaryEntry("sample1.jpg", "😊", "기분 최고!", "2025-06-01", 0.0, 0.0),
            DiaryEntry("sample2.jpg", "😌", "혼자 걷는 산책길", "2025-06-03", 0.0, 0.0)
        )
        val adapter = PolaroidAdapter(dummyList)
        recyclerView.adapter = adapter

// containerView 안에 RecyclerView 넣기
        container.addView(recyclerView)



        fabMenuLayout = findViewById(R.id.fabMenuLayout)
        fabMain = findViewById(R.id.fab_custom)

        // FAB 클릭 → 메뉴 토글
        fabMain.setOnClickListener {
            if (fabMenuLayout.visibility == View.GONE) {
                fabMenuLayout.visibility = View.VISIBLE
            } else {
                fabMenuLayout.visibility = View.GONE
            }
        }

        // 메뉴 안 버튼들 기능 연결



    }
}
