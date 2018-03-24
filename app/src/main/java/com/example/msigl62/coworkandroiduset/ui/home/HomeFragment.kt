package com.example.msigl62.coworkandroiduset.ui.home

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ethanhua.skeleton.Skeleton
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.adapter.AdapterCoWorkNearby
import com.example.msigl62.coworkandroiduset.adapter.AdapterCoWorkPopular
import com.example.msigl62.coworkandroiduset.model.modellistcowork.CoWorkPopular
import com.example.msigl62.coworkandroiduset.model.ResponseSuggestion
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.list_co_work_nearby_you.*
import kotlinx.android.synthetic.main.list_co_work_popular.*

class HomeFragment : Fragment(), HomeContact.View, LocationListener {

    private lateinit var presenter: HomeContact.Presenter
    private lateinit var locationManager: LocationManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater!!.inflate(R.layout.activity_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = HomePresenter(this)
        presenter.callCoWorkPopular()
        setFilter()
    }

    override fun onStart() {
        super.onStart()
        presenter.callCoWorkNearby(98.9487219,18.770992)
        //getLocationUser()
    }

    private fun getLocationUser() {
        if (ContextCompat.checkSelfPermission(context as Activity, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context as Activity,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context as Activity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION), 101)
        } else {
            locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 70000, 5f, this)
        }
    }

    private fun setFilter() {
        filter.setOnClickListener {
            val bottomSheetView = layoutInflater.inflate(R.layout.layout_filter_home, null)
            val bottomSheetDialog = BottomSheetDialog(context as Activity)
            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()
            bottomSheetDialog.show()
        }
    }

    override fun onCallSuccessCoWorkPopular(coWorkPopular: List<CoWorkPopular>?) {
        val adapterCoWorkPopular: AdapterCoWorkPopular by lazy { AdapterCoWorkPopular(listOf()) }
        listCoWorkPopular?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        listCoWorkPopular?.adapter = adapterCoWorkPopular
        coWorkPopular?.let { adapterCoWorkPopular.setItem(it) }
    }

    override fun onCallSuccessCoWorkNearby(responseSuggestion: ResponseSuggestion?) {
        val adapterCoWorkNearby: AdapterCoWorkNearby by lazy { AdapterCoWorkNearby(listOf()) }
        listCoWorkNearby?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        listCoWorkNearby?.adapter = adapterCoWorkNearby
        responseSuggestion?.let { adapterCoWorkNearby.setItem(it.data) }

        val skeletonScreen = Skeleton.bind(listCoWorkNearby)
                .adapter(adapterCoWorkNearby)
                .shimmer(true)
                .angle(20)
                .frozen(false)
                .duration(1200)
                .count(10)
                .load(R.layout.item_skeleton_news)
                .show()
        listCoWorkNearby.postDelayed(Runnable { skeletonScreen.hide() }, 3000)
        return

    }

    //TODO getLocation send API
    override fun onLocationChanged(location: Location?) {
        location?.let { presenter.callCoWorkNearby(location.longitude, location.latitude) }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }

}
