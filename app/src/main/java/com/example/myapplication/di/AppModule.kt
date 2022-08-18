package com.example.myapplication.di

import com.example.myapplication.api.BlogApi
import com.example.myapplication.detail.DetailRepository
import com.example.myapplication.detail.DetailRepositoryImpl
import com.example.myapplication.edit.EditRepository
import com.example.myapplication.edit.EditRepositoryImpl
import com.example.myapplication.main.repository.MainRepository
import com.example.myapplication.main.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
       return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideBlogApi(retrofit : Retrofit) : BlogApi{
        return retrofit.create(BlogApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMainRepository(retrofit : Retrofit) : MainRepository{
        return MainRepositoryImpl(retrofit.create(BlogApi::class.java))
    }

    @Singleton
    @Provides
    fun provideDetailRepository(retrofit : Retrofit) : DetailRepository{
        return DetailRepositoryImpl(retrofit.create(BlogApi::class.java))
    }

    @Singleton
    @Provides
    fun provideEditRepository(retrofit : Retrofit) : EditRepository {
        return EditRepositoryImpl(retrofit.create(BlogApi::class.java))
    }



}