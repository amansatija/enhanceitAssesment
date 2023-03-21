/*
 * Copyright (C) 2022, amansatija
 * All rights reserved.
 */
package com.example.enhanceittechincaltest.di.providers

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import com.example.enhanceittechincaltest.di.providers.ThemeProvider

class ThemeProviderImpl : ThemeProvider {
    override fun isDarkTheme(context: Context): Boolean {
        return context.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }

    override fun isLightTheme(context: Context): Boolean {
        return !isDarkTheme(context)
    }

    override fun setNightMode(forceNight: Boolean) {
        val mode = if (forceNight) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}