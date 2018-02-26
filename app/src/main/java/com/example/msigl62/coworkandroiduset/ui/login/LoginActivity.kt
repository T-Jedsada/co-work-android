package com.example.msigl62.coworkandroiduset.ui.login
import android.content.Intent
import android.view.View
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.ui.register.RegisterActivity
import com.example.msigl62.coworkandroiduset.ContractMain
import com.example.msigl62.coworkandroiduset.PresenterMain
import com.example.msigl62.coworkandroiduset.base.BaseActivity
import com.example.msigl62.coworkandroiduset.ui.forgot.ForgotActivity
import com.example.msigl62.coworkandroiduset.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*
class LoginActivity : BaseActivity<ContractMain.View, PresenterMain>(),View.OnClickListener{

    override fun setUpBottomBar() {}
    override fun showProgressDialog() {}
    override fun layoutToInflate(): Int=R.layout.activity_login
    override fun setUpView() {
        register.setOnClickListener(this)
        btnSubmitLogin.setOnClickListener(this)
        forgot.setOnClickListener(this)
    }
    override fun onClick(v: View) {
        when (v.id) {
            R.id.register -> {
                val i = Intent(this, RegisterActivity::class.java)
                startActivity(i)
            }
            R.id.btnSubmitLogin -> {
                val i = Intent(this, HomeActivity::class.java)
                startActivity(i)
            }
            R.id.forgot -> {
                val i = Intent(this, ForgotActivity::class.java)
                startActivity(i)
            }
            else -> {
            }
        } }

}


