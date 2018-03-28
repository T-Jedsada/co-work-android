package com.example.msigl62.coworkandroiduset.ui.detail

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.adapter.AdapterReView
import com.example.msigl62.coworkandroiduset.adapter.AdapterReViewShowAll
import com.example.msigl62.coworkandroiduset.adapter.SectionsPagerAdapter
import com.example.msigl62.coworkandroiduset.extension.navigate
import com.example.msigl62.coworkandroiduset.extension.simpleFadeInAnimation
import com.example.msigl62.coworkandroiduset.extension.simpleFadeOutAnimation
import com.example.msigl62.coworkandroiduset.model.Gallery
import com.example.msigl62.coworkandroiduset.model.ResponseDetail
import com.example.msigl62.coworkandroiduset.model.ResponseReView
import com.example.msigl62.coworkandroiduset.ui.MainFragment
import com.example.msigl62.coworkandroiduset.ui.reserve.ReserveActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_detail_co_work.*
import kotlinx.android.synthetic.main.list_co_work_review.*

class DetailNearbyActivity : AppCompatActivity(), OnMapReadyCallback, DetailContact.View {

    private lateinit var mMap: GoogleMap
    private val presenter: DetailContact.Presenter = DetailPresenter(this)
    private var id:String?=null
    private var rating:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_co_work)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapCoWork) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setPagerImage()
        id = intent.extras?.getString("key")
        rating = intent.extras?.getString("raring")
        presenter.checkIdProvider(id)
        presenter.checkIdreView("5aafe91005ace400144e2b9a")  //TODO make value
        setGallery()
        setId()
    }

    private fun setId() {
        backMain.setOnClickListener {
            navigate<MainFragment> {}
        }
    }

    private fun setGallery() {
        gallery.setOnClickListener {
            val id = intent.extras?.getString("key")
            navigate<GalleryActivity> {
                putExtra("id",id)
            }
        }
    }

    private fun setPagerImage() {
        val mSectionsPagerAdapter: SectionsPagerAdapter?
        val im: Gallery = intent.getParcelableExtra("im")
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager,
                im.img1,
                im.img2,
                im.img3,
                im.img4,
                im.img5)
        container.adapter = mSectionsPagerAdapter
        val dot: Int
        val dots: Array<ImageView?>
        dot = mSectionsPagerAdapter.count
        dots = arrayOfNulls(dot)
        for (i in 0 until dot) {
            dots[i] = ImageView(this)
            dots[i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.nonactive_dot))
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(8, 0, 8, 0)
            sliderDots.addView(dots[i], params)
        }
        dots[0]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))
        container.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                for (i in 0 until dot) {
                    dots[i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.nonactive_dot))
                }
                dots[position]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val latitude = intent.extras?.getDouble("latitude")
        val longitude = intent.extras?.getDouble("longitude")
        val sydney = latitude ?.let { longitude?.let { it1 -> LatLng(it, it1) } }
        mMap.addMarker(sydney?.let { MarkerOptions().position(it).title("") })
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 19.0f))

    }

    override fun onResponseFromApi(responseDetail: ResponseDetail?) {
        nameCoWorking.text = responseDetail?.data?.get(0)?.name
        content.text = responseDetail?.data?.get(0)?.details
        address.text = responseDetail?.data?.get(0)?.address
        textPrice.text = responseDetail?.data?.get(0)?.price_per_hour
        ratingTextDetail.text=rating
        rat.rating=rating?.toFloat()!!
        textContact.text = "0816117137"  //TODO make value
        textContact.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", textContact.text as String, null))
            startActivity(intent)
        }
        if (responseDetail?.data?.get(0)?.status.equals("true")) {
            btnReserveSeat.setImageResource(R.drawable.reserve_seat)
            btnReserveSeat.setOnClickListener {
                navigate<ReserveActivity> {  }
                val section = getSharedPreferences("idCOWork", Context.MODE_PRIVATE)
                val editor = section.edit()
                editor.putString("idCOWork", id)
                editor.commit()
            }
        } else { }
    }

    override fun onResponseFromApiReView(responseReView: ResponseReView?) {
        val adapterReView: AdapterReView by lazy { AdapterReView(listOf()) }
        recyclerViewReview?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewReview?.adapter = adapterReView
        responseReView?.let { adapterReView.setItem(it.data) }
        showAllView.setOnClickListener {
            showAllView.visibility = View.GONE
            val adapterReView: AdapterReViewShowAll by lazy { AdapterReViewShowAll(listOf()) }
            recyclerViewReview?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            recyclerViewReview?.adapter = adapterReView
            responseReView?.let { adapterReView.setItem(it.data) }
        }
    }
}