package com.example.msigl62.coworkandroiduset.ui.detail

import android.annotation.SuppressLint
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.ContractMain
import com.example.msigl62.coworkandroiduset.PresenterMain
import com.example.msigl62.coworkandroiduset.base.BaseFragment
import com.example.msigl62.coworkandroiduset.extension.load
import kotlinx.android.synthetic.main.pager_theme.*

@SuppressLint("ValidFragment")
class ImageCoWorkPagerThree (imageGalleryThree:String): BaseFragment<ContractMain.View, PresenterMain>() {
    var url: String =imageGalleryThree

    override fun showProgressDialog() {}

    override fun layoutToInflate(): Int = R.layout.pager_theme

    override fun setup() { imagePager.load(url) }
}