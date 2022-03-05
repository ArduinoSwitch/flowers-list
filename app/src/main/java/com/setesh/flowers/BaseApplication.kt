package com.setesh.flowers

import android.app.Application
import com.setesh.commons.di.commonsModule
import com.setesh.data.di.dataModule
import com.setesh.data.network.di.apiModule
import com.setesh.domain.di.domainModule
import com.setesh.flowers.feature.detail.di.detailModule
import com.setesh.flowers.feature.main.di.mainModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class BaseApplication: Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        setupLogger()
        setupDi()
    }

    private fun setupDi() {
        startKoin {
            androidContext(this@BaseApplication)
            commonsModule.single { applicationScope }
            modules(
                listOf(
                    commonsModule,
                    mainModule,
                    detailModule,
                    apiModule,
                    dataModule,
                    domainModule,
                )
            )
        }
    }

    private fun setupLogger() {
        if (BuildConfig.DEBUG) Timber.plant(LineNumberDebugTree())
    }
}

private class LineNumberDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return "(${element.fileName}:${element.lineNumber})#${element.methodName}"
    }
}
