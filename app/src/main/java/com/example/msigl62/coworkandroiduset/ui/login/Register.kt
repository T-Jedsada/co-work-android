package com.example.msi_gl62.co_work_android_uset.ui.login
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.widget.Toast
import com.example.msi_gl62.co_work_android_uset.R
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.Pattern
import com.facebook.FacebookException
import com.facebook.FacebookCallback

class Register : AppCompatActivity() {
    val PICK_IMAGE = 3000
    private var callbackManager: CallbackManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setImageviewUser()
        setButtonNext()
        getDataFacebook()
        LoginManager.getInstance().logOut() }

    private fun setButtonNext() {
        btnSubmit.setOnClickListener {
            val validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"
            val email = edtEmail.text.toString()
            val matcher = Pattern.compile(validemail).matcher(email)
            if (matcher.matches()){ Toast.makeText(getApplicationContext(),"True",Toast.LENGTH_LONG).show(); }
            else { Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_LONG).show(); } } }

    private fun setImageviewUser() {
        imageView.setOnClickListener{
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_PICK
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE) } }

    private fun getDataFacebook() {
        callbackManager = CallbackManager.Factory.create()
        login_button.setReadPermissions("email")
        login_button.registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        val params = Bundle()
                        params.putString("fields", "id,name,gender,email")
                        GraphRequest(AccessToken.getCurrentAccessToken(), "me", params, HttpMethod.GET,
                                GraphRequest.Callback { response -> val data = response.jsonObject
                                    if (response != null) {
                                        try {
                                            val textname=data.getString("name")
                                            edtName.setText(textname)
                                            val textid=data.getString("id")
                                            val textemail=data.getString("email")
                                            edtEmail.setText(textemail)
                                        } catch (e: Exception) {
                                            e.printStackTrace() }
                                    }
                                }).executeAsync()
                        login_button.visibility = GONE
                        textOR.visibility = GONE
                    }
                    override fun onCancel() {}
                    override fun onError(exception: FacebookException) {} }) }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE) {
            val picUri = data.data
            imageView.setImageURI(picUri) } }

    override fun onDestroy() {
        super.onDestroy()
        LoginManager.getInstance().logOut() }

}





