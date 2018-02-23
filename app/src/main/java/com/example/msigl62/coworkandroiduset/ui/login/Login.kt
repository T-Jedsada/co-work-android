package com.example.msigl62.coworkandroiduset.ui.login
import android.content.Intent
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.ui.register.Register
import com.example.msigl62.coworkandroiduset.ContractMain
import com.example.msigl62.coworkandroiduset.PresenterMain
import com.example.msigl62.coworkandroiduset.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class Login : BaseActivity<ContractMain.View, PresenterMain>(){
    override fun showProgressDialog() {}
    override fun layoutToInflate(): Int=R.layout.activity_login
    override fun setupView() {
        btnRegister.setOnClickListener {
            val i = Intent(this, Register::class.java)
            startActivity(i)
        } }
}
