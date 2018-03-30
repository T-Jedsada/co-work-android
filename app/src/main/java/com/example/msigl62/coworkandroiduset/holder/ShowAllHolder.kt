package com.example.msigl62.coworkandroiduset.holder

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.msigl62.coworkandroiduset.extension.load
import com.example.msigl62.coworkandroiduset.model.DataCoWorkNearby
import com.example.msigl62.coworkandroiduset.ui.detail.DetailNearbyActivity
import kotlinx.android.synthetic.main.list_item_co_working_popular.view.*

class ShowAllHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun onBind(coWork: DataCoWorkNearby) {
        itemView.apply {
            imageCoWorkPopular.load(coWork.poster)
            rating.numStars= coWork.averageRating.toInt()
            rating.rating = coWork.averageRating
            ratingTextPop.text = coWork.averageRating.toInt().toString()
            textCoWorkPopularName.text = coWork.name
            imageCoWorkPopular.setOnClickListener {
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