package com.saarthak.cpcompanion.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.saarthak.cpcompanion.R
import com.saarthak.cpcompanion.model.Contest

open class ContestListAdapter(): RecyclerView.Adapter<ContestListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_contest_details, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contest = differ.currentList[position]

        holder.apply {
            idx_tv.text = position.toString()
            title_tv.text = contest.title
            url_tv.text = contest.url
            date_tv.text = contest.date
            startTm_tv.text = contest.start
            duration_tv.text = contest.duration
        }
    }

    override fun getItemCount() = differ.currentList.size

    private val differCallback = object: DiffUtil.ItemCallback<Contest>(){
        override fun areItemsTheSame(oldItem: Contest, newItem: Contest): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Contest, newItem: Contest): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val idx_tv: TextView = v.findViewById(R.id.idx_tv)
        val title_tv: TextView = v.findViewById(R.id.contestTitle_tv)
        val date_tv: TextView = v.findViewById(R.id.date_tv)
        val startTm_tv: TextView = v.findViewById(R.id.startTime_tv)
        val duration_tv: TextView = v.findViewById(R.id.duration_tv)
        val url_tv: TextView = v.findViewById(R.id.url_tv)
    }
}