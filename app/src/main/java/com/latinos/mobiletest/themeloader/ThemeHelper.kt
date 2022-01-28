package com.latinos.mobiletest.themeloader

import com.latinos.mobiletest.themeloader.ThemeHelperImpl.Companion.SAVED_NIGHT_VALUE


interface ThemeHelper {
    fun getAppTheme(): Int
    fun getNightMode(darkModeSetting: Int = SAVED_NIGHT_VALUE): Int
}