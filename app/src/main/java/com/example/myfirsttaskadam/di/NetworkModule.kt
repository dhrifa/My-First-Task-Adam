package com.example.myfirsttaskadam.di

import com.example.myfirsttaskadam.data.remote.ApiDetail
import com.example.myfirsttaskadam.data.remote.ApiReference
import com.example.myfirsttaskadam.data.repository.Repository
import com.example.myfirsttaskadam.data.repository.RepositoryImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Time
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideHttpInterceptor(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideOkHtttpClient(
        loginIntercepter: HttpLoggingInterceptor
    ): OkHttpClient {
        return  OkHttpClient.Builder()
            .addInterceptor(loginIntercepter)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideRetrofit(
        gson: Gson,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiReference.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Provides
    fun provideApiDetails(
        retrofit: Retrofit
    ):ApiDetail{
        return retrofit.create(ApiDetail::class.java)
    }

    @Provides
    fun provideReporitory(
        apiDetail: ApiDetail
    ):Repository{
        return RepositoryImpl(apiDetail)
    }
}