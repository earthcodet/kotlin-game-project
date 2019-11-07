package com.s59160969.least

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.s59160969.least.database.LeastScore

class HistoryAdaptor:RecyclerView.Adapter<HistoryAdaptor.ViewHolder>()  {
    var data = listOf<LeastScore>()
        set(value){
            field = value
            notifyDataSetChanged()
        }
    class ViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        val score:TextView = itemView.findViewById(R.id.score)
        val date:TextView = itemView.findViewById(R.id.date)
        val image:ImageView = itemView.findViewById(R.id.image)
        val ranking:TextView = itemView.findViewById(R.id.ranking)
       // val layout:View = itemView.findViewById(R.id.containerId)
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_item_score, parent, false)
                return ViewHolder(view)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount() = data.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, position)

    }

    @SuppressLint("ResourceAsColor")
    fun ViewHolder.bind(item: LeastScore, position: Int) {
        val res = itemView.context.resources
        date.text = convertLongToDateString(item.date)
        score.text = "${item.leastScore} score"
        ranking.text = (position + 1).toString()
        when(position+1){
            1 -> image.setImageResource(R.drawable.medal)
                    //layout.setBackgroundColor(R.color.rankingNo1)}
//            2 -> layout.setBackgroundColor(R.color.rankingNo2)
//            3 -> layout.setBackgroundColor(R.color.rankingNo3)
            else -> image.visibility = View.GONE
        }
//        if (position + 1 == 1) {
//            image.setImageResource(R.drawable.medal)
//            layout.setBackgroundColor(Color.parseColor("#e3b10b"))
//        } else {
//            image.visibility = View.GONE
//        }
//        layout
    }



}