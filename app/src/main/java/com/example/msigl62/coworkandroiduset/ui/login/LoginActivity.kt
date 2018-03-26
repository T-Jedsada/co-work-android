package com.example.msigl62.coworkandroiduset.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.extension.navigate
import com.example.msigl62.coworkandroiduset.model.LoginEmail
import com.example.msigl62.coworkandroiduset.ui.MainFragment
import com.example.msigl62.coworkandroiduset.ui.forgot.ForgotActivity
import com.example.msigl62.coworkandroiduset.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener, LoginContact.View {
    private lateinit var presenter: LoginContact.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        register.setOnClickListener(this)
        btnSubmitLogin.setOnClickListener(this)
        forgot.setOnClickListener(this)
        btnSubmitLoginFacebook.setOnClickListener(this)
        presenter = LoginPresenter(this)
        edtPasswordLogin.hint = "Password"
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.register -> {
                navigate<RegisterActivity> {}
            }
            R.id.btnSubmitLogin -> {
                val model = LoginEmail(edtEmailLogin.text.trim().toString(), edtPasswordLogin.text.trim().toString())
                presenter.checkEdiTextLogin(model)
            }
            R.id.forgot -> {
                navigate<ForgotActivity> {}
            }
            R.id.btnSubmitLoginFacebook -> {
                navigate<RegisterActivity> {
                    putExtra("keyStatusFormLoginActivity", "true")
                }
            }
            else -> { }
        }
    }

    override fun onSuccessValidated(model: LoginEmail) {
        presenter.requestValidateApiLogin(model)
    }

    override fun onErrorMessage(err: Int) {
        Toast.makeText(this, applicationContext.getText(err), Toast.LENGTH_SHORT).show()
    }

    override fun onResponseFromApiLogin(resMessage: String, name: String?, image: String?,message:String?,status:String?) {
        if (resMessage == "false") {
            Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show()
        } else {
            if(resMessage=="status-false"){
                Toast.makeText(this, R.string.statusFalseConfirmSingUp, Toast.LENGTH_SHORT).show()
            }else{
                val section = getSharedPreferences("sectionLogin", Context.MODE_PRIVATE)
                val editor = section.edit()
                editor.putString("sectionLoginName", name)
                editor.putString("sectionLoginImage",image)
                editor.commit()
                val i = Intent(this, MainFragment::class.java)
                startActivity(i)
            }
        }
    }

    override fun onBackPressed() {
        val i = Intent(this, MainFragment::class.java)
        startActivity(i)
    }
}


