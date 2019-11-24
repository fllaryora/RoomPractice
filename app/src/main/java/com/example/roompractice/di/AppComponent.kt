package com.example.roompractice.di

import android.app.Application
import com.example.roompractice.presentation.SessionManager
import com.example.roompractice.presentation.base.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Component is a graph. We build a component. Component will provide injected instances by using modules.
 * Extends appcomponent with [AndroidInjector] to avoid old way of injection application
 *
 * <code>
 *     fun inject(application: BaseApplication)
 * </code>
 *
 * AppComponent is act as a server whereas, [BaseApplication] act as a client.
 * Dagger interaction is like client-server interaction
 *
 * Anotated with [Singleton] Scope to tell dagger to keep it in the memory while application exists
 * and destroy it when application destroy
 *
 * Scopes are a way to give a name to the lifetime of a component.
 * And Then all dependencies that exists inside that component are scoped to that component.
 * So all the dependencies of a particular scoped component will no be recreated anywhere outside of that scope.
 *
 *
 */

@Singleton
@Component (
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilderModule::class,
        AppModule::class,
        ViewModelFactoryModule::class
    ]
)
/**
 * There is a relationship between AppComponent (as server) and BaseApplication (as client).
 * This is the difference with the old way to do dagger.
 */
interface AppComponent : AndroidInjector<BaseApplication> {

    /**
     Overrrides the bilder
     */
    @Component.Builder
    interface Builder {
        /**
         * [BindsInstance] annotation is used for, if you want to bind particular object or instance
         * of an object through the time of component construction
         */
        @BindsInstance
        fun application(application: Application) : Builder

        /**
         * Mandatory Step inside @Component.Builder
         */
        fun build() : AppComponent
    }


    /**
     * Session manager can be access any where in the application
     */
    fun sessionManager() : SessionManager
}