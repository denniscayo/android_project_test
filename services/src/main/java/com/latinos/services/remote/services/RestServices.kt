package com.latinos.services.remote.services

import com.google.gson.JsonParseException
import com.latinos.common.utils.either.Either
import com.latinos.services.remote.manager.NetworkManager
import com.latinos.services.remote.manager.error.RestNetworkException
import com.latinos.services.remote.manager.error.RestServiceException
import okhttp3.Headers
import okhttp3.Request
import retrofit2.Call
import java.io.IOException
import javax.inject.Inject

internal interface RestServices {
    @Throws(RestNetworkException::class, RestServiceException::class)
    fun <T> execute(request: Call<T>): Either<RestResponse<T>, RestError>

    class Default
    @Inject
    constructor(
        private val networkManager: NetworkManager,
    ) : RestServices {

        private inline val logTag
            get() = javaClass.simpleName

        override fun <T> execute(request: Call<T>): Either<RestResponse<T>, RestError> =
            try {
                networkManager.checkConnectivity()

                val response = request.execute()
                if (response.isSuccessful) {
                    Either.Success(RestResponse(response.body(),
                        response.code(),
                        response.headers()))
                } else {
                    Either.Failure(RestError(response.errorBody()?.string(),
                        response.code(),
                        response.headers()))
                }
            } catch (ex: JsonParseException) {
                throw RestServiceException(ex.message,
                    (request.request() as Request).url.toString())
            } catch (ex: IOException) {
                throw RestServiceException(ex.message,
                    (request.request() as Request).url.toString())
            }
    }
}

data class RestResponse<T>(
    val optData: T?,
    val statusCode: Int,
    val headers: Headers,
) {
    val data: T
        get() = optData!!
}

data class RestError(
    val description: String?,
    val statusCode: Int,
    val headers: Headers,
)