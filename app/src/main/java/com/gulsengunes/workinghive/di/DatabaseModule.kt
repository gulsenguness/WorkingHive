package com.gulsengunes.workinghive.di

import android.content.Context
import com.gulsengunes.workinghive.data.database.TaskDao
import com.gulsengunes.workinghive.data.database.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): TaskDatabase {
        return TaskDatabase.getDatabase(context)
    }

    @Provides
    fun provideTaskDao(database: TaskDatabase):TaskDao{
        return database.taskDao()
    }

}