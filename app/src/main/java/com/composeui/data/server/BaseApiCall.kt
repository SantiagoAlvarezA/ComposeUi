package com.composeui.data.server

import com.composeui.data.Resource
import retrofit2.Response
import java.io.IOException

open class BaseApiCall {
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Resource<T> {
        val response: Response<T>
        try {
            response = call.invoke()
        } catch (e: Exception) {
            return Resource.Error(exception = e)
        }

        if (!response.isSuccessful) {
            if (response.code() == 304) {
                return Resource.Error()
            } else {
                val responseErrorBody = response.errorBody()
                if (responseErrorBody != null) {
                    return Resource.Error(exception = IOException(responseErrorBody.toString()))
                }
                return Resource.Error(exception = IOException("Error Occurred during getting safe Api result, Custom ERROR "))
            }
        }

        val header = response.headers()["date-sync"]
        //Timber.i("===> Set header date-sync $header")
        header?.let {
            if (it.isNotEmpty()) {
                //MyApplication.setDate(header)
            }
        }

        return Resource.Success(response.body()!!)
    }
}