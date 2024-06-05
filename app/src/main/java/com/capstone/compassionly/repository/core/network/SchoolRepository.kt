package com.capstone.compassionly.repository.core.network

import com.capstone.compassionly.datasource.network.ApiConfiguration
import com.capstone.compassionly.models.SchoolMajor
import com.capstone.compassionly.models.SchoolModel
import com.capstone.compassionly.models.SuccessResponse
import retrofit2.Response

class SchoolRepository {

    suspend fun getSchoolList(): Response<SuccessResponse<List<SchoolModel>>> {
        return ApiConfiguration.hitPointService.getSchoolList()
    }

    suspend fun getSchoolMajor(): Response<SuccessResponse<List<SchoolMajor>>> {
        return ApiConfiguration.hitPointService.getSchoolMajorList()
    }

    companion object {
        @Volatile
        private var instance : SchoolRepository? = null
        fun getInstance() : SchoolRepository? = instance ?: synchronized(this) {
            instance = SchoolRepository()
            instance
        }
    }
}