package com.example.msi_gl62.rider.base
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.msi_gl62.buildvariantsandroid.base.BaseContract

abstract class BaseFragment <V : BaseContract.View, P : BaseContract.Presenter<V>> :
        Fragment(), BaseContract.View{

    protected abstract fun layoutToInflate(): Int

    protected  abstract fun setup()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(layoutToInflate(), container, false) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }
}