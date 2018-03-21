package com.example.msigl62.coworkandroiduset.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.holder.CoWorkReViewHolder
import com.example.msigl62.coworkandroiduset.model.DataReView

class AdapterReView (private var coWork: List<DataReView>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setItem(items: List<DataReView>?) {
        items?.let {coWork = it}
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return CoWorkReViewHolder(view)
    }

    override fun getItemCount():  Int = coWork.size

    override fun getItemViewType(position: Int): Int = R.layout.list_item_re_view

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) = (holder as CoWorkReViewHolder).onBind(coWork[position])
}