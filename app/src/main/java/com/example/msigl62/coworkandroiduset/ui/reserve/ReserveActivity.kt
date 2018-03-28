package com.example.msigl62.coworkandroiduset.ui.reserve

import android.util.Log
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.ContractMain
import com.example.msigl62.coworkandroiduset.PresenterMain
import com.example.msigl62.coworkandroiduset.base.BaseActivity
import com.example.msigl62.coworkandroiduset.extension.navigate
import kotlinx.android.synthetic.main.activity_reserve.*

class ReserveActivity : BaseActivity<ContractMain.View, PresenterMain>() {

    override fun showProgressDialog() {}

    override fun setUpToolBar() {}

    override fun layoutToInflate(): Int =R.layout.activity_reserve

    override fun setUpView() {
        val idCoWork = intent.extras?.getString("idCoWork")
        Log.e("id","id"+idCoWork)
        setButton()
    }

    private fun setButton(){
        btnConfirmReserve.setOnClickListener {
            navigate<ReserveSummaryActivity> {  }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}
