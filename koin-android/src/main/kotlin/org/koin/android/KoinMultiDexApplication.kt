package org.koin.android

import android.support.multidex.MultiDexApplication
import org.koin.Context
import org.koin.Koin
import kotlin.reflect.KClass

/**
 * Android Application with Koin Context
 */
open class KoinMultiDexApplication(val clazz: KClass<out AndroidModule>) : MultiDexApplication() {

    lateinit var koin: Context

    override fun onCreate() {
        super.onCreate()

        koin = Koin()
                .init(this)
                .build(clazz)
    }

}