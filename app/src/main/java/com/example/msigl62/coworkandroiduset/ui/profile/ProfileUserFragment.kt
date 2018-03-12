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
import com.example.msigl62.coworkandroiduset.extension.load
import com.example.msigl62.coworkandroiduset.ui.MainFragment
import kotlinx.android.synthetic.main.activity_profile_user.*

class ProfileUserFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater!!.inflate(R.layout.activity_profile_user, container, false)

    @SuppressLint("ApplySharedPref")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sh1 = this.activity?.getSharedPreferences("sectionLogin", Context.MODE_PRIVATE)
        val nameUserLogin = sh1?.getString("sectionLoginName", "")
        val imageUserLogin = sh1?.getString("sectionLoginImage", "")
        nameUser.text = nameUserLogin
        imageViewProfile.load(imageUserLogin)
        layOutLogOut.setOnClickListener {
            val section = this.activity?.getSharedPreferences("sectionLogin", Context.MODE_PRIVATE)
            val editor = section?.edit()
            editor?.putString("sectionLoginName",null)
            editor?.commit()
            val i = Intent(context, MainFragment::class.java)
            startActivity(i)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
