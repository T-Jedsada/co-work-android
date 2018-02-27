package com.example.msigl62.coworkandroiduset.ui.login
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.model.Login
import com.example.msigl62.coworkandroiduset.ui.forgot.ForgotActivity
import com.example.msigl62.coworkandroiduset.ui.home.HomeActivity
import com.example.msigl62.coworkandroiduset.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),View.OnClickListener,LoginContact.View{

    override fun onSuccessValidated(model: Login) {
        //TODO Call Api
        //presenter.requestValidateApi(model)
        Toast.makeText(applicationContext, ""+model, Toast.LENGTH_LONG).show()
    }

    override fun onErrorMessage(err: Int) {
        Toast.makeText(this, applicationContext.getText(err), Toast.LENGTH_SHORT).show()
    }

    override fun onResponseFromApi(resMessage: String) {
        //TODO true  val i = Intent(this, HomeActivity::class.java)

    }

    private lateinit var presenter: LoginContact.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        register.setOnClickListener(this)
        btnSubmitLogin.setOnClickListener(this)
        forgot.setOnClickListener(this)
        presenter = LoginPresenter(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.register -> {
                val i = Intent(this, RegisterActivity::class.java)
                startActivity(i)
            }
            R.id.btnSubmitLogin -> {
                //TODO get edittext email and password
                val model = Login("","karn939.n@gmail.com","123456")
                presenter.checkEdiTextLogin(model)
                val i = Intent(this, HomeActivity::class.java)
                startActivity(i)
            }
            R.id.forgot -> {
                val i = Intent(this, ForgotActivity::class.java)
                startActivity(i)
            }
            else -> { }
        } }

}


