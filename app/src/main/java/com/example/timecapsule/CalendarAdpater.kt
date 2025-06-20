package com.example.timecapsule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CalendarAdapter(private val calendarList: List<CalendarCell>) :
    RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    inner class CalendarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dayNumber: TextView = itemView.findViewById(R.id.dayNumber)
        val dayImage: ImageView = itemView.findViewById(R.id.dayImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_day, parent, false)
        return CalendarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val item = calendarList[position]

        if (item.day != null) {
            holder.dayNumber.text = item.day.toString()
            if (!item.imageUrl.isNullOrEmpty()) {
                holder.dayImage.visibility = View.VISIBLE
                Glide.with(holder.itemView.context)
                    .load(item.imageUrl)
                    .into(holder.dayImage)
            } else {
                holder.dayImage.visibility = View.GONE
            }
        } else {
            holder.dayNumber.text = ""
            holder.dayImage.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = calendarList.size
}
