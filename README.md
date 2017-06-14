# KOIN - Simple KOtlin dependency INjection framework for Android

KOIN is a simple dependency injection framework that use Kotlin and its functional power to get it done!  No Proxy, No code generation, No introspection. Just functional Kotlin and DSL ;)

Koin-Android is based on [Koin](https://github.com/Ekito/koin/), the main library of Koin framework.

**Insert Koin to inject your app :)**

## Gradle Setup

Check that you have jcenter repository and add the following gradle dependency:

```gradle
repositories {
        jcenter()
}

compile 'org.koin:koin-android:0.1.0'

```

## Getting Started for Android

First of all, you need to write a module to gather your components definitions.

### Writing your Android Module

Write a class that extends [AndroidModule](https://github.com/Ekito/koin-android/blob/master/koin-android/src/main/kotlin/org/koin/android/AndroidModule.kt) like below:

```Kotlin
class MyModule : AndroidModule() {
    override fun onLoad() {
        declareContext {
            provide { createClient() }
            provide { retrofitWS(get(), resources.getString(R.string.server_url)) }
            provide { WeatherService(get()) }
        }
        //...
    }
}

```
Your must open declaration section in the `onLoad()` method, with the `declareContext` function.  

Each `provide` function helps you to declare your component. Each lambda must return an expression for your component creation. e.g: `createClient()` return an OkHttpClient. You can also directly instantiate your class like `WeatherService`.

Inject your component constructor with `get()` method, which will resolve the needed dependency. 

AndroidModule also gives you the possibility to retrieve your ApplicationContext, Resources & Assets directly in your module context. e.g: I can get an Android string with `resources.getString(R.string.server_url)`

### Setup your Android Application

To start your Koin module, you must build it: `Koin().init(*applicationInstance*).build(MyModule::class)`. This will return a context on which you will get your components isntances.

On Android, we can make it on our Application class, but it will be tricky to retrieve your Koin context from Android. Koin proposes a [KoinApplication](https://github.com/Ekito/koin-android/blob/master/koin-android/src/main/kotlin/org/koin/android/KoinApplication.kt) and a [KoinMultiDexApplication](https://github.com/Ekito/koin-android/blob/master/koin-android/src/main/kotlin/org/koin/android/KoinMultiDexApplication.kt) to help easily setup your context & get it from everywhere (thanks to [Kotlin extensions](https://github.com/Ekito/koin-android/blob/master/koin-android/src/main/kotlin/android/app/AndroidExt.kt)):

```Kotlin
class MainApplication : KoinApplication(MyModule::class) {

    override fun onCreate() {
        super.onCreate()
        //...
    }
}

```

By using these KoinApplication class, you will be able to use the Koin Android extensions below.


### Just get Koin

You can get the Koin context from anywhere (Application, Activity, Fragment), by using the `getKoin()` method extension. Below a way of lazy injection dependency in an Activity:

```Kotlin
class MainActivity : AppCompatActivity() {

    val weatherService by lazy { getKoin().get<WeatherService>() }
    
    //...
}
```

**No need of special method in your android component lifecycle.** Just use `getKoin().get<YourComponent>()`to retrieve your component.

### More features

Check the [Koin core project](https://github.com/Ekito/koin/) for the advanced features.

