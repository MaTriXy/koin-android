package android.app

import org.koin.Context
import org.koin.android.KoinApplication

/**
 * Return Koin context from Application
 */
fun Application.getKoin(): Context {
    if (this is KoinApplication) {
        return this.koin
    } else throw IllegalStateException("Application is not a KoinApplication !")
}

/**
 * Return Koin context from Activity
 */
fun Activity.getKoin(): Context {
    if (this.application is KoinApplication) {
        return this.application.getKoin()
    } else throw IllegalStateException("Application is not a KoinApplication !")
}

/**
 * Return Koin context from Fragment
 */
fun Fragment.getKoin(): Context {
    if (this.activity.application is KoinApplication) {
        return this.activity.getKoin()
    } else throw IllegalStateException("Application is not a KoinApplication !")
}