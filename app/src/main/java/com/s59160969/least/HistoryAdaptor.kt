package com.s59160969.least

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.s59160969.least.database.LeastScore
import com.s59160969.least.databinding.ListItemScoreBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private val ITEM_VIEW_TYPE_HEADER = 0
private val ITEM_VIEW_TYPE_ITEM = 1
class HistoryAdaptor: ListAdapter<LeastScore, HistoryAdaptor.ViewHolder>(LeastScoreDiffCallback())  {
    private val adapterScope = CoroutineScope(Dispatchers.Default)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return when (viewType) {
//            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
//            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
//            else -> throw ClassCastException("Unknown viewType ${viewType}")
//        }
        return ViewHolder.from(parent)
    }
//    fun addHeaderAndSubmitList(list: List<LeastScore>?) {
//        adapterScope.launch {
//            val items = when (list) {
//                null -> listOf(DataItem.Header)
//                else -> listOf(DataItem.Header) + list.map { DataItem.ScoreItem(it) }
//            }
//            withContext(Dispatchers.Main) {
//                submitList(items)
//            }
//        }
//    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        when (holder) {
//            is ViewHolder -> {
//                val leastItem = getItem(position) as DataItem.ScoreItem
//                holder.bind(leastItem.LeastScore)
//            }
//        }
        val item = getItem(position)
        holder.bind(item)
        updateIndexScore()
    }
//    override fun getItemViewType(position: Int): Int {
//        return when (getItem(position)) {
//            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
//            is DataItem.ScoreItem -> ITEM_VIEW_TYPE_ITEM
//        }
//    }
//    class TextViewHolder(view: View): RecyclerView.ViewHolder(view) {
//        companion object {
//            fun from(parent: ViewGroup): TextViewHolder {
//                val layoutInflater = LayoutInflater.from(parent.context)
//                val view = layoutInflater.inflate(R.layout.header, parent, false)
//                return TextViewHolder(view)
//            }
//        }
//    }
    class ViewHolder private constructor(val binding: ListItemScoreBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: LeastScore) {
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
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: LeastScore, newItem: LeastScore): Boolean {
        return  oldItem == newItem
    }
}

//sealed class DataItem {
//    abstract val id: Long
//    data class ScoreItem(val LeastScore: LeastScore): DataItem(){
//        override val id = LeastScore.leastId
//    }
//    object Header: DataItem(){
//        override val id = Long.MIN_VALUE
//    }
//}