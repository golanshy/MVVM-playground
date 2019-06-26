package uk.co.applylogic.playground.mvvm.di

import dagger.Module
import dagger.Provides
import uk.co.applylogic.playground.mvvm.application.MyApp
import javax.inject.Singleton

/**
 * Created by Golan Shay @golanshy on 26 June,2019
 */
@Module
class AppModule(val app: MyApp) {
    @Provides
    @AppScope
    @ApplicationContext
    fun provideApp() = app
}