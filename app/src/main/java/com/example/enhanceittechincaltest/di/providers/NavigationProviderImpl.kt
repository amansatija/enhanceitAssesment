/*
 * Copyright (C) 2022, amansatija
 * All rights reserved.
 */
package com.example.enhanceittechincaltest.di.providers

import android.content.Context
import android.content.Intent
import com.example.enhanceittechincaltest.MainActivity
import com.example.enhanceittechincaltest.utils.core.extensions.launchActivity


class NavigationProviderImpl(private val context: Context) : NavigationProvider {
    override fun launchMainActivity() {
        context.launchActivity<MainActivity> {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }

//    override fun launchDetailFragment(fragment: Fragment, detailId: Int) {
//        val detailFragment = DetailFragment.newInstance(detailId = detailId)
//        fragment.navigateFragment(fragment = detailFragment, animation = AnimationType.DEFAULT)
//    }
}