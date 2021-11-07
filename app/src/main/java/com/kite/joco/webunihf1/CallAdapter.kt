package com.kite.joco.webunihf1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CallAdapter : RecyclerView.Adapter<CallAdapter.ViewHolder> {

    private val items = mutableListOf<CallLog>()
    private val context: Context

    constructor(context: Context, itemsList: List<CallLog>) : super() {
        this.context = context
        items.addAll(itemsList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.calllogitem, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvPhoneNum.text = items[position].phoneNum
        holder.tvTimeStamp.text = items[position].timeStamp

        holder.btnCall.setOnClickListener {
            (context as MainActivity).callNumberWithPermissionCheck(
                items[position].phoneNum
            )
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPhoneNum: TextView = itemView.findViewById(R.id.tvPhoneNum)
        val tvTimeStamp: TextView = itemView.findViewById(R.id.tvTimeStamp)
        val btnCall: Button = itemView.findViewById(R.id.btnCall)
    }
}

