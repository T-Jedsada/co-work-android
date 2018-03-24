package com.example.msigl62.coworkandroiduset.ui.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.extension.load
import com.example.msigl62.coworkandroiduset.model.ResponseDetail
import com.example.msigl62.coworkandroiduset.model.ResponseReView
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : AppCompatActivity(),DetailContact.View {
    private val presenter: DetailContact.Presenter = DetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        val id = intent.extras?.getString("id")
        presenter.checkIdProvider(id)
    }

    override fun onResponseFromApi(responseDetail: ResponseDetail?) {
        imGallery.load(responseDetail?.data?.get(0)?.gallery?.img1)
        imGallery2.load(responseDetail?.data?.get(0)?.gallery?.img2)
        imGallery3.load(responseDetail?.data?.get(0)?.gallery?.img3)
        imGallery4.load(responseDetail?.data?.get(0)?.gallery?.img4)
        imGallery5.load(responseDetail?.data?.get(0)?.gallery?.img5)
    }

    override fun onResponseFromApiReView(responseReView: ResponseReView?) {}
}
