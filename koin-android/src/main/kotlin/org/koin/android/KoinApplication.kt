package org.koin.android

import android.app.Application
import org.koin.Context
import org.koin.Koin
import kotlin.reflect.KClass

/**
 * Android Application with Koin Context
 */
open class KoinApplication(val clazz: KClass<out AndroidModule>) : Application() {

    lateinit var koin: Context

    override fun onCreate() {
        super.onCreate()

        koin = Koin()
                .init(this)
                .build(clazz)
    }

}