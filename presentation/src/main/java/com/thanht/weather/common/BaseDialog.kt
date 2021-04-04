package com.thanht.weather.common

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.thanht.weather.R

abstract class BaseDialog<B : ViewDataBinding>(
    context: Context,
    private val lifecycleOwner: LifecycleOwner? = null,
    @LayoutRes private val layoutId: Int,
    @StyleRes styleId: Int = R.style.SimpleDialogTheme
) : Dialog(context, styleId), LifecycleObserver {

    lateinit var mBinding: B

    abstract fun B.initViews()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            layoutId, null, false
        )
        setContentView(mBinding.root)

        lifecycleOwner?.lifecycle?.addObserver(this)

        setOnDismissListener {
            lifecycleOwner?.lifecycle?.removeObserver(this)
        }

        mBinding.initViews()
    }

    override fun onStart() {
        super.onStart()
        window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun dismissDialog() {
        dismiss()
    }
}
