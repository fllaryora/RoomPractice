package com.example.roompractice.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.roompractice.BuildConfig
import com.example.roompractice.R
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton



/**
 * [AppModule] class is responsible for providing application level dependencies
 *
 * Anotated with singleton annotation to tell dagger these dependencies also exists
 * when [AppComponent] alive and destroy these dependencies when [AppComponent] destroy
 */
@Module
class AppModule {

    /**
     * internal es el equivalente al static en java para dagger.
     * La funcion manejara los placeholders y los errores de network cuando glide haga llamadas al
     * repo de imagenes.
     */
    @Singleton
    @Provides
    internal fun provideRequestOptions(): RequestOptions {
        return RequestOptions
            .placeholderOf(R.drawable.white_background)
            .error(R.drawable.white_background)
    }

    @Singleton
    @Provides
    internal fun provideGlideInstance(application: Application, requestOptions: RequestOptions) :
            RequestManager {
        return Glide.with(application).setDefaultRequestOptions(requestOptions)
    }

    @Singleton
    @Provides
    internal fun provideAppDrawable(application: Application): Drawable {
        return ContextCompat.getDrawable(application, R.drawable.logo)!!
    }

    /**
     * retrofit para TODOS.
     */
    @Singleton
    @Provides
    internal fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}