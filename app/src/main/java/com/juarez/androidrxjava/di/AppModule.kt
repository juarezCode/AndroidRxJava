package com.juarez.androidrxjava.di

import com.juarez.androidrxjava.api.UserApi
import com.juarez.androidrxjava.users.data.UserRepository
import com.juarez.androidrxjava.users.data.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideUserRepository(defaultScheduler: Scheduler, userApi: UserApi): UserRepository {
        return UserRepositoryImpl(userApi, defaultScheduler)
    }

    @Singleton
    @Provides
    fun provideDefaultScheduler(): Scheduler {
        return Schedulers.io()
    }
}