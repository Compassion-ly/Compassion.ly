package com.capstone.compassionly.utility

sealed class ResourcesResponse<T> {
    data class OnSuccess<T>(val data: T) : ResourcesResponse<T>()
    data class OnFailure<T>(val message: String) : ResourcesResponse<T>()
    class Loading<T>() : ResourcesResponse<T>()
}