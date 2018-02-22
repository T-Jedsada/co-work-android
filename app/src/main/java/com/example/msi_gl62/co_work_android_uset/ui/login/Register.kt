package com.example.msi_gl62.co_work_android_uset.ui.login
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.msi_gl62.co_work_android_uset.R
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*
import java.util.regex.Pattern

class Register : AppCompatActivity() {
    val PICK_IMAGE = 1000
    private var callbackManager: CallbackManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setImageviewUser()
        setButtonNext()
        getDataFacebook()


    }



    private fun setButtonNext() {
        btnSubmit.setOnClickListener {
            val validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"
            val email = edtEmail.text.toString()
            val matcher = Pattern.compile(validemail).matcher(email)
            if (matcher.matches()){ Toast.makeText(getApplicationContext(),"True",Toast.LENGTH_LONG).show(); }
            else { Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_LONG).show(); }
        } }

    private fun setImageviewUser() {
        imageView.setOnClickListener{
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_PICK
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
        } }

    private fun getDataFacebook() {

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
                                            e.printStackTrace() }
                                    }
                                }).executeAsync()

                    }
                    override fun onCancel() {}
                    override fun onError(exception: FacebookException) {}
                })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

            if (requestCode == PICK_IMAGE) {
                val picUri = data.data
                Log.e("twtetst","........"+picUri)
                imageView.setImageURI(picUri)
            }

    }

}





