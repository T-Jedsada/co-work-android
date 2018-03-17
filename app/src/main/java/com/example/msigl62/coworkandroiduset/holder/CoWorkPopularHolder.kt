package com.example.msigl62.coworkandroiduset.holder

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.example.msigl62.coworkandroiduset.extension.load
import com.example.msigl62.coworkandroiduset.model.modellistcowork.CoWorkNearby
import com.example.msigl62.coworkandroiduset.model.modellistcowork.CoWorkPopular
import com.example.msigl62.coworkandroiduset.ui.MainFragment
import com.example.msigl62.coworkandroiduset.ui.detail.DetailCoWork
import kotlinx.android.synthetic.main.list_item_co_working_popular.view.*

class CoWorkPopularHolder (view: View) : RecyclerView.ViewHolder(view) {
    fun onBind(coWork: CoWorkPopular) {
        itemView.apply {
            imageCoWorkPopular.load( coWork.cover_image?.image_01)
            textCoWorkPopularName.text = coWork.name
            imageCoWorkPopular.setOnClickListener {
              itemView.context.startActivity(Intent(
                       itemView.context, DetailCoWork::class.java
               ).putExtra(DetailCoWork.Key, coWork))
            }
        }
    }
}