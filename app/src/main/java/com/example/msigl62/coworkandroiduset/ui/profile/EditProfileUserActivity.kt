package com.example.msigl62.coworkandroiduset.ui.profile

import android.content.Context
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.ContractMain
import com.example.msigl62.coworkandroiduset.PresenterMain
import com.example.msigl62.coworkandroiduset.base.BaseActivity
import com.example.msigl62.coworkandroiduset.extension.load
import com.example.msigl62.coworkandroiduset.extension.navigate
import com.example.msigl62.coworkandroiduset.ui.MainActivity
import kotlinx.android.synthetic.main.activity_edit_profile_user.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class EditProfileUserActivity : BaseActivity<ContractMain.View, PresenterMain>() {

    override fun showProgressDialog() {}

    override fun layoutToInflate(): Int = R.layout.activity_edit_profile_user

    override fun setUpToolBar() {
        image_arrow.setOnClickListener {
            navigate<MainActivity> {  }
        }
    }

    override fun setUpView() {
        setUpUserProfile()
    }

    private fun setUpUserProfile(){
        val sh1 = getSharedPreferences("sectionLogin", Context.MODE_PRIVATE)
        val nameUserLogin = sh1?.getString("sectionLoginName", "")
        val imageUserLogin = sh1?.getString("sectionLoginImage", "")
        editName.setText(nameUserLogin)
        imEditUser.load(imageUserLogin)
    }
}
