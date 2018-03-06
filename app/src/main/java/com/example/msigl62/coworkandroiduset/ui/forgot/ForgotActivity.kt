package com.example.msigl62.coworkandroiduset.ui.forgot

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.model.Forgot
import com.example.msigl62.coworkandroiduset.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_forgot.*
import kotlinx.android.synthetic.main.layout_toolbar.*

@Suppress("DEPRECATION")
class ForgotActivity : AppCompatActivity(),ForgotContact.View  {
    private lateinit var presenter: ForgotContact.Presenter
    private var loadingDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)
        setToolBar()
        setButtonSubmitForgotPassword()
        presenter = ForgotPresenter(this)
    }

    @SuppressLint("SetTextI18n")
    private fun setToolBar() {
        text_toolbar.text = getString(R.string.login_header)
        image_arrow.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        } }

    override fun onSuccessValidated(model: Forgot) {
        loadingDialog = ProgressDialog.show(this,
                "Loading",
                "Loading...",
                true,
                false
        )
        presenter.requestValidateApi(model)
    }

    override fun onErrorMessage(err: Int) {
        Toast.makeText(this, applicationContext.getText(err), Toast.LENGTH_SHORT).show()
    }

    override fun onResponseFromApi(resMessage: String) {
        loadingDialog?.dismiss()
        if(resMessage == "noSuccess"){
            Toast.makeText(this,"This email do not sing up", Toast.LENGTH_SHORT).show()
        }else{
            val i = Intent(this, ForgotActivityFinish::class.java)
            startActivity(i)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left) }
    }

    private fun setButtonSubmitForgotPassword() {
        btnSubmitForgot.setOnClickListener {
            val model=Forgot("",edt_forgot_email.text.trim().toString())
            presenter.checkEdiText(model) }
    }

}
