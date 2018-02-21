package com.example.msi_gl62.co_work_android_uset.ui.login
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.msi_gl62.co_work_android_uset.R
import com.facebook.CallbackManager

class Login : AppCompatActivity() {
    private var callbackManager: CallbackManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        callbackManager = CallbackManager.Factory.create()
        callbackManager = CallbackManager.Factory.create()
    }
}
