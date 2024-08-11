package feature.noticeSearch.model

internal sealed class NoticeSearchClearType {
    data class Delete(val query: String) : NoticeSearchClearType()
    data object DeleteAll : NoticeSearchClearType()
    data object Nothing : NoticeSearchClearType()
}
