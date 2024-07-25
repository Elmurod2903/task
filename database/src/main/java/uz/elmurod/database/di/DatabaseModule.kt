package uz.elmurod.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.elmurod.database.data.dao.ArticlesDao
import uz.elmurod.database.data.database.ArticleDatabase
import uz.elmurod.database.util.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun mainDatabase(@ApplicationContext context: Context): ArticleDatabase {
        return Room.databaseBuilder(context, ArticleDatabase::class.java, Constants.DB)
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun activeOrderDao(database: ArticleDatabase): ArticlesDao {
        return database.getArticleDao()
    }

}