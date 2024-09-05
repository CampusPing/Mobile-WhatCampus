package core.data.cache

import core.model.Notice

class NoticeCache : Cache<Long, List<Notice>>() {

    override val emptyValue: List<Notice>
        get() = emptyList()
}
