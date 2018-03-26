package com.example.msigl62.coworkandroiduset.ui.show

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.adapter.AdapterNearbyShowAll
import com.example.msigl62.coworkandroiduset.extension.navigate
import com.example.msigl62.coworkandroiduset.model.ResponseSuggestion
import com.example.msigl62.coworkandroiduset.model.modellistcowork.CoWorkPopular
import com.example.msigl62.coworkandroiduset.ui.MainFragment
import com.example.msigl62.coworkandroiduset.ui.home.HomeContact
import com.example.msigl62.coworkandroiduset.ui.home.HomePresenter
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.android.synthetic.main.list_co_work_nearby_you.*

class ShowAllNearbyActivity : AppCompatActivity(), HomeContact.View {

    private lateinit var presenter: HomeContact.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_all_nearby)
        presenter = HomePresenter(this)
        presenter.callCoWorkNearby(98.9487219,18.770992)
        setToolBar()
    }

    private fun setToolBar() {
        image_arrow.setOnClickListener {
            navigate<MainFragment> {  }
        }
    }

    override fun onCallSuccessCoWorkPopular(coWorkPopular: List<CoWorkPopular>?) {}

    override fun onCallSuccessCoWorkNearby(responseSuggestion: ResponseSuggestion?) {
        val adapterCoWorkNearby: AdapterNearbyShowAll by lazy { AdapterNearbyShowAll(listOf()) }
        listCoWorkNearby?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listCoWorkNearby?.adapter = adapterCoWorkNearby
        responseSuggestion?.let { adapterCoWorkNearby.setItem(it.data) }
    }
}
