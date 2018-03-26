package com.example.msigl62.coworkandroiduset.ui.reserve

import android.content.Intent
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.ContractMain
import com.example.msigl62.coworkandroiduset.PresenterMain
import com.example.msigl62.coworkandroiduset.base.BaseActivity
import kotlinx.android.synthetic.main.layout_toolbar.*

class ReserveSummaryActivity : BaseActivity<ContractMain.View, PresenterMain>()  {
    override fun showProgressDialog() {}

    override fun layoutToInflate(): Int= R.layout.activity_reserve_summary

    override fun setUpView() {}

    override fun setUpToolBar() {
        image_arrow.setOnClickListener {
            startActivity(Intent(this, ReserveActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

}
