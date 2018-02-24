package com.example.msigl62.coworkandroiduset.ui.register
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.CursorLoader
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View.GONE
import android.widget.Toast
import com.example.msi_gl62.co_work_android_uset.R
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*
import android.view.View.OnFocusChangeListener

class RegisterActivity : AppCompatActivity(), RegisterContact.View {
    companion object {
        const val REQUEST_CODE = 1
    }

    private lateinit var presenter: RegisterContact.Presenter
    private var callbackManager: CallbackManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setImageViewUser()
        setButtonSubmitRegister()
        getDataFacebook()
        setonFocusChangeListener()
        presenter = RegisterPresenter()
        LoginManager.getInstance().logOut()

    }

    private fun setonFocusChangeListener() {
        edt_Name.onFocusChangeListener = OnFocusChangeListener { _, _ ->
            if(edt_Name.text.length>30){
                edt_Name.error = "nameerror"
            } }
        edt_Password.onFocusChangeListener = OnFocusChangeListener { _, _ ->
            if(edt_Password.text.length!=6){
                edt_Password.error = "passerror"
            } }
        edt_re_Password.onFocusChangeListener = OnFocusChangeListener { _, _ ->
            if(edt_re_Password.text.length!=6){
                edt_re_Password.error = "passerror"
            } }
    }

    override fun contactPresenter(Description: String) {
        Toast.makeText(applicationContext, Description, Toast.LENGTH_LONG).show(); }

    private fun setButtonSubmitRegister() {
        btnSubmit.setOnClickListener {
            val model=RegisterContactModel(edt_Name.text.toString(),edt_Email.text.toString()
                    ,edt_Password.text.toString(),edt_re_Password.text.toString())
            presenter.checkEdiText(model, this)
        }
    }

    private fun setImageViewUser() {
        imageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_CODE)
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
                            ) { `object`, _ ->
                                Log.e("CheckEmail", `object`.has("email").toString())
                                val name = `object`.getString("name")
                                edt_Name.setText(name)
                                val email = `object`.getString("email")
                                edt_Email.setText(email)
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

    @Suppress("DEPRECATED_IDENTITY_EQUALS")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode === Activity.RESULT_OK) {
            if (requestCode === REQUEST_CODE) {
                val imageUri = data?.data.let { it }
                setImageView(imageUri)
                val fileImage = File(imageUri?.let { getPath(it) })
                val requestFileImage = RequestBody.create(MediaType.parse("multipart/form-data"), fileImage)
                val bodyPartImage = MultipartBody.Part.createFormData("image", fileImage.name, requestFileImage)
                Log.e("testtsts","testtsts"+bodyPartImage)

                //todo call function that api here
            }
        }
    }

    private fun setImageView(imageUri: Uri?) {
        imageView.setImageURI(imageUri)
    }

    override fun onDestroy() {
        super.onDestroy()
        LoginManager.getInstance().logOut()
    }

    private fun getPath(contentUri: Uri): String? {
        val arrData = arrayOf(MediaStore.Images.Media.DATA)
        val loader = CursorLoader(applicationContext, contentUri, arrData, null, null, null)
        val cursor = loader.loadInBackground()
        val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor?.moveToFirst()
        val result = columnIndex?.let { cursor.getString(it) }
        cursor?.close()
        return result
    }
}