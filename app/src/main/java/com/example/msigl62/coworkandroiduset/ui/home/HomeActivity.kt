package com.example.msigl62.coworkandroiduset.ui.home

import android.support.annotation.IdRes
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.ContractMain
import com.example.msigl62.coworkandroiduset.PresenterMain
import com.example.msigl62.coworkandroiduset.adapter.AdapterCoWorkNearby
import com.example.msigl62.coworkandroiduset.adapter.AdapterCoWorkPopular
import com.example.msigl62.coworkandroiduset.base.BaseActivity
import com.example.msigl62.coworkandroiduset.model.modellistcowork.CoWorkNearby
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
        bottomBar!!.setItems(R.menu.bottom_bar_menu)
        bottomBar!!.setOnMenuTabClickListener(object : OnMenuTabClickListener {
            override fun onMenuTabSelected(@IdRes menuItemId: Int) {
                if (menuItemId==R.id.explore){
                    Toast.makeText(applicationContext, "", Toast.LENGTH_LONG).show()
                }else{ }
            }
            override fun onMenuTabReSelected(@IdRes menuItemId: Int) {}
        })
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
}
