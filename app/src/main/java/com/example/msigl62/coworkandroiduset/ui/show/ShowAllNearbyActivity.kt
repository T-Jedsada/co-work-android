package com.example.msigl62.coworkandroiduset.ui.show

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
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

class ShowAllNearbyActivity : BaseActivity<ContractMain.View, PresenterMain>(), HomeContact.View, LocationListener {

    private lateinit var presenter: HomeContact.Presenter
    private lateinit var locationManager: LocationManager

    override fun onCallSuccessCoWorkPopular(coWorkPopular: List<CoWorkPopular>?) {}

    override fun onCallSuccessCoWorkNearby(responseSuggestion: ResponseSuggestion?) {
        val adapterCoWorkNearby: AdapterNearbyShowAll by lazy { AdapterNearbyShowAll(listOf()) }
        listCoWorkNearby?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listCoWorkNearby?.adapter = adapterCoWorkNearby
        responseSuggestion?.let { adapterCoWorkNearby.setItem(it.data) }
    }

    override fun showProgressDialog() {}

    override fun layoutToInflate(): Int =R.layout.activity_show_all_nearby

    override fun setUpView() {}

    override fun onStart() {
        super.onStart()
        getLocationUser()
    }

    override fun setUpToolBar() {
        image_arrow.setOnClickListener {
            navigate<MainFragment> {}
        }
    }

    private fun getLocationUser() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION), 101)
        } else {
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
        }
    }

    override fun onLocationChanged(location: Location?) {
        presenter = HomePresenter(this)
        location?.let { presenter.callCoWorkNearby(location.longitude, location.latitude) }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

    override fun onProviderEnabled(provider: String?) {}

    override fun onProviderDisabled(provider: String?) {}

}
