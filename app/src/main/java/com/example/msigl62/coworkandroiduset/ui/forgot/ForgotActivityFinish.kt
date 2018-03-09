package com.example.msigl62.coworkandroiduset.ui.forgot

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_forgot_finish.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class ForgotActivityFinish : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_finish)
        setToolBar()
        setButtonBackToLogin()
    }

    private fun setButtonBackToLogin() {
        btnBackToLogin.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left) }
    }

    @SuppressLint("SetTextI18n")
    private fun setToolBar() {
        text_toolbar.text = getString(R.string.forgot_header)
        image_arrow.setOnClickListener {
            val i = Intent(this, ForgotActivity::class.java)
            startActivity(i)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

}
