package com.example.msigl62.coworkandroiduset.base
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity <V : BaseContract.View, P : BaseContract.Presenter<V>> :
        AppCompatActivity(), BaseContract.View {

    protected abstract fun layoutToInflate(): Int
    protected abstract fun setUpView()
    protected abstract fun setUpBottomBar()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutToInflate())
        setUpView()
        setUpBottomBar()
    }
}