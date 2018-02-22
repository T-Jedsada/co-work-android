package com.example.msi_gl62.co_work_android_uset.ui.login
import com.example.msi_gl62.co_work_android_uset.PresenterMain
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msi_gl62.rider.ContractMain
import com.example.msi_gl62.rider.base.BaseActivity
class Login : BaseActivity<ContractMain.View, PresenterMain>(){

    override fun showProgressDialog() {}
    override fun layoutToInflate(): Int=R.layout.activity_login
    override fun setupView() {
    }


}
