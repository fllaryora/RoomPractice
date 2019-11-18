package com.example.roompractice.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import java.lang.annotation.Documented
import kotlin.reflect.KClass


/**
 * Es una anotacion que va a acarrear keys.
 * Para decirle a dagger que soy las key le agrego la anotacion [MapKey]
 */
@Documented
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

/**
 * Esto
 * KClass<out ViewModel>
 *     significa
 * Some class that extend viewModel
*/