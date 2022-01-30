package com.latinos.mobiletest.features.base

import android.app.Activity
import android.content.res.Configuration
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar

private fun Activity.isNightModeActive() = resources?.configuration
    ?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

fun Fragment.isNightModeActive() = resources.configuration
    ?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

fun Activity.setTranslucentStatusBar(translucent: Boolean) {
    val flag = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
    val flags = window.attributes.flags

    // guard to prevent multiple calls when flag is already set or cleared
    if (((flags and flag) != 0 && translucent) ||
        ((flags and flag) == 0 && !translucent)
    ) return

    if (translucent) {
        window.setFlags(flag, flag)
        window.setLightStatusBarIcons()
    } else {
        if (isNightModeActive()) {
            window.setLightStatusBarIcons()
        } else {
            window.setDarkStatusBarIcons()
        }
        window.clearFlags(flag)
    }
}

private fun Window.setLightStatusBarIcons() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        insetsController?.setSystemBarsAppearance(
            0,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )
    } else {
        var flags = decorView.systemUiVisibility
        flags = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        decorView.systemUiVisibility = flags
    }
}

private fun Window.setDarkStatusBarIcons() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        insetsController?.setSystemBarsAppearance(
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )
    } else {
        var flags = decorView.systemUiVisibility
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        decorView.systemUiVisibility = flags
    }
}

fun MaterialToolbar?.setUpButtonColor(@ColorInt color: Int) {
    if (this == null) return
    navigationIcon?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
        color,
        BlendModeCompat.SRC_ATOP)
}