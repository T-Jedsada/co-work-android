package com.example.msi_gl62.co_work_android_uset.ui.login
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.widget.Toast
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.ui.login.Contact
import com.example.msigl62.coworkandroiduset.ui.login.Model
import com.example.msigl62.coworkandroiduset.ui.login.Presenter
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*
class Register : AppCompatActivity(), Contact.view  {
    lateinit var presenter: Contact.presenter
    private val PICK_IMAGE = 3000
    private var callbackManager: CallbackManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setImageViewUser()
        setButtonNext()
        getDataFacebook()
        presenter = Presenter()
        LoginManager.getInstance().logOut()
    }

    override fun contactPresenter(Description: String) {
        Toast.makeText(applicationContext, Description, Toast.LENGTH_LONG).show(); }

    private fun setButtonNext() {
        btnSubmit.setOnClickListener {
            val model = Model(edtEmail.text.toString())
            presenter.checkMatcherEmail(model, this) }
    }

    private fun setImageViewUser() {
        imageView.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_PICK
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
        } }

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
                    }) } }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE) {
            val picUri = data.data
            imageView.setImageURI(picUri)
        } }

    override fun onDestroy() {
        super.onDestroy()
        LoginManager.getInstance().logOut() }

}





