package core.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class CampusMapResponse(
    val campusMapUrl: String,
)
