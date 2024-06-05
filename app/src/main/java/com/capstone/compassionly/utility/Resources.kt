package com.capstone.compassionly.utility

sealed class Resources<out R> private constructor() {
    data class Success<out T>(val data: T) : Resources<T>()
    data class Error<out T>(val error: T) : Resources<T>()
    object Loading : Resources<Nothing>()
}