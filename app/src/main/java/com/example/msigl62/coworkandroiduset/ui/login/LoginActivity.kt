package com.example.msigl62.coworkandroiduset.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.model.Login
import com.example.msigl62.coworkandroiduset.ui.forgot.ForgotActivity
import com.example.msigl62.coworkandroiduset.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),View.OnClickListener,LoginContact.View{

    private lateinit var presenter: LoginContact.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        register.setOnClickListener(this)
        btnSubmitLogin.setOnClickListener(this)
        forgot.setOnClickListener(this)
        btnSubmitLoginFacebook.setOnClickListener(this)
        presenter = LoginPresenter(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.register -> {
                val i = Intent(this, RegisterActivity::class.java)
                startActivity(i) }
            R.id.btnSubmitLogin -> {
                // TODO channel login email
                val model= Login("",edt_Email_Login.text.trim().toString(),edt_Password_Login.text.trim().toString())
                presenter.checkEdiTextLogin(model)
            }
            R.id.forgot -> {
                val i = Intent(this, ForgotActivity::class.java)
                startActivity(i)
            }
            R.id.btnSubmitLoginFacebook->{
                // TODO channel login facrbook
                //val intent = Intent(this, RegisterActivity::class.java)
                //intent.putExtra("keyStatusFormLoginActivity", "false")
                //startActivity(intent)
                val model= Login("44555","","")
                presenter.getIdUserFacebookLogin(model)
            }
            else -> { }
        }
    }

    override fun onSuccessValidated(model: Login) {
        presenter.requestValidateApi(model)
    }

    override fun onErrorMessage(err: Int) {
        Toast.makeText(this, applicationContext.getText(err), Toast.LENGTH_SHORT).show()
    }

    override fun onResponseFromApi(resMessage: String) {
        Toast.makeText(this, ""+resMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {}
}


