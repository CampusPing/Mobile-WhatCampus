package feature.notificationArchive.model

import core.model.NotificationArchive
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class NotificationArchiveUiState(
    val isLoading: Boolean,
    val notificationArchives: PersistentList<NotificationArchive> = persistentListOf(),
)
