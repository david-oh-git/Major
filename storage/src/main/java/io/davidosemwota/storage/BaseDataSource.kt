package io.davidosemwota.storage

import retrofit2.Response
import timber.log.Timber

abstract class BaseDataSource {

    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Response<T>,
        errorMsg: String
    ): Result<T> = safeApiResult(call, errorMsg)


    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>,
        errorMsg: String
    ): Result<T> {

        try {
            val response = call.invoke()
            if (response.isSuccessful) return Result.Success(response.body()!!)

            return Result.Error(
                Exception("Error occurred while getting safe api result, ERROR - $errorMsg")
            )
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }
}