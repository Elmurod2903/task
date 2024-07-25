package uz.elmurod.database.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.elmurod.database.data.dao.ArticlesDao
import uz.elmurod.domain.data.database.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun getArticleDao(): ArticlesDao
}