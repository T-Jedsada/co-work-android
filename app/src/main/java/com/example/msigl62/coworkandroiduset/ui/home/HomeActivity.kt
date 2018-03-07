package com.example.msigl62.coworkandroiduset.ui.home

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.annotation.IdRes
import android.support.v7.widget.LinearLayoutManager
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.ContractMain
import com.example.msigl62.coworkandroiduset.PresenterMain
import com.example.msigl62.coworkandroiduset.adapter.AdapterCoWorkNearby
import com.example.msigl62.coworkandroiduset.adapter.AdapterCoWorkPopular
import com.example.msigl62.coworkandroiduset.base.BaseActivity
import com.example.msigl62.coworkandroiduset.model.modellistcowork.CoWorkNearby
import com.example.msigl62.coworkandroiduset.ui.login.LoginActivity
import com.example.msigl62.coworkandroiduset.ui.profile.ProfileUserActivity
import com.roughike.bottombar.BottomBar
import com.roughike.bottombar.OnMenuTabClickListener
import kotlinx.android.synthetic.main.list_co_work_nearby_you.*
import kotlinx.android.synthetic.main.list_co_work_popular.*

class HomeActivity : BaseActivity<ContractMain.View, PresenterMain>(),HomeContact.View{

    private lateinit var presenter: HomeContact.Presenter
    private var bottomBar: BottomBar? = null

    override fun setUpBottomBar() {
        val savedInstanceState = null
        bottomBar = BottomBar.attachShy(findViewById(R.id.myCoordinator),
                findViewById(R.id.myScrollingContent), savedInstanceState)
        bottomBar?.setItems(R.menu.bottom_bar_menu)
        bottomBar?.setOnMenuTabClickListener(object : OnMenuTabClickListener {
            override fun onMenuTabSelected(@IdRes menuItemId: Int) {
                when (menuItemId) {
                    R.id.explore -> {
                    }
                    R.id.saved -> {
                    }
                    R.id.seat -> {
                    }
                    R.id.profile -> {
                        checkSectionLogin()
                    }
                    else -> {} }
            }
            override fun onMenuTabReSelected(@IdRes menuItemId: Int) {}
        })
    }

    private fun checkSectionLogin(){
        val sh1 = getSharedPreferences("sectionLogin", Context.MODE_PRIVATE)
        val nameUserLogin = sh1.getString("sectionLoginName", "")
        if(nameUserLogin.isNotEmpty()){
            val i = Intent(this, ProfileUserActivity::class.java)
            startActivity(i)
        }else{
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
    }

    override fun showProgressDialog() {}

    override fun layoutToInflate(): Int =R.layout.activity_home

    override fun setUpView() {
        presenter = HomePresenter(this)
        presenter.callCoWorkNearby()
    }

    override fun onCallSuccessCoWorkNearby(coWorkNearby: List<CoWorkNearby>?) {
        val adapterCoWorkNearby: AdapterCoWorkNearby by lazy { AdapterCoWorkNearby(listOf()) }
        listCoWorkNearby.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        listCoWorkNearby.adapter = adapterCoWorkNearby
        coWorkNearby?.let { adapterCoWorkNearby.setItem(it) }
        //TODO change onCallSuccessCoWorkNearby
        val adapterCoWorkPopular: AdapterCoWorkPopular by lazy { AdapterCoWorkPopular(listOf()) }
        listCoWorkPopular.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listCoWorkPopular.adapter = adapterCoWorkPopular
        coWorkNearby?.let { adapterCoWorkPopular.setItem(it) }
    }

    override fun onBackPressed() {
        val simpleAlert = AlertDialog.Builder(this).create()
        simpleAlert.setTitle("Exit")
        simpleAlert.setMessage("Do you want to leave the app ?")
        simpleAlert.setButton(AlertDialog.BUTTON_POSITIVE, "Enter", { _, _ ->
            finish()
        })
        simpleAlert.show()
    }

}
