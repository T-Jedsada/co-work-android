package com.example.msi_gl62.co_work_android_uset.ui.login
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.widget.Toast
import com.example.msi_gl62.co_work_android_uset.R
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.Pattern
import java.util.*

class Register : AppCompatActivity() {
    private val PICK_IMAGE = 3000
    private var callbackManager: CallbackManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setImageViewUser()
        setButtonNext()
        getDataFacebook()
        LoginManager.getInstance().logOut()
    }

    private fun setButtonNext() {
        btnSubmit.setOnClickListener {
            val validEmail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"
            val email = edtEmail.text.toString()
            val matcher = Pattern.compile(validEmail).matcher(email)
            if (matcher.matches()) {
                Toast.makeText(applicationContext, "True", Toast.LENGTH_LONG).show(); } else {
                Toast.makeText(applicationContext, "Fail", Toast.LENGTH_LONG).show(); }
        }
    }

    private fun setImageViewUser() {
        imageView.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_PICK
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
        }
    }

    private fun getDataFacebook() {
        btnFacebook.setOnClickListener {
            callbackManager = CallbackManager.Factory.create()
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
            LoginManager.getInstance().registerCallback(callbackManager,
                    object : FacebookCallback<LoginResult> {
                        override fun onSuccess(loginResult: LoginResult) {
                            btnFacebook.visibility = GONE
                            textOR.visibility = GONE
                            val request = GraphRequest.newMeRequest(
                                    loginResult.accessToken
                            ) { `object`, _ ->   Log.e("CheckEmail", `object`.has("email").toString())
                            val name = `object`.getString("name")
                                edtName.setText(name)
                                var email =`object`.getString("email")
                                edtEmail.setText(email)
                            }
                            val parameters = Bundle()
                            parameters.putString("fields", "id,name,link,email")
                            request.parameters = parameters
                            request.executeAsync()
                        }
                        override fun onCancel() {}
                        override fun onError(error: FacebookException) {}
                    })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE) {
            val picUri = data.data
            imageView.setImageURI(picUri)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LoginManager.getInstance().logOut()
    }

}





