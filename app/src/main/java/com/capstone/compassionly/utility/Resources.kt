package com.capstone.compassionly.utility

//sealed class Resources<T>(
//    val data: T? = null,
//    val message: String? = null
//) {
//    class Success<T>(data: T) : Resources<T>(data)
//    class Loading<T> : Resources<T>()
//    class OnFailure<T>(message: String, data: T? = null) : Resources<T>(data, message)
//}

sealed class Resources<out R> private constructor() {
    data class Success<out T>(val data: T) : Resources<T>()
    data class Error<out T>(val error: T) : Resources<T>()
    object Loading : Resources<Nothing>()
}