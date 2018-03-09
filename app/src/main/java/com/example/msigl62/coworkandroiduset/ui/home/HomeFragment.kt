package com.example.msigl62.coworkandroiduset.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.adapter.AdapterCoWorkNearby
import com.example.msigl62.coworkandroiduset.adapter.AdapterCoWorkPopular
import com.example.msigl62.coworkandroiduset.model.modellistcowork.CoWorkNearby
import kotlinx.android.synthetic.main.list_co_work_nearby_you.*
import kotlinx.android.synthetic.main.list_co_work_popular.*

class HomeFragment : Fragment(),HomeContact.View{

    private lateinit var presenter: HomeContact.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater!!.inflate(R.layout.activity_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = HomePresenter(this)
        presenter.callCoWorkNearby()

    }

    override fun onCallSuccessCoWorkNearby(coWorkNearby: List<CoWorkNearby>?) {
        val adapterCoWorkNearby: AdapterCoWorkNearby by lazy { AdapterCoWorkNearby(listOf()) }
        listCoWorkNearby.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        listCoWorkNearby.adapter = adapterCoWorkNearby
        coWorkNearby?.let { adapterCoWorkNearby.setItem(it) }
        //TODO change onCallSuccessCoWorkNearby
        val adapterCoWorkPopular: AdapterCoWorkPopular by lazy { AdapterCoWorkPopular(listOf()) }
        listCoWorkPopular.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        listCoWorkPopular.adapter = adapterCoWorkPopular
        coWorkNearby?.let { adapterCoWorkPopular.setItem(it) }
    }

}
