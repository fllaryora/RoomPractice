package com.example.roompractice.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.roompractice.BuildConfig
import com.example.roompractice.R
import com.example.roompractice.data.room.MyDataBase
import com.example.roompractice.data.room.dao.UsersDAO
import com.example.roompractice.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * [AppModule] class is responsible for providing application level dependencies
 *
 * Annotated with singleton annotation to tell dagger these dependencies also exists
 * when [AppComponent] alive and destroy these dependencies when [AppComponent] destroy
 */
@Module
class AppModule {

    /**
     * internal reserved-word is the equivalent to static for java in dagger.
     * This function handles the  placeholders and  errors coming from network
     * when glide do the calls to image repository.
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
     * retrofit for ALL.
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

    /**
     * room for ALL.
     */
    @Singleton
    @Provides
    internal fun provideDatabase(application: Application): MyDataBase {
        return Room.databaseBuilder(application.applicationContext,
            MyDataBase::class.java, Constants.DATABASE_NAME
        ).build()
    }


    /**
     * users for ALL.
     */
    @Singleton
    @Provides
    internal fun provideUsersDAO(database: MyDataBase): UsersDAO {
        return database.usersDAO()
    }

}