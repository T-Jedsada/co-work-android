package com.example.msi_gl62.rider.base
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.msi_gl62.buildvariantsandroid.base.BaseContract

abstract class BaseActivity <V : BaseContract.View, P : BaseContract.Presenter<V>> :
        AppCompatActivity(), BaseContract.View {

    protected abstract fun layoutToInflate(): Int
    protected abstract fun setupView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutToInflate())
        setupView()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}