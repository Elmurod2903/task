package uz.elmurod.domain.ports.preference

import kotlinx.coroutines.flow.Flow

interface PreferencePort {

    suspend fun setNetworkStatus(boolNet: Boolean)

    fun networkStatus(): Flow<Boolean>

}
