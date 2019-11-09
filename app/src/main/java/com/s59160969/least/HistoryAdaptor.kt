package com.s59160969.least

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.s59160969.least.database.LeastScore
import com.s59160969.least.databinding.ListItemScoreBinding

class HistoryAdaptor: ListAdapter<LeastScore, HistoryAdaptor.ViewHolder>(LeastScoreDiffCallback())  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        updateIndexScore()
    }
    class ViewHolder private constructor(val binding: ListItemScoreBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: LeastScore) {
//           binding.date.text = convertLongToDateString(item.date)
//           binding.score.text = "${item.leastScore} score"
//           binding.ranking.text = (position + 1).toString()
//           when(position+1){
//               1 -> binding.image.setImageResource(R.drawable.medal)
//               else -> binding.image.visibility = View.GONE
//           }
           //val  arr = listOf(item,position)

            binding.least = item
            binding.executePendingBindings()
       }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ListItemScoreBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class LeastScoreDiffCallback : DiffUtil.ItemCallback<LeastScore>() {
    override fun areItemsTheSame(oldItem: LeastScore, newItem: LeastScore): Boolean {
        return oldItem.leastId == newItem.leastId
    }

    override fun areContentsTheSame(oldItem: LeastScore, newItem: LeastScore): Boolean {
        return  oldItem == newItem
    }
}