package com.example.msigl62.coworkandroiduset.ui.login

import com.example.msigl62.coworkandroiduset.PresenterMain
import com.example.msigl62.coworkandroiduset.R
import com.example.msigl62.coworkandroiduset.ContractMain
import com.example.msigl62.coworkandroiduset.base.BaseActivity


class Login : BaseActivity<ContractMain.View, PresenterMain>(){

    override fun showProgressDialog() {}
    override fun layoutToInflate(): Int=R.layout.activity_login
    override fun setupView() {}
}