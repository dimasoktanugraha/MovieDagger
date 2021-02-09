package com.artera.movieexpertdagger.core.di

import android.content.Context
import androidx.room.Room
import com.artera.movieexpertdagger.core.data.source.local.room.MovieDao
import com.artera.movieexpertdagger.core.data.source.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): MovieDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("movie".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java, "Movies.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }

    @Provides
    fun provideTourismDao(database: MovieDatabase): MovieDao = database.tourismDao()
}