package com.app.focustimer.di

import android.content.Context
import androidx.room.Room
import com.app.focustimer.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * DatabaseModule provides dependencies related to the Room database.
 *
 * This module is installed in the SingletonComponent, meaning the provided dependencies will
 * have a singleton scope and will be available throughout the application's lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    /**
     * Provides a singleton instance of the AppDatabase.
     *
     * @param context The application context.
     * @return A singleton instance of AppDatabase.
     */
    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        // Builds the Room database instance.
        return Room.databaseBuilder(
            context, // The application context.
            AppDatabase::class.java, // The AppDatabase class.
            "focus_timer_database" // The name of the database.
        ).build() // Builds the database.
    }

    /**
     * Provides a singleton instance of the TimerSessionDao.
     *
     * @param db The AppDatabase instance.
     * @return A singleton instance of TimerSessionDao.
     */
    @Singleton
    @Provides
    fun provideTimerSessionDao(db: AppDatabase) = db.timerSessionDao() // Gets the TimerSessionDao from the AppDatabase.
}