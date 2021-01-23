package com.saarthak.cpcompanion.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.saarthak.cpcompanion.R
import com.saarthak.cpcompanion.model.Contest
import kotlin.coroutines.coroutineContext

open class ContestListAdapter(private val context: Context): RecyclerView.Adapter<ContestListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_contest_details, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contest = differ.currentList[position]

        holder.apply {
            idx_tv.text = (1 + position).toString()
            title_tv.text = contest.title
            url_tv.text = contest.url
            date_tv.text = contest.date
            startTm_tv.text = contest.start
            duration_tv.text = "${contest.duration} minutes"
            platform_tv.text = contest.platform
            downB.setOnClickListener {
                if(details_ll.visibility == View.GONE){
                    downB.background = AppCompatResources.getDrawable(context, R.drawable.ic_arrow_up_24)
                    details_ll.visibility = View.VISIBLE
                }
                else{
                    downB.background = AppCompatResources.getDrawable(context, R.drawable.ic_arrow__down_24)
                    details_ll.visibility = View.GONE
                }
            }
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
        val platform_tv: TextView = v.findViewById(R.id.platform_tv)
        val downB: AppCompatImageButton = v.findViewById(R.id.downB)
        val details_ll: LinearLayout = v.findViewById(R.id.details_ll)
        val date_tv: TextView = v.findViewById(R.id.date_tv)
        val startTm_tv: TextView = v.findViewById(R.id.startTime_tv)
        val duration_tv: TextView = v.findViewById(R.id.duration_tv)
        val url_tv: TextView = v.findViewById(R.id.url_tv)
    }
}