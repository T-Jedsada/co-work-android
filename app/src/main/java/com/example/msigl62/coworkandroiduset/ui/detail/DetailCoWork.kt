package com.example.msigl62.coworkandroiduset.ui.detail

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.adapter.SectionsPagerAdapter
import com.example.msigl62.coworkandroiduset.model.modellistcowork.CoWorkPopular
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_detail_co_work.*

class DetailCoWork : AppCompatActivity(), OnMapReadyCallback {
    companion object { const val Key = "KEY_DATA" }
    private lateinit var mMap: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_co_work)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.mapCoWork) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setPagerImage()
        setDetailCoWork()

    }

    private fun setDetailCoWork(){
        val dataCoWork: CoWorkPopular = intent.getParcelableExtra(Key)
        nameCoWorking.text = dataCoWork.name
        content.text=dataCoWork.details
        //textPrice.text=(dataCoWork.price_per_hour.toString())
    }

    //TODO Pager
    private fun setPagerImage() {
        var mSectionsPagerAdapter: SectionsPagerAdapter?
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager,"")
        container.adapter = mSectionsPagerAdapter
    }

    //TODO Map
    override fun onMapReady(googleMap: GoogleMap) {
        val dataCoWork: CoWorkPopular = intent.getParcelableExtra(Key)
        val lat:Double?= dataCoWork.latitude
        val lng:Double?= dataCoWork.longitude
        mMap = googleMap
        val sydney = lat?.let { lng?.let { it1 -> LatLng(it, it1) } }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,19.0f))
        Glide.with(this)
                .asBitmap().load("")
                .apply(RequestOptions().override(110, 110).apply(RequestOptions().circleCrop()))
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        val i = BitmapDescriptorFactory.fromBitmap((resource))
                        mMap.addMarker(sydney?.let { MarkerOptions().position(it).title(dataCoWork.name).icon(i) }) }
                })
    }
}
