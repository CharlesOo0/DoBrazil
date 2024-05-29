package com.example.dobrazil.Room

import android.content.Context
import androidx.room.Room
import com.example.dobrazil.Dao.EventDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
     @Singleton
     @Provides
     fun provideDataBase(@ApplicationContext context: Context): AppDatabase {
         return Room.databaseBuilder(
             context.applicationContext,
             AppDatabase::class.java,
             "DoBrazil.db"
         ).build()
     }

     @Provides
     fun provideTaskEventDao(database: AppDatabase): EventDao = database.eventDao()

}