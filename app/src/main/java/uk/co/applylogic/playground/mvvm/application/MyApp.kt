package uk.co.applylogic.playground.mvvm.application

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import uk.co.applylogic.playground.mvvm.di.AppComponent
import uk.co.applylogic.playground.mvvm.di.AppModule
import uk.co.applylogic.playground.mvvm.di.DaggerAppComponent

class MyApp : Application() {

    companion object {
        lateinit var appContext: MyApp
    }

    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        component.inject(this)

        if (!LeakCanary.isInAnalyzerProcess(this)) LeakCanary.install(this)
    }
}