package feature.notificationArchive.model

import core.model.Notification
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class NotificationArchiveUiState(
    val isLoading: Boolean,
    val notifications: PersistentList<Notification> = persistentListOf(),
)
