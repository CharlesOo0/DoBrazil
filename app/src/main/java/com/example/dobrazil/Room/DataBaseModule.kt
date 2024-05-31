package com.example.dobrazil.Room

import android.content.Context
import androidx.room.Room
import com.example.dobrazil.Dao.EventDao
import com.example.dobrazil.Dao.EventFinanciersCrossRefDao
import com.example.dobrazil.Dao.EventInvitedCrossRefDao
import com.example.dobrazil.Dao.ExpenseDao
//import com.example.dobrazil.Dao.EventFinanciersCrossRefDao
//import com.example.dobrazil.Dao.EventInvitedCrossRefDao
//import com.example.dobrazil.Dao.ExpenseDao
import com.example.dobrazil.Dao.ProfilDao
//import com.example.dobrazil.EntityRepositories.EventFinanciersRepository
//import com.example.dobrazil.EntityRepositories.EventInvitedRepository
//import com.example.dobrazil.EntityRepositories.EventRepository
//import com.example.dobrazil.EntityRepositories.ExpenseRepository
import com.example.dobrazil.EntityRepositories.ProfilRepository
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
         )
             .fallbackToDestructiveMigration()
             .build()
     }

     @Provides
     fun provideTaskProfilDao(database: AppDatabase): ProfilDao = database.profilDao()

    @Provides
    fun provideTaskExpenseDao(database: AppDatabase): ExpenseDao = database.expenseDao()

    @Provides
    fun provideTaskEventInvitedCrossRefDao(database: AppDatabase): EventInvitedCrossRefDao = database.eventInvitedCrossRefDao()

    @Provides
    fun provideTaskEventFinanciersCrossRefDao(database: AppDatabase): EventFinanciersCrossRefDao = database.eventFinanciersCrossRefDao()

    @Provides
    fun provideTaskEventDao(database: AppDatabase): EventDao = database.eventDao()
}