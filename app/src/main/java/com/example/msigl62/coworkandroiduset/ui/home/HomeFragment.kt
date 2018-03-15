package com.example.msigl62.coworkandroiduset.ui.home

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.adapter.AdapterCoWorkNearby
import com.example.msigl62.coworkandroiduset.adapter.AdapterCoWorkPopular
import com.example.msigl62.coworkandroiduset.model.modellistcowork.CoWorkNearby
import kotlinx.android.synthetic.main.list_co_work_nearby_you.*
import kotlinx.android.synthetic.main.list_co_work_popular.*

class HomeFragment : Fragment(), HomeContact.View, LocationListener {

    private lateinit var presenter: HomeContact.Presenter
    lateinit var locationManager: LocationManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.activity_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = HomePresenter(this)
        presenter.callCoWorkNearby()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        getLocationUser()
    }

    override fun onCallSuccessCoWorkNearby(coWorkNearby: List<CoWorkNearby>?) {
        val adapterCoWorkNearby: AdapterCoWorkNearby by lazy { AdapterCoWorkNearby(listOf()) }
        listCoWorkNearby?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        listCoWorkNearby?.adapter = adapterCoWorkNearby
        coWorkNearby?.let { adapterCoWorkNearby.setItem(it) }

        //TODO change onCallSuccessCoWorkNearby
        //TODO   Wait API CoWorkPopular
        val adapterCoWorkPopular: AdapterCoWorkPopular by lazy { AdapterCoWorkPopular(listOf()) }
        listCoWorkPopular?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        listCoWorkPopular?.adapter = adapterCoWorkPopular
        coWorkNearby?.let { adapterCoWorkPopular.setItem(it) }
    }

    private fun getLocationUser() {
        if (ContextCompat.checkSelfPermission(context as Activity, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context as Activity,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context as Activity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION), 101)
        }else{
            locationManager = activity?.getSystemService(Context.LOCATION_SERVICE)as LocationManager
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5f, this)
        }
    }

    override fun onLocationChanged(location: Location?) {
        Toast.makeText(activity, "lat : " + location?.latitude + " lng : " + location?.longitude, Toast.LENGTH_LONG).show()
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }


}
