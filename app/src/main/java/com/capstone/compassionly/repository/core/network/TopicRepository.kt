package com.capstone.compassionly.repository.core.network

import com.capstone.compassionly.datasource.network.ApiConfiguration
import com.capstone.compassionly.models.RatingModel
import com.capstone.compassionly.models.SuccessResponse
import com.capstone.compassionly.models.TopicModel
import com.capstone.compassionly.models.forsending.RatingModelSend
import com.capstone.compassionly.utility.Utils
import retrofit2.Response

class TopicRepository{

    suspend fun getAllTopic(
        token: String,
        limit: Int,
        offset: Int
    ): Response<SuccessResponse<List<TopicModel>>> {
        return ApiConfiguration.hitPointService.getAllTopic(
            Utils.getHeader(token), limit, offset
        )
    }

    suspend fun postRate(
        token: String,
        ratingModel: RatingModelSend
    ): Response<SuccessResponse<RatingModel>> {
        return ApiConfiguration.hitPointService.postRating(
            Utils.getHeader(token),
            ratingModel
        )
    }

    companion object {

        @Volatile
        private var instance: TopicRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance = TopicRepository()
            instance
        }
    }
}