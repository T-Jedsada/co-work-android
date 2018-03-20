package com.example.msigl62.coworkandroiduset.holder

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.msigl62.coworkandroiduset.model.DataCoWorkNearby
import com.example.msigl62.coworkandroiduset.ui.detail.DetailCoWorkNearbyActivity
import kotlinx.android.synthetic.main.list_item_co_working_nearby.view.*

class CoWorkNearbyHolder (view: View) : RecyclerView.ViewHolder(view) {
    fun onBind(coWork: DataCoWorkNearby) {
        itemView.apply {
            textCoWorkName.text=coWork.name
            rating.rating=coWork.averageRating.toFloat()
            imageCoWorkNearby.setOnClickListener {
                itemView.context.startActivity(Intent(
                        itemView.context, DetailCoWorkNearbyActivity::class.java
                ).putExtra("key",coWork.id))
            }
        }
    }
}