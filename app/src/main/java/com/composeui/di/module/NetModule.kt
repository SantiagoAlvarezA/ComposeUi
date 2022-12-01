package com.composeui.di.module

import com.composeui.BuildConfig
import com.composeui.data.server.api.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    // .addHeader("Authorization", "Bearer " + pref.getString(Pref.TOKEN))
                    .build()
                val response = chain.proceed(newRequest)

                if (!response.isSuccessful) {
                    response.body?.let {
                        val source = it.source()
                        val charset = it.contentType()?.charset(Charset.forName("UTF-8"))
                        charset?.let {
                            val buffer = source.buffer.readString(charset)
                            //TODO: case errors
                        }
                    }
                }

                response
            }

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }

        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        //val urlBase = String.format(BuildConfig.API_BASE, "/v${BuildConfig.VERSION_NAME}")
        //Timber.e("URL BASE ===>  $urlBase")
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)



}