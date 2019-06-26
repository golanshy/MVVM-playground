package uk.co.applylogic.playground.mvvm.di

import dagger.Component
import uk.co.applylogic.playground.mvvm.application.MyApp
import uk.co.applylogic.playground.mvvm.networking.APIInterface
import uk.co.applylogic.playground.mvvm.networking.RetrofitModule
import uk.co.applylogic.playground.mvvm.view.MainActivity

/**
 * Created by Golan Shay @golanshy on 26 June,2019
 */

@AppScope
@Component(modules = [AppModule::class, RetrofitModule::class])
interface AppComponent {
    fun inject(application: MyApp)
    fun inject(activity: MainActivity)
    fun getApiInterface() : APIInterface
}