package uz.elmurod.domain.mapper

import uz.elmurod.core.mapper.ResponseModelMapper
import uz.elmurod.domain.data.network.ResponseSource
import uz.elmurod.domain.data.ui.Source
import javax.inject.Inject

class SourceRMMapper @Inject constructor() : ResponseModelMapper<ResponseSource, Source> {
    override fun mapFromResponse(response: ResponseSource): Source = Source(
        id = response.id,
        name = response.name
    )
}