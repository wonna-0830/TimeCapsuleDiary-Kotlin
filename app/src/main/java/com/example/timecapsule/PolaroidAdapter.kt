package com.example.timecapsule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PolaroidAdapter(private val itemList: List<DiaryEntry>) :
    RecyclerView.Adapter<PolaroidAdapter.PolaroidViewHolder>() {

    inner class PolaroidViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val emotionText: TextView = itemView.findViewById(R.id.emotionText)
        val summaryText: TextView = itemView.findViewById(R.id.summaryText)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PolaroidViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_polaroid, parent, false)
        return PolaroidViewHolder(view)
    }

    override fun onBindViewHolder(holder: PolaroidViewHolder, position: Int) {
        val item = itemList[position]

        // 1. 이미지 불러오기 (Glide 권장)
        Glide.with(holder.itemView.context)
            .load(item.imageUrl)
            .placeholder(R.drawable.ic_launcher_background) // 대체 이미지
            .into(holder.imageView)

        // 2. 감정 이모지
        holder.emotionText.text = item.emotion

        // 3. 내용 요약 (한 줄만 표시되게 XML에서 처리함)
        holder.summaryText.text = item.summary

        // 4. 회전각 랜덤 (-8도 ~ +8도)
        val rotation = (-5..5).random()
        holder.cardView.rotation = rotation.toFloat()

        // 5. 크기 랜덤 (너무 다르면 레이아웃 무너짐 → 살짝만 조절)
        val layoutParams = holder.cardView.layoutParams
        layoutParams.height = (400..440).random()
        holder.cardView.layoutParams = layoutParams

    }

    override fun getItemCount(): Int = itemList.size
}
