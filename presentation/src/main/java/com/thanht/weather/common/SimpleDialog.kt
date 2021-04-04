package com.thanht.weather.common

import android.content.Context
import android.view.Gravity
import androidx.annotation.DrawableRes
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import com.thanht.weather.R
import com.thanht.weather.databinding.DialogSimpleBinding
import com.thanht.weather.util.setSafeOnClickListener

class SimpleDialog private constructor(
    context: Context,
    lifecycleOwner: LifecycleOwner? = null
) : BaseDialog<DialogSimpleBinding>(context, lifecycleOwner, R.layout.dialog_simple) {

    companion object {
        fun create(context: Context, lifecycleOwner: LifecycleOwner? = null): SimpleDialog {
            return SimpleDialog(context, lifecycleOwner)
        }
    }

    @DrawableRes
    private var imageRes: Int = -1
    fun setImageResource(@DrawableRes res: Int = -1) = apply {
        imageRes = res
    }

    private var title: String? = null
    fun setTitle(title: String?) = apply {
        this.title = title
    }

    private var content: String? = null
    fun setContent(content: String) = apply {
        this.content = content
    }

    private var positiveLabel: String? = null
    fun setPositiveLabel(label: String) = apply {
        positiveLabel = label
    }

    private var positiveListener: () -> Unit = {}
    fun setPositiveListener(listener: () -> Unit = {}) = apply {
        positiveListener = listener
    }

    private var negativeLabel: String? = null
    fun setNegativeLabel(label: String) = apply {
        negativeLabel = label
    }

    private var negativeListener: () -> Unit = {}
    fun setNegativeListener(listener: () -> Unit = {}) = apply {
        negativeListener = listener
    }

    private var enableCancel: Boolean = true
    fun enableCancel(cancelable: Boolean) = apply {
        this.enableCancel = cancelable
    }

    private var dismissOnTouchOutside: Boolean = true
    fun dismissOnTouchOutside(dismissOnTouchOutside: Boolean) = apply {
        this.dismissOnTouchOutside = dismissOnTouchOutside
    }

    override fun DialogSimpleBinding.initViews() {
        if (imageRes != -1) {
            ivImage.setImageResource(imageRes)
        } else {
            ivImage.isVisible = false
            tvTitle.gravity = Gravity.START
            tvContent.gravity = Gravity.START
        }

        mBinding.tvTitle.run {
            isVisible = !title.isNullOrBlank()
            text = title
        }

        mBinding.tvContent.run {
            isVisible = !content.isNullOrBlank()
            text = content
        }

        with(btnPositive) {
            val hasPositive = !positiveLabel.isNullOrBlank()
            isVisible = hasPositive
            if (hasPositive) {
                text = positiveLabel
                setSafeOnClickListener {
                    dismiss()
                    positiveListener.invoke()
                }
            }
        }

        with(btnNegative) {
            val hasNegative = !negativeLabel.isNullOrBlank()
            isVisible = hasNegative
            if (hasNegative) {
                text = negativeLabel
                setSafeOnClickListener {
                    dismiss()
                    negativeListener.invoke()
                }
            }
        }

        setCancelable(enableCancel)
        setCanceledOnTouchOutside(dismissOnTouchOutside)
    }

}
