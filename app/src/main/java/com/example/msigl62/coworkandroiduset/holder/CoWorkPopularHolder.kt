package com.example.msigl62.coworkandroiduset.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.msigl62.coworkandroiduset.extension.load
import com.example.msigl62.coworkandroiduset.model.modellistcowork.CoWorkNearby
import kotlinx.android.synthetic.main.list_item_co_working_popular.view.*


class CoWorkPopularHolder (view: View) : RecyclerView.ViewHolder(view) {
    fun onBind(coWork: CoWorkNearby) {
        itemView.apply {
            imageCoWorkPopular.load( coWork.header_blog_image)
        }



    }
}