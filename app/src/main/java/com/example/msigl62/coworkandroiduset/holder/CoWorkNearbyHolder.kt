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
            imageCoWorkNearby.load(coWork.poster)
            ratingText.text=coWork.averageRating.toInt().toString()
            ratingSuggest.numStars = coWork.averageRating.toInt()
            ratingSuggest.rating = coWork.averageRating
            if(coWork.status == "false"){
                status.visibility = View.GONE
            }else{ }
            imageCoWorkNearby.setOnClickListener {
                itemView.context.startActivity(Intent(itemView.context, DetailNearbyActivity::class.java
                ).putExtra("key", coWork._id)
                        .putExtra("latitude", coWork.latitude)
                        .putExtra("longitude", coWork.longitude)
                        .putExtra("poster", coWork.poster)
                        .putExtra("im", coWork.gallery)
                        .putExtra("raring",coWork.averageRating)
                )
            }
        }
    }
}