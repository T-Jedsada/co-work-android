package com.example.msigl62.coworkandroiduset.holder

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.msigl62.coworkandroiduset.extension.load
import com.example.msigl62.coworkandroiduset.model.modellistcowork.CoWorkPopular
import com.example.msigl62.coworkandroiduset.ui.detail.DetailPopularActivity
import kotlinx.android.synthetic.main.list_item_co_working_popular.view.*

class CoWorkPopularHolder (view: View) : RecyclerView.ViewHolder(view) {

    fun onBind(coWork: CoWorkPopular) {
        itemView.apply {
            imageCoWorkPopular.load( coWork.gellery?.image_01)
            //rating.rating= coWork.rarting.toFloat()
            ratingTextPop.text=coWork.rarting
            textCoWorkPopularName.text = coWork.name
            if(coWork.status == "false"){
                statusPop.visibility = View.GONE
            }else{ }
             imageCoWorkPopular.setOnClickListener {
              itemView.context.startActivity(Intent(itemView.context, DetailPopularActivity::class.java
               ).putExtra(DetailPopularActivity.Key, coWork))
            }
        } }
}