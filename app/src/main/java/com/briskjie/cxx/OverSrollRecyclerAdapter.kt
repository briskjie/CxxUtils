package com.briskjie.cxx

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OverSrollRecyclerAdapter(context: Context, list: ArrayList<String>?) :
    RecyclerView.Adapter<OverSrollRecyclerAdapter.MyHolder>() {
    private var mList: ArrayList<String>? = list
    private var mContext: Context? = context
    private var itemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view =
            LayoutInflater.from(mContext).inflate(R.layout.item_multi_column_list, parent, false)
        return MyHolder(view);
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.textView.text = mList!![position]
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClickListener(position);
        }
    }

    override fun getItemCount(): Int = mList!!.size

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.text)
    }

    interface ItemClickListener {
        fun onItemClickListener(position: Int)
    }

    fun setOnItemClicklistener(listener : ItemClickListener) {
        itemClickListener = listener
    }
}