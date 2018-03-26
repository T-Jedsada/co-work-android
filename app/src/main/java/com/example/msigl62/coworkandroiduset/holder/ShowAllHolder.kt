package com.example.msigl62.coworkandroiduset.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.msigl62.coworkandroiduset.extension.load
import com.example.msigl62.coworkandroiduset.model.DataCoWorkNearby
import kotlinx.android.synthetic.main.list_item_co_working_popular.view.*

class ShowAllHolder (view: View) : RecyclerView.ViewHolder(view) {
    fun onBind(coWork: DataCoWorkNearby) {
        itemView.apply {
            imageCoWorkPopular.load( coWork.poster)
            //rating.rating= coWork.rarting.toFloat()
            ratingTextPop.text=coWork.averageRating
            textCoWorkPopularName.text = coWork.name

        } }
}