package com.latinos.data.preference

import android.content.Context
import com.latinos.data.di.MoshiDefault
import com.squareup.moshi.Moshi
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPrefs @Inject constructor(
    private val spm: SharedPrefManager,
    @ApplicationContext private val context: Context,
    @MoshiDefault private val moshi: Moshi,
) {
    fun setDarkModeSetting(darkMode: Int) {
        spm.setIntValue(SHARED_PREF_DARK_MODE, darkMode)
    }

    fun getDarkModeSetting() = spm.getIntValue(SHARED_PREF_DARK_MODE)

    fun setThemeSetting(darkMode: Int) {
        spm.setIntValue(SHARED_PREF_THEME, darkMode)
    }

    fun getThemeSetting() = spm.getIntValue(SHARED_PREF_THEME)

    companion object {
        const val SHARED_PREF_DARK_MODE = "SHARED_PREF_DARK_MODE"
        const val SHARED_PREF_THEME = "SHARED_PREF_THEME"
    }
}
