package com.example.timecapsule

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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 스플래시 전용 레이아웃 사용 (필요 시 너가 만든 배경화면 등 넣기)
        setContentView(R.layout.activity_main)

        // 5초(5000ms) 후에 MainDiary로 이동
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainDiary::class.java)
            startActivity(intent)
            finish() // MainActivity는 종료
        }, 5000)
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