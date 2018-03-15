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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_detail_co_work.*

class DetailCoWork : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_co_work)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.mapCoWork) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setPagerImage()
    }

    //TODO Pager
    private fun setPagerImage() {
        var mSectionsPagerAdapter: SectionsPagerAdapter?
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager,"")
        container.adapter = mSectionsPagerAdapter
    }

    //TODO Map
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val sydney = LatLng(13.736717, 100.523186)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(13.736717, 100.523186), 14.0f))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        Glide.with(this)
                .asBitmap().load("https://scontent.xx.fbcdn.net/v/t1.0-1/c0.1.200.200/p200x200/24296346_841543329361507_5839077709061960159_n.jpg?oh=ddb06acfbc8b42d6cf0b66be0041f829&oe=5B0245C0")
                .apply(RequestOptions().override(110, 110).apply(RequestOptions().circleCrop()))
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        val i = BitmapDescriptorFactory.fromBitmap((resource))
                        mMap.addMarker(sydney?.let { MarkerOptions().position(it).title("test").icon(i) }) }
                })


    }
}
