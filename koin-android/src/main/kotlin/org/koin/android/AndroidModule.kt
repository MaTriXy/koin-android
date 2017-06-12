package org.koin.android

import android.app.Application
import android.content.res.AssetManager
import android.content.res.Resources
import org.koin.module.Module

/**
 * Koin module with Android facilities
 */
abstract class AndroidModule : Module() {

    val applicationContext: Application by lazy { context.get<Application>() }

    val resources: Resources by lazy { applicationContext.resources }

    val assets: AssetManager by lazy { applicationContext.assets }

}