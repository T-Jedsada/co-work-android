package com.example.msigl62.coworkandroiduset.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.adapter.SectionsPagerAdapter
import com.example.msigl62.coworkandroiduset.model.ResponseDetail
import com.example.msigl62.coworkandroiduset.model.modellistcowork.CoWorkPopular
import com.example.msigl62.coworkandroiduset.ui.MainFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_detail_co_work.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class DetailCoWorkPoppularActivity : AppCompatActivity(), OnMapReadyCallback,DetailContact.View {

    companion object {
        const val Key = "KEY_DATA"
    }

    private lateinit var mMap: GoogleMap
    private val presenter: DetailContact.Presenter = DetailPresenter(this)
    private var lat: Double? = null
    private var lng: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_co_work)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.mapCoWork) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setPagerImage()
        setDetailCoWork()
        setToolBar()
        val dataCoWork: CoWorkPopular = intent.getParcelableExtra(Key)
        Log.e("sdsdsd","ssdsdsd"+dataCoWork)
        presenter.checkIdProvider(dataCoWork._id)
    }

    @SuppressLint("SetTextI18n")
    private fun setToolBar() {
        image_arrow.setOnClickListener {
            val i = Intent(this, MainFragment::class.java)
            startActivity(i)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

    //TODO set Tel:
    private fun setDetailCoWork() {
        textContact.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", textContact.toString(), null))
            startActivity(intent)
        }
    }

    private fun setPagerImage() {
        val dataCoWork: CoWorkPopular = intent.getParcelableExtra(Key)
        var mSectionsPagerAdapter: SectionsPagerAdapter?
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager,
                dataCoWork.gellery?.image_01.toString(),
                dataCoWork.gellery?.image_02.toString(),
                dataCoWork.gellery?.image_03.toString(),
                dataCoWork.gellery?.image_04.toString(),
                dataCoWork.gellery?.image_05.toString())
        container.adapter = mSectionsPagerAdapter
        var dotscount: Int
        var dots: Array<ImageView?>
        dotscount = mSectionsPagerAdapter.count
        dots = arrayOfNulls(dotscount)
        for (i in 0 until dotscount) {
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
                for (i in 0 until dotscount) {
                    dots[i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.nonactive_dot))
                }
                dots[position]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val dataCoWork: CoWorkPopular = intent.getParcelableExtra(Key)
        mMap = googleMap
        val sydney = lat?.let { lng?.let { it1 -> LatLng(it, it1) } }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 19.0f))
        Glide.with(this)
                .asBitmap().load(dataCoWork.gellery?.image_01)
                .apply(RequestOptions().override(110, 110).apply(RequestOptions().circleCrop()))
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        val i = BitmapDescriptorFactory.fromBitmap((resource))
                        mMap.addMarker(sydney?.let { MarkerOptions().position(it).title(dataCoWork.name).icon(i) })
                    }
                })
    }

    override fun onResponseFromApi(responseDetail: ResponseDetail?) {
        nameCoWorking.text= responseDetail?.data?.get(0)?.name
        content.text=responseDetail?.data?.get(0)?.details
        address.text=responseDetail?.data?.get(0)?.address
        textPrice.text=(responseDetail?.data?.get(0)?.price_per_hour+"Baht")
        lat=responseDetail?.data?.get(0)?.latitude
        lng=responseDetail?.data?.get(0)?.longitude
    }
}
