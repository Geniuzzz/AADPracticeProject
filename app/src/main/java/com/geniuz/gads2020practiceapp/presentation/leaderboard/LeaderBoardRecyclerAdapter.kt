package com.geniuz.gads2020practiceapp.presentation.leaderboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.geniuz.gads2020practiceapp.R
import com.geniuz.gads2020practiceapp.data.models.LBData
import com.geniuz.gads2020practiceapp.data.models.LeaderBoard
import com.geniuz.gads2020practiceapp.toLBData

class LeaderBoardRecyclerAdapter(val data: List<LBData>) :
    RecyclerView.Adapter<LeaderBoardRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            with(itemView) {
                val nameTv = findViewById<TextView>(R.id.item_lb_name_tv)
                val detailsTv = findViewById<TextView>(R.id.item_lb_details_tv)
                val badgeIv = findViewById<ImageView>(R.id.item_lb_badge_image_view)

                if (data.isEmpty()) return
                val item = data[position]

                Glide
                    .with(itemView)
                    .load(item.badgeUrl)
                    .centerCrop()
                    .into(badgeIv)

                nameTv.text = item.name
                detailsTv.text = item.score
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_leaderboard, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
}