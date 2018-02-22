package com.example.msi_gl62.co_work_android_uset.ui.login
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.msi_gl62.co_work_android_uset.R
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_register.*
import android.app.Activity

class Register : AppCompatActivity() {
    val PICK_IMAGE = 1000
    private var callbackManager: CallbackManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        getDataFacebook()
        setImageviewUser()
    }

    private fun setImageviewUser() {
        imageView.setOnClickListener{

            val galleryIntent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, PICK_IMAGE)
        } }


    private fun getDataFacebook() {
        callbackManager = CallbackManager.Factory.create()
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        val params = Bundle()
                        params.putString("fields", "id,email,name,gender")
                        GraphRequest(AccessToken.getCurrentAccessToken(), "me", params, HttpMethod.GET,
                                GraphRequest.Callback { response ->
                                    val data = response.jsonObject
                                    if (response != null) {
                                        try {
                                            val textname=data.getString("name")
                                            edtName.setText(textname)
                                            val textid=data.getString("id")
                                            Log.e("textid","......"+textid)
                                            val textemail=data.getString("email")
                                            edtName.setText(textemail)
                                        } catch (e: Exception) {
                                            e.printStackTrace() } }
                                    Log.e("teststst","sdsdsd"+data)
                                }).executeAsync()

                    }
                    override fun onCancel() {}
                    override fun onError(exception: FacebookException) {}
                })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode === Activity.RESULT_OK) {
            if (requestCode === PICK_IMAGE) {
                val picUri = data.data
                Log.e("twtetst","........"+picUri)
                imageView.setImageURI(picUri) }
        }
    }

}





