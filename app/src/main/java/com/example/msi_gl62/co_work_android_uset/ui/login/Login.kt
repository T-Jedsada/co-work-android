package com.example.msi_gl62.co_work_android_uset.ui.login
import android.content.Intent
import com.example.msi_gl62.co_work_android_uset.PresenterMain
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msi_gl62.rider.ContractMain
import com.example.msi_gl62.rider.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class Login : BaseActivity<ContractMain.View, PresenterMain>(){

    override fun showProgressDialog() {}
    override fun layoutToInflate(): Int=R.layout.activity_login
    override fun setupView() {

        btnRegister.setOnClickListener {
            val i = Intent(this, Register::class.java)
            startActivity(i)

        }
    }


}
