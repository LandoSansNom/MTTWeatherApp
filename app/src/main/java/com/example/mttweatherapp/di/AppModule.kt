package com.example.mttweatherapp.di


import com.example.mttweatherapp.data.remote.ApiCalls
import com.example.mttweatherapp.data.remote.ApiDetails.Companion.BASE_URL
import com.example.mttweatherapp.data.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideWeatherRepository(
        api: ApiCalls
    ) = WeatherRepository(api)

    @Singleton
    @Provides
    fun provideWeatherApi(): ApiCalls {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiCalls::class.java)
    }
}