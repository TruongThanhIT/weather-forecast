package com.thanht.weather.util

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorInt

fun Activity?.changeStatusBar(
    fullLayout: Boolean = false,
    lightBackground: Boolean = false,
    @ColorInt color: Int = Color.TRANSPARENT
) {
    val window = this?.window ?: return
    window.apply {
        clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_IMMERSIVE.or(View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        if (fullLayout) {
            decorView.systemUiVisibility = decorView.systemUiVisibility
                .or(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        }
        if (lightBackground) {
            decorView.systemUiVisibility = decorView.systemUiVisibility
                .or(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        }
        statusBarColor = color
    }
}

fun View?.hideSoftKeyboard() {
    this ?: return
    val inputMethod = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethod.hideSoftInputFromWindow(windowToken, 0)
}

fun View?.showSoftKeyboard() {
    this ?: return
    val inputMethod = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethod.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}