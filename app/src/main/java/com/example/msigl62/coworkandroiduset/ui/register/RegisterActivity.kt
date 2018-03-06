package com.example.msigl62.coworkandroiduset.ui.register

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import android.widget.Toast
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.extension.getPath
import com.example.msigl62.coworkandroiduset.extension.load
import com.example.msigl62.coworkandroiduset.model.Register
import com.example.msigl62.coworkandroiduset.ui.login.LoginActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*

@Suppress("DEPRECATION")
class RegisterActivity : AppCompatActivity(), RegisterContact.View {
    private var idFacebook: String? = null
    private var imageBodyPartImage: MultipartBody.Part? = null
    private var imagePathFacebook: String? = null
    private lateinit var presenter: RegisterContact.Presenter
    private var callbackManager: CallbackManager? = null
    private var loadingDialog: ProgressDialog? = null

    companion object {
        const val REQUEST_CODE = 1
    }

    override fun onResponseFromApi(resMessage: String) {
        loadingDialog?.dismiss()
        val simpleAlert = AlertDialog.Builder(this).create()
        simpleAlert.setTitle(resMessage)
        simpleAlert.setMessage("Verify Email")
        simpleAlert.setCancelable(false)
        simpleAlert.setCanceledOnTouchOutside(false)
        simpleAlert.setButton(AlertDialog.BUTTON_POSITIVE, "Enter", { _, _ ->
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        })
        simpleAlert.show()
    }

    override fun onSuccessValidated(model: Register) {
        loadingDialog = ProgressDialog.show(this,
                "Loading",
                "Loading...",
                true,
                false
        )
        presenter.requestValidateApi(model)
        btnSubmit.isClickable = false
        btnFacebook.isClickable = false
    }

    override fun onErrorMessage(err: Int) {
        Toast.makeText(this, applicationContext.getText(err), Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setImageViewUser()
        setButtonSubmitRegister()
        getIntentFromLoginActivity()
        setToolBar()
        presenter = RegisterPresenter(this)
        LoginManager.getInstance().logOut()
        edt_re_Password.hint = "Re-Password"
        edt_Password.hint = "Password"
    }

    private fun getIntentFromLoginActivity() {
        val status = intent.extras!!.getString("keyStatusFormLoginActivity")
        if (status == "false") {
            getDataFacebook()
        } else {
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setToolBar() {
        text_toolbar.text = getString(R.string.login_header)
        image_arrow.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

    private fun setButtonSubmitRegister() {
        btnSubmit.setOnClickListener {
            val model = imageBodyPartImage?.let { it1 ->
                Register(idFacebook, edt_Name.text.trim().toString(), edt_Email.text.trim().toString()
                        , edt_Password.text.trim().toString(), edt_re_Password.text.trim().toString(), imagePathFacebook, it1) }

            imageBodyPartImage?.let { model?.let { it1 -> presenter.checkEdiText(it1) } }
                    ?: Toast.makeText(applicationContext, "Please upload image", Toast.LENGTH_SHORT).show() }

        btnFacebook.setOnClickListener { getDataFacebook() }
    }

    private fun setImageViewUser() {
        imageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_CODE) }
    }

    private fun getDataFacebook() {
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
        LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        btnFacebook.visibility = GONE
                        textOR.visibility = GONE
                        textUploadImage.visibility = GONE
                        val request = GraphRequest.newMeRequest(
                                loginResult.accessToken
                        ) { `object`, _ ->
                            val name = `object`.getString("name")
                            edt_Name.setText(name)
                            val email: Boolean? = `object`.has("email")
                            if (email == false) {
                                Toast.makeText(applicationContext, "NoEmail", Toast.LENGTH_LONG).show()
                            } else {
                                edt_Email.setText(`object`.getString("email"))
                            }
                            idFacebook = `object`.getString("id")
                            val profilePicUrl = `object`.getJSONObject("picture").getJSONObject("data").getString("url")
                            imageView.load(profilePicUrl)
                            val bodyPartImage = MultipartBody.Part.createFormData(profilePicUrl, profilePicUrl)
                            imageBodyPartImage = bodyPartImage
                            imagePathFacebook = profilePicUrl

                        }
                        val parameters = Bundle()
                        parameters.putString("fields", "id,name,link,email,picture.type(large)")
                        request.parameters = parameters
                        request.executeAsync()
                    }

                    override fun onCancel() {}
                    override fun onError(error: FacebookException) {}
                })
    }

    @Suppress("DEPRECATED_IDENTITY_EQUALS")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode === Activity.RESULT_OK) {
            if (requestCode === REQUEST_CODE) {
                val imageUri = data?.data.let { it }
                setImageView(imageUri)
                val fileImage = File(imageUri?.getPath(this))
                val requestFileImage = RequestBody.create(MediaType.parse("multipart/form-data"), fileImage)
                val bodyPartImage = MultipartBody.Part.createFormData("image", fileImage.name, requestFileImage)
                imageBodyPartImage = bodyPartImage
                textUploadImage.visibility = GONE
            } }
    }

    private fun setImageView(imageUri: Uri?) {
        imageView.setImageURI(imageUri) }

    override fun onDestroy() {
        super.onDestroy()
        LoginManager.getInstance().logOut() }

    override fun onBackPressed() {
        val simpleAlert = AlertDialog.Builder(this).create()
        simpleAlert.setTitle("Exit")
        simpleAlert.setMessage("Register")
        simpleAlert.setButton(AlertDialog.BUTTON_POSITIVE, "Enter", { _, _ ->
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        })
        simpleAlert.show() }
}