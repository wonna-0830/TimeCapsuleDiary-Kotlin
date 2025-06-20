package com.example.timecapsule

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainDiary : AppCompatActivity() {

    private lateinit var fabMenuLayout: LinearLayout
    private lateinit var fabMain: TextView // 만약 TextView 기반 버튼일 경우
    // private lateinit var fabMain: FloatingActionButton // 진짜 FAB일 경우 이걸로

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maindiary)

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
