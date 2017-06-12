package koin.sampleapp

import com.joanzapata.iconify.Iconify
import com.joanzapata.iconify.fonts.WeathericonsModule
import koin.sampleapp.koin.MyModule
import org.koin.android.KoinApplication

class MainApplication : KoinApplication(MyModule::class) {

    override fun onCreate() {
        super.onCreate()

        Iconify.with(WeathericonsModule())
    }
}
