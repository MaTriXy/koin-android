package org.koin.android

import android.app.Application
import org.koin.Koin

/**
 * init android Application - for Koin koin
 */
fun Koin.init(application: Application): Koin {
    context.provide { application }
    return this
}