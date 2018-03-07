package com.example.msigl62.coworkandroiduset.ui.profile

import android.content.Context
import android.content.Intent
import android.support.annotation.IdRes
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.ContractMain
import com.example.msigl62.coworkandroiduset.PresenterMain
import com.example.msigl62.coworkandroiduset.base.BaseActivity
import com.example.msigl62.coworkandroiduset.extension.load
import com.example.msigl62.coworkandroiduset.ui.home.HomeActivity
import com.roughike.bottombar.BottomBar
import com.roughike.bottombar.OnMenuTabClickListener
import kotlinx.android.synthetic.main.activity_profile_user.*

class ProfileUserActivity : BaseActivity<ContractMain.View, PresenterMain>() {

    private var bottomBar: BottomBar? = null

    override fun setUpBottomBar() {
        val savedInstanceState = null
        bottomBar = BottomBar.attachShy(findViewById(R.id.myCoordinator),
                findViewById(R.id.myScrollingContent), savedInstanceState)
        bottomBar?.setItems(R.menu.bottom_bar_menu)
        bottomBar?.setOnMenuTabClickListener(object : OnMenuTabClickListener {
            override fun onMenuTabSelected(@IdRes menuItemId: Int) {
                when (menuItemId) {
                    R.id.profile -> {
                    }
                    R.id.saved -> {
                    }
                    R.id.seat -> {
                    }
                    R.id.explore -> {
                    }
                    else -> {}
                }
            }
            override fun onMenuTabReSelected(@IdRes menuItemId: Int) {}
        })
    }

    override fun showProgressDialog() {}

    override fun layoutToInflate(): Int=R.layout.activity_profile_user

    override fun setUpView() {
        val sh1 = getSharedPreferences("sectionLogin", Context.MODE_PRIVATE)
        val nameUserLogin = sh1.getString("sectionLoginName", "")
        val imageUserLogin = sh1.getString("sectionLoginImage", "")
        nameUser.text = nameUserLogin
        imageViewProfile.load(imageUserLogin)
        layOutLogOut.setOnClickListener {
            val section = getSharedPreferences("sectionLogin", Context.MODE_PRIVATE)
            val editor = section.edit()
            editor.putString("sectionLoginName",null)
            editor.commit()
            val i = Intent(this, HomeActivity::class.java)
            startActivity(i) }
    }

    override fun onBackPressed() {
        val i = Intent(this, HomeActivity::class.java)
        startActivity(i)
    }
}
