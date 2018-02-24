package com.example.msigl62.coworkandroiduset.ui.login
import android.content.Intent
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.ui.register.RegisterActivity
import com.example.msigl62.coworkandroiduset.ContractMain
import com.example.msigl62.coworkandroiduset.PresenterMain
import com.example.msigl62.coworkandroiduset.base.BaseActivity
import com.example.msigl62.coworkandroiduset.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<ContractMain.View, PresenterMain>(){
    override fun setUpBottomBar() {}
    override fun showProgressDialog() {}
    override fun layoutToInflate(): Int=R.layout.activity_login
    override fun setUpView() {
        register.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
        }
        btnSubmitLogin.setOnClickListener {
            val i = Intent(this, HomeActivity::class.java)
            startActivity(i)
        }
    }
}
