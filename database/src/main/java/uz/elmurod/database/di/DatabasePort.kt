package uz.elmurod.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.elmurod.database.plugs.ArticlePlugDatabase
import uz.elmurod.domain.ports.database.ArticlesDatabasePort
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabasePort {

    @Singleton
    @Provides
    fun articlePort(plugDatabase: ArticlePlugDatabase): ArticlesDatabasePort = plugDatabase

}