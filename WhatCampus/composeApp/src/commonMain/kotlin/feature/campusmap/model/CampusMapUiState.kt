package feature.campusmap.model

data class CampusMapUiState(
    val campusMapUrl: String,
) {
    companion object {
        val Default = CampusMapUiState("")
    }
}
