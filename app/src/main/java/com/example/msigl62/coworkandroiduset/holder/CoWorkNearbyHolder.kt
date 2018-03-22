package com.example.msigl62.coworkandroiduset.holder

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.msigl62.coworkandroiduset.extension.load
import com.example.msigl62.coworkandroiduset.model.DataCoWorkNearby
import com.example.msigl62.coworkandroiduset.ui.detail.DetailNearbyActivity
import kotlinx.android.synthetic.main.list_item_co_working_nearby.view.*

class CoWorkNearbyHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun onBind(coWork: DataCoWorkNearby) {
        itemView.apply {
            textCoWorkName.text = coWork.name
            rating.rating = coWork.averageRating.toFloat()
            imageCoWorkNearby.load(coWork.poster)
            imageCoWorkNearby.setOnClickListener {
                itemView.context.startActivity(Intent(
                        itemView.context, DetailNearbyActivity::class.java
                ).putExtra("key", coWork.id)
                        .putExtra("latitude", coWork.latitude)
                        .putExtra("longitude", coWork.longitude)
                        .putExtra("poster", coWork.poster)
                        .putExtra("im", coWork.gallery))
            }
        } }
}