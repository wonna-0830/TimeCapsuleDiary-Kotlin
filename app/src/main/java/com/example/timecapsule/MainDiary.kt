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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Calendar

class MainDiary : AppCompatActivity() {

    private lateinit var fabMenuLayout: LinearLayout
    private lateinit var fabMain: TextView // 만약 TextView 기반 버튼일 경우
    // private lateinit var fabMain: FloatingActionButton // 진짜 FAB일 경우 이걸로
    private var isPolaroidMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maindiary)

        val container = findViewById<FrameLayout>(R.id.containerView)

        container.removeAllViews()

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
        // 테마 전환 버튼 클릭 이벤트
        val btnChangeTheme = findViewById<Button>(R.id.btnChangeTheme)
        btnChangeTheme.setOnClickListener {
            switchThemeMode()
            fabMenuLayout.visibility = View.GONE
        }

        // 초기 화면 설정 (예: 폴라로이드 모드)
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerView, PolaroidFragment())
            .commit()
    }




    private fun switchThemeMode() {
        val transaction = supportFragmentManager.beginTransaction()
        val newFragment = if (isPolaroidMode) CustomCalendarFragment() else PolaroidFragment()
        transaction.replace(R.id.containerView, newFragment)
        transaction.commit()
        isPolaroidMode = !isPolaroidMode
    }





}
