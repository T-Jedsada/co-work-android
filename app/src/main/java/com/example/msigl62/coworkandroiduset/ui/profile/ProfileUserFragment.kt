package com.example.msigl62.coworkandroiduset.ui.profile

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.ui.MainActivity
import kotlinx.android.synthetic.main.activity_profile_user.*

class ProfileUserFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater!!.inflate(R.layout.activity_profile_user, container, false)

    @SuppressLint("ApplySharedPref")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLogOut()
        setEditProfil()
        setProfileUser()

    }

    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState) }

    private fun setEditProfil() {
        imEditProfile.setOnClickListener {
            startActivity(Intent(context, EditProfileUserActivity::class.java))
        }
    }

    private fun setLogOut(){
        logOut.setOnClickListener {
            val section = this.activity?.getSharedPreferences("sectionLogin", Context.MODE_PRIVATE)
            val editor = section?.edit()
            editor?.putString("sectionLoginName",null)
            editor?.commit()
            startActivity(Intent(context, MainActivity::class.java))
        }
    }

    private fun setProfileUser(){
        val sh1 = this.activity?.getSharedPreferences("sectionLogin", Context.MODE_PRIVATE)
        val nameUserLogin = sh1?.getString("sectionLoginName", "")
        profileName.text = "Hello,"+nameUserLogin
    }
}
