package com.example.enhanceittechincaltest.utils.core.config

abstract class CoreConfig {
    abstract fun appName(): String

//    abstract fun environment(): CoreEnvironment
//
//    abstract fun baseUrl(): String

    abstract fun timeOut(): Long

    open fun isDev() = false

    open fun isSessionLoggerEnable() = false

    open fun isNetworkLoggerEnabled() = false

    open fun isSslPinningEnabled() = true

    open fun uncaughtExceptionPage(): Class<*>? = null

    open fun uncaughtExceptionMessage(): String? = null
}