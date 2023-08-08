package com.example.mttweatherapp.di


import com.example.mttweatherapp.data.remote.ApiCalls
import com.example.mttweatherapp.data.remote.ApiDetails.Companion.BASE_URL
import com.example.mttweatherapp.data.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpInstance(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherApi(
        client: OkHttpClient
    ): ApiCalls {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(ApiCalls::class.java)
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(api: ApiCalls): WeatherRepository {
        return WeatherRepository(api)
    }
}