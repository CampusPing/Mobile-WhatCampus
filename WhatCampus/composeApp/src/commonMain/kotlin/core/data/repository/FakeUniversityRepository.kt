package core.data.repository

import core.domain.repository.UniversityRepository
import core.model.Department
import core.model.University
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeUniversityRepository : UniversityRepository {
    private val universities = listOf(
        University(
            id = 0L,
            name = "상명대학교 (서울)",
            departments = sangmyungSeoulDepartments.mapIndexed { id, name ->
                Department(
                    id = id.toLong(),
                    name = name
                )
            }.toPersistentList()
        ),
        University(
            id = 1L,
            name = "상명대학교 (천안)",
            departments = sangmyungCheonanDepartments.mapIndexed { id, name ->
                Department(
                    id = id.toLong(),
                    name = name
                )
            }.toPersistentList()
        ),
    )

    override fun flowUniversity(query: String): Flow<List<University>> {
        return flow {
            emit(universities.filter { it.name.contains(query) })
        }
    }
}

private val sangmyungSeoulDepartments = listOf(
    "역사콘텐츠전공",
    "지적재산전공",
    "문헌정보학전공",
    "공간환경학부",
    "행정학부",
    "가족복지학과",
    "국가안보학과",
    "국어교육과",
    "영어교육과",
    "교육학과",
    "수학교육과",
    "경제금융학부",
    "경영학부",
    "글로벌경영학과",
    "융합경영학과",
    "휴먼지능정보공학전공",
    "핀테크전공",
    "빅데이터융합전공",
    "스마트생산전공",
    "컴퓨터과학전공",
    "전기공학전공",
    "게임전공",
    "애니메이션전공",
    "한일문화콘텐츠전공",
    "생명공학전공",
    "화학에너지공학전공",
    "화공신소재전공",
    "식품영양학전공",
    "의류학전공",
    "스포츠건강관리전공",
    "무용예술전공",
    "조형예술전공",
    "생활예술전공",
    "음악학부",
)

private val sangmyungCheonanDepartments = listOf(
    "글로벌지역학부",
    "한국언어문화전공",
    "일본어권지역학전공",
    "중국어권지역학전공",
    "영어권지역학전공",
    "프랑스어권지역학전공",
    "독일어권지역학전공",
    "러시아어권지역학전공",
    "디자인학부",
    "커뮤니케이션디자인전공",
    "패션디자인전공",
    "텍스타일디자인전공",
    "스페이스디자인전공",
    "세라믹디자인전공",
    "인더스트리얼디자인전공",
    "AR·VR미디어디자인전공",
    "영화영상전공",
    "연극전공",
    "무대미술전공",
    "사진영상미디어전공",
    "디지털만화영상전공",
    "문화예술경영전공",
    "AI미디어콘텐츠전공 ",
    "글로벌금융경영학부",
    "식품공학과",
    "그린스마트시티학과",
    "간호학과",
    "스포츠융합자유전공학부",
    "전자공학과",
    "소프트웨어학과",
    "스마트정보통신공학과",
    "경영공학과",
    "그린화학공학과",
    "건설시스템공학과",
    "정보보안공학과",
    "시스템반도체공학과",
    "휴먼지능로봇공학과",
)
