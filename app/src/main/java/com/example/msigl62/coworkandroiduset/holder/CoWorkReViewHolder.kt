package com.example.msigl62.coworkandroiduset.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.msigl62.coworkandroiduset.model.DataReView
import kotlinx.android.synthetic.main.list_item_re_view.view.*

class CoWorkReViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    fun onBind(coWork:DataReView ) {
        itemView.apply {
           comment.text=coWork.comment
        }
    }
}