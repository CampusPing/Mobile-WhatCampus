package core.data.repository

import core.domain.repository.CampusMapRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCampusMapRepository : CampusMapRepository {
    override fun flowCampusMapUrl(campusId: Long): Flow<String> {
        return flow {
            emit("https://github.com/CampusPing/Mobile-WhatCampus/assets/56534241/3e6eff8f-08a6-4785-bcfe-1a195d3e4817")
        }
    }
}
