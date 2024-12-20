package com.app.focustimer.di

import com.app.focustimer.data.repository.LocalStorageRepositoryImpl
import com.app.focustimer.domain.repository.LocalStorageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * RepositoryModule is a Dagger Hilt module that provides bindings for repositories.
 *
 * This module is installed in the SingletonComponent, meaning the provided bindings will
 * have a singleton scope and will be available throughout the application's lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Binds the LocalStorageRepositoryImpl to the LocalStorageRepository interface.
     *
     * This tells Hilt that when a dependency of type LocalStorageRepository is requested,
     * it should provide an instance of LocalStorageRepositoryImpl.
     *
     * @param localStorageRepositoryImpl The implementation of the LocalStorageRepository.
     * @return The LocalStorageRepository interface.
     */
    @Singleton
    @Binds
    abstract fun bindLocalStorageRepository(localStorageRepositoryImpl: LocalStorageRepositoryImpl): LocalStorageRepository
}