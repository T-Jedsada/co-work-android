package com.example.msigl62.coworkandroiduset.ui.home

import android.support.annotation.IdRes
import android.widget.Toast
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.ContractMain
import com.example.msigl62.coworkandroiduset.PresenterMain
import com.example.msigl62.coworkandroiduset.base.BaseActivity
import com.roughike.bottombar.BottomBar
import com.roughike.bottombar.OnMenuTabClickListener


class HomeActivity : BaseActivity<ContractMain.View, PresenterMain>(){
    private var bottomBar: BottomBar? = null
    override fun setUpBottomBar() {
        val savedInstanceState = null
        bottomBar = BottomBar.attachShy(findViewById(R.id.myCoordinator),
                findViewById(R.id.myScrollingContent), savedInstanceState)
        bottomBar!!.setItems(R.menu.bottom_bar_menu)
        bottomBar!!.setOnMenuTabClickListener(object : OnMenuTabClickListener {
            override fun onMenuTabSelected(@IdRes menuItemId: Int) {
                if (menuItemId==R.id.like){
                    Toast.makeText(applicationContext, "", Toast.LENGTH_LONG).show()
                }else{ } }
            override fun onMenuTabReSelected(@IdRes menuItemId: Int) {}
        })
    }

    override fun showProgressDialog() {}

    override fun layoutToInflate(): Int =R.layout.activity_home

    override fun setUpView() {
    }


}
