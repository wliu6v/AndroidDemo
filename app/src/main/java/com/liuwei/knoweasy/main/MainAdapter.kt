package com.liuwei.knoweasy.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.liuwei.knoweasy.R
import kotlinx.android.synthetic.main.card_main.view.*

/**
 * Created by liuwei on 2017/7/18.
 */
class MainAdapter(val provider: MainListProvider) : RecyclerView.Adapter<ViewHolder>() {

    var listener: IRecyclerViewEventListener? = null

    override fun getItemCount() = provider.getCount()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_main, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = provider.getTitle(position)
        holder.itemView.setOnClickListener {
            listener?.onItemClick(holder.itemView, position)
        }
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textView = itemView.main_card_title_tv as TextView
}

interface IRecyclerViewEventListener {
    fun onItemClick(v: View, pos: Int)
}
