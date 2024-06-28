package core.data.repository

import core.domain.repository.CampusMapRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCampusMapRepository : CampusMapRepository {
    override fun flowCampusMapUrl(campusId: Long): Flow<String> {
        return flow {
            emit("https://postfiles.pstatic.net/MjAyNDA2MjhfMTcw/MDAxNzE5NTg1MDg2NDY3.UegfISKMypHzfHFqGw-bvWO4fUEsLyJEtQAv99cOZNIg.kVbvglyLbdN7b8B9Kr3PQzL9h05M65Qc-fMhZ_-UHo0g.PNG/%EC%83%81%EB%AA%85%EB%8C%80%ED%95%99%EA%B5%90_%EC%84%9C%EC%9A%B8_%EC%A7%80%EB%8F%84.png?type=w773")
        }
    }
}
