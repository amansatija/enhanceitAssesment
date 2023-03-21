/*
 * Copyright (C) 2022, amansatija
 * All rights reserved.
 */
package com.example.enhanceittechincaltest.di.providers

import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes id: Int): String
}