package uz.elmurod.network.plugs

import kotlinx.coroutines.flow.Flow
import uz.elmurod.domain.ports.preference.PreferencePort
import uz.elmurod.network.datastore.DataStoreManager
import javax.inject.Inject

class PreferencePlug @Inject constructor(private val dataStoreManager: DataStoreManager) :
    PreferencePort {
    override suspend fun setNetworkStatus(boolNet: Boolean) {
        dataStoreManager.setNetworkStatus(boolNet)
    }

    override fun networkStatus(): Flow<Boolean> {
        return dataStoreManager.networkStatus
    }
}