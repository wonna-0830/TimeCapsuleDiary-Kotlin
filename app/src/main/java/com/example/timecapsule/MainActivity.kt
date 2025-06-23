package com.example.timecapsule

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
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

        Log.d("CheckFlow", "MainActivity onCreate 시작됨")

        // 스플래시 전용 레이아웃 사용 (나중에 배경화면 등 넣기)
        setContentView(R.layout.activity_main)

        // 🔐 고유 ID 생성 및 저장
        Log.d("CheckFlow", "1. onCreate 시작")
        val userId = getUserId(this)
        Log.d("CheckFlow", "2. userId 생성됨: $userId")

        val userRef = Firebase.firestore.collection("user").document(userId)
        Log.d("CheckFlow", "3. Firestore 문서 참조 생성됨")

        userRef.get()
            .addOnSuccessListener { document ->
                Log.d("CheckFlow", "4. 문서 조회 성공")
                if (!document.exists()) {
                    Log.d("CheckFlow", "5. 문서 없음 → 생성 시도")
                    userRef.set(mapOf("createdAt" to FieldValue.serverTimestamp()))
                        .addOnSuccessListener {
                            Log.d("Firestore", "✅ 유저 문서 생성 성공")
                        }
                        .addOnFailureListener { e ->
                            Log.e("Firestore", "❌ 유저 문서 생성 실패", e)
                        }
                } else {
                    Log.d("CheckFlow", "5. 문서 이미 존재함")
                }
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "❌ 문서 조회 실패", e)
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