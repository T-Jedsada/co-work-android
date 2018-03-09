package com.example.msigl62.coworkandroiduset.ui

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.ui.home.HomeFragment
import com.example.msigl62.coworkandroiduset.ui.login.LoginActivity
import com.example.msigl62.coworkandroiduset.ui.profile.ProfileUserFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivityFragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(R.id.containerFragment, HomeFragment(), "")
                .commit()
        setUpBottomBar()
    }

    private fun setUpBottomBar() {
        bottomBar?.setOnTabSelectListener { tabId ->
            when (tabId) {
                R.id.explore -> {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.containerFragment, HomeFragment(), "")
                            .commit()
                }
                R.id.seat -> {
                }
                R.id.saved -> {
                }
                R.id.profile -> {
                    val sh1 = getSharedPreferences("sectionLogin", Context.MODE_PRIVATE)
                    val nameUserLogin = sh1.getString("sectionLoginName", "")
                    if (nameUserLogin.isNotEmpty()) {
                        supportFragmentManager.beginTransaction()
                                .replace(R.id.containerFragment, ProfileUserFragment(), "")
                                .commit()
                    } else {
                        val i = Intent(this, LoginActivity::class.java)
                        startActivity(i)
                    }
                }
                else -> {
                }
            }
        }
    }

    override fun onBackPressed() {}
}
