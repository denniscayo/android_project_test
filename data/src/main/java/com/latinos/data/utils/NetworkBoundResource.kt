package com.latinos.data.utils

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.*
import retrofit2.Response

abstract class NetworkBoundResource<RequestType, ResultType>(
    private val errorHandler: ErrorHandler,
) {
    fun asFlow() = flow {
        val cachedData = fetchFromLocal().firstOrNull()

        try {
            if (shouldFetch(cachedData)) {
                emit(Result.Success(cachedData)) // update loading state with cached data

                val apiResponse = fetchFromRemote()
                val remoteResponse = apiResponse.body()

                if (apiResponse.isSuccessful && remoteResponse != null) {
                    saveRemoteData(remoteResponse)

                    // Collects all the values from the given flow and emits them to the collector
                    emitAll(fetchFromLocal().map { Result.Success(it) })
                } else {
                    emitAll(fetchFromLocal().map {
                        Result.Error(errorHandler.getApiError(apiResponse.code()), it)
                    })
                }
            } else {
                emit(Result.Success(cachedData))
            }
        } catch (e: Exception) {
            emitAll(fetchFromLocal().map { Result.Error(errorHandler.getError(e), it) })
        }
    }

    @WorkerThread
    protected abstract suspend fun saveRemoteData(response: RequestType)

    @MainThread
    protected abstract fun fetchFromLocal(): Flow<ResultType>

    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<RequestType>

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean
}
