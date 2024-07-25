package uz.elmurod.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.elmurod.domain.ports.network.NewsPort
import uz.elmurod.domain.ports.preference.PreferencePort
import uz.elmurod.network.plugs.NewsPlug
import uz.elmurod.network.plugs.PreferencePlug
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModulePort {

    @Provides
    @Singleton
    fun newsPlug(newsPlug: NewsPlug): NewsPort = newsPlug

    @Provides
    fun preferencePlug(preferencePlug: PreferencePlug): PreferencePort = preferencePlug
}