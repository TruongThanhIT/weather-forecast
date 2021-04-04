package com.thanht.weather.base

import android.app.Dialog
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.thanht.weather.common.ProgressDialog
import javax.inject.Inject
import kotlin.reflect.KClass
import kotlin.isInitialized as isInitialized1

abstract class BaseFragment<B : ViewDataBinding, VM : ViewModel>(
    @LayoutRes private val layoutId: Int,
    viewModelClass: KClass<VM>
) : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    val mViewModel: VM by createViewModelLazy(viewModelClass, { viewModelStore }, { factory })

    val navController: NavController by lazy { findNavController() }

    open lateinit var mBinding: B

    private val progressDialog: Dialog by lazy { ProgressDialog(requireContext()) }

    abstract fun B.initViews()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!::mBinding.isInitialized1)
            mBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.initViews()
    }

    protected fun showProgressDialog(show: Boolean, isCancel: Boolean = true) {
        progressDialog.setCancelable(isCancel)
        if (show) progressDialog.show()
        else progressDialog.dismiss()
    }

    protected fun getColor(@ColorRes res: Int): Int {
        return ContextCompat.getColor(requireContext(), res)
    }

    protected fun getDrawable(@DrawableRes res: Int): Drawable? {
        return ContextCompat.getDrawable(requireContext(), res)
    }

    protected fun getDimension(@DimenRes res: Int): Int {
        return requireContext().resources.getDimensionPixelSize(res)
    }

}
