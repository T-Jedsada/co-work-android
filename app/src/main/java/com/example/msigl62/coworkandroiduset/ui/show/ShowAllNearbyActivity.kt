package com.example.msigl62.coworkandroiduset.ui.show

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.ContractMain
import com.example.msigl62.coworkandroiduset.PresenterMain
import com.example.msigl62.coworkandroiduset.adapter.AdapterNearbyShowAll
import com.example.msigl62.coworkandroiduset.base.BaseActivity
import com.example.msigl62.coworkandroiduset.extension.navigate
import com.example.msigl62.coworkandroiduset.model.ResponseSuggestion
import com.example.msigl62.coworkandroiduset.model.modellistcowork.CoWorkPopular
import com.example.msigl62.coworkandroiduset.ui.MainFragment
import com.example.msigl62.coworkandroiduset.ui.home.HomeContact
import com.example.msigl62.coworkandroiduset.ui.home.HomePresenter
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.android.synthetic.main.list_co_work_nearby_you.*

class ShowAllNearbyActivity : BaseActivity<ContractMain.View, PresenterMain>(), HomeContact.View{
    private lateinit var presenter: HomeContact.Presenter

    override fun onCallSuccessCoWorkPopular(coWorkPopular: List<CoWorkPopular>?) {}

    override fun onCallSuccessCoWorkNearby(responseSuggestion: ResponseSuggestion?) {
        val adapterCoWorkNearby: AdapterNearbyShowAll by lazy { AdapterNearbyShowAll(listOf()) }
        listCoWorkNearby?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listCoWorkNearby?.adapter = adapterCoWorkNearby
        responseSuggestion?.let { adapterCoWorkNearby.setItem(it.data) }
    }

    override fun showProgressDialog() {}

    override fun layoutToInflate(): Int =R.layout.activity_show_all_nearby

    override fun setUpView() {
        getLocationUser()
    }

    override fun setUpToolBar() {
        image_arrow.setOnClickListener {
            navigate<MainFragment> {}
        }
    }

    private fun getLocationUser() {
        val sh1 = getSharedPreferences("location", Context.MODE_PRIVATE)
        val longitude = sh1?.getString("longitude", "")
        val latitude = sh1?.getString("latitude", "")
        presenter = HomePresenter(this)
        longitude?.toDouble()?.let { latitude?.toDouble()?.let { it1 -> presenter.callCoWorkNearby(it, it1) } }

    }
}
