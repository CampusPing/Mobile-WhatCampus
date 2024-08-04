package core.data.repository

import core.database.dao.NoticeDao
import core.database.mapper.toNotice
import core.database.mapper.toNoticeEntity
import core.domain.repository.NoticeRepository
import core.model.Notice
import core.model.NoticeCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone.Companion.currentSystemDefault
import kotlinx.datetime.toLocalDateTime

class FakeNoticeRepository(
    private val noticeDao: NoticeDao,
) : NoticeRepository {
    private val noticeCategories = listOf(
        NoticeCategory(1, "학과", "📚"),
        NoticeCategory(2, "학사", "🎓"),
        NoticeCategory(3, "일반", "📰"),
        NoticeCategory(4, "사회봉사", "🤝"),
        NoticeCategory(5, "등록/장학", "💰"),
        NoticeCategory(6, "학생생활", "🏠"),
        NoticeCategory(7, "글로벌", "🌍"),
        NoticeCategory(8, "진로취업", "👔"),
        NoticeCategory(9, "비교과", "🎨"),
        NoticeCategory(10, "코로나19", "😷"),
    )

    private val notices = listOf(
        Notice(0, "공지사항입니다!", LocalDateTime(2024, 6, 4, 0, 0, 0), "https://www.naver.com"),
        Notice(1, "공지사항입니다!", LocalDateTime(2024, 6, 2, 0, 0, 0), "https://www.naver.com"),
        Notice(2, "공지사항입니다!", LocalDateTime(2024, 6, 5, 0, 0, 0), "https://www.naver.com"),
        Notice(3, "공지사항입니다!", Clock.System.now().toLocalDateTime(currentSystemDefault()), "https://www.naver.com"),
        Notice(
            4,
            "공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!",
            Clock.System.now().toLocalDateTime(currentSystemDefault()),
            "https://www.naver.com"
        ),
        Notice(
            5,
            "공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!",
            Clock.System.now().toLocalDateTime(currentSystemDefault()),
            "https://www.naver.com"
        ),
        Notice(
            6,
            "공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!",
            Clock.System.now().toLocalDateTime(currentSystemDefault()),
            "https://www.naver.com"
        ),
        Notice(
            7,
            "공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!",
            Clock.System.now().toLocalDateTime(currentSystemDefault()),
            "https://www.naver.com"
        ),
        Notice(
            8,
            "공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!",
            Clock.System.now().toLocalDateTime(currentSystemDefault()),
            "https://www.naver.com"
        ),
        Notice(
            9,
            "공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!공지사항입니다!",
            Clock.System.now().toLocalDateTime(currentSystemDefault()),
            "https://www.naver.com"
        ),
    )

    private val subscribedNoticeCategories = mutableSetOf<NoticeCategory>()

    override fun flowNoticeCategory(universityId: Long): Flow<List<NoticeCategory>> {
        return flow {
            emit(noticeCategories)
        }
    }

    override fun flowNoticesByCategoryId(
        universityId: Long,
        noticeCategoryId: Long,
    ): Flow<List<Notice>> {
        return flow {
            emit(notices)
        }
    }

    override fun flowNoticesByDepartmentId(
        universityId: Long,
        departmentId: Long,
    ): Flow<List<Notice>> {
        return flow {
            emit(notices)
        }
    }

    override fun flowBookmarkedNotices(): Flow<List<Notice>> {
        return noticeDao.getAll()
            .map { noticeEntities ->
                noticeEntities.map { entity -> entity.toNotice() }
            }
    }

    override fun bookmarkNotice(notice: Notice): Flow<Unit> {
        return flow {
            emit(noticeDao.insert(notice = notice.toNoticeEntity()))
        }
    }

    override fun unbookmarkNotice(notice: Notice): Flow<Unit> {
        return flow {
            emit(noticeDao.delete(notice = notice.toNoticeEntity()))
        }
    }

    override suspend fun unbookmarkNotices(notices: List<Notice>) {
        notices.forEach {
            noticeDao.delete(notice = it.toNoticeEntity())
        }
    }

    override fun flowSubscribedNoticeCategories(userId: Long): Flow<Set<NoticeCategory>> {
        return flow {
            emit(subscribedNoticeCategories.toSet())
        }
    }

    override suspend fun subscribeNoticeCategories(userId: Long, noticeCategories: Set<NoticeCategory>) {
        subscribedNoticeCategories.clear()
        subscribedNoticeCategories.addAll(noticeCategories)
    }
}
