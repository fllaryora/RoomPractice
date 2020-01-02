package com.example.roompractice.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass
import kotlin.annotation.MustBeDocumented


/**
 * It's a annotation that's going to carry keys on.
 * To tell dagger what the keys are The programmer adds the notation [MapKey]
 */
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

/**
 * This
 * KClass<out ViewModel>
 *     means
 * Some class that extend viewModel
*/