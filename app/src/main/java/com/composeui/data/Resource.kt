package com.composeui.data

sealed class Resource<out R> {
    object Loading : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(
        val errors: HashMap<String, String>? = null,
        val exception: Exception? = null
    ) : Resource<Nothing>()
}
