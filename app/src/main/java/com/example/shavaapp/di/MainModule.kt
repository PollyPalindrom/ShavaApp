package com.example.shavaapp.di

import android.content.Context
import com.example.shavaapp.common.GetMacAddressManager
import com.example.shavaapp.common.VerificationManager
import com.example.shavaapp.data.database.DatabaseDataSource
import com.example.shavaapp.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    @Singleton
    fun provideRepo(
        databaseDataSource: DatabaseDataSource,
        verificationManager: VerificationManager,
        getMacAddressManager: GetMacAddressManager
    ): Repository {
        return Repository(databaseDataSource, verificationManager, getMacAddressManager)
    }

    @Provides
    @Singleton
    fun provideVerificationManager(): VerificationManager {
        return VerificationManager()
    }

    @Provides
    @Singleton
    fun provideDatabaseDataSource(): DatabaseDataSource {
        return DatabaseDataSource()
    }

    @Provides
    @Singleton
    fun provideGetMacAddressManager(): GetMacAddressManager {
        return GetMacAddressManager()
    }
}