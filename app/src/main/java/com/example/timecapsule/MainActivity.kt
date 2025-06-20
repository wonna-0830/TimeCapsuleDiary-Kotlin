package com.example.timecapsule

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.timecapsule.ui.theme.TimeCapsuleTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import java.util.UUID

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 스플래시 전용 레이아웃 사용 (나중에 배경화면 등 넣기)
        setContentView(R.layout.activity_main)

        // 🔐 고유 ID 생성 및 저장
        val userId = getUserId(this)

        // ☁️ Firebase에 user 문서 생성 여부 확인
        val userRef = Firebase.firestore.collection("user").document(userId)
        userRef.get().addOnSuccessListener { document ->
            if (!document.exists()) {
                userRef.set(mapOf("createdAt" to FieldValue.serverTimestamp()))
            }
        }

        // ⏱ 5초 후 메인 다이어리 화면으로 전환
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainDiary::class.java))
            finish()
        }, 5000)
    }
    fun getUserId(context: Context): String {
        val prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        var userId = prefs.getString("userId", null)

        if (userId == null) {
            userId = UUID.randomUUID().toString()
            prefs.edit().putString("userId", userId).apply()
        }

        return userId
    }

}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TimeCapsuleTheme {
        Greeting("Android")
    }
}