package com.example.msigl62.coworkandroiduset.base

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

@Suppress("DEPRECATION")
abstract class BaseActivity<V : BaseContract.View, P : BaseContract.Presenter<V>> : AppCompatActivity(), BaseContract.View {

    protected abstract fun layoutToInflate(): Int
    protected abstract fun setUpView()
    protected abstract fun setUpToolBar()
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutToInflate())
        setUpView()
        setUpToolBar()
    }

    protected fun showDialog(title :String, message: String){
        progressDialog = ProgressDialog.show(this, title, message,true,false)
    }

    protected fun loadingSuccess(){
        progressDialog.dismiss()
    }
}