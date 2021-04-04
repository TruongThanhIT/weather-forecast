package com.thanht.weather.common

import android.app.Dialog
import android.content.Context
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.thanht.weather.R


// If request done before 150ms, we no need show loading to smooth Fragment Transition
private const val DELAY_SHOW_LOADING: Long = 150

class ProgressDialog(context: Context) : Dialog(context, R.style.DialogTransparent),
    LifecycleObserver {
    private val loadingAnimation: Animation =
        AnimationUtils.loadAnimation(context, R.anim.anim_progress)
    private val handler = Handler()
    private val runnable = Runnable {
        if (isActivityForeground) {
            show()
        }
    }
    private val ivLoading: ImageView
    private var isActivityForeground: Boolean = true

    init {
        setContentView(R.layout.dialog_progress)
        ivLoading = findViewById(R.id.ivLoading)
    }

    fun close() {
        if (isActivityForeground) {
            handler.removeCallbacks(runnable)
            dismiss()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroyed() {
        handler.removeCallbacks(runnable)
        isActivityForeground = false
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreated() {
        isActivityForeground = true
    }

    override fun onStart() {
        super.onStart()
        ivLoading.startAnimation(loadingAnimation)
    }

    override fun onStop() {
        ivLoading.clearAnimation()
        super.onStop()
    }

    fun setOperationCancelable(cancel: Boolean) {
        setCanceledOnTouchOutside(cancel)
        setCancelable(cancel)
    }

    fun showProgress() {
        if (isActivityForeground) {
            handler.postDelayed(runnable,
                DELAY_SHOW_LOADING
            )
        }
    }
}