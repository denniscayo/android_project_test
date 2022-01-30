package com.latinos.data.error

import com.latinos.domain.utils.error.GlobalErrorMapper
import com.latinos.domain.utils.error.GlobalErrorType
import com.latinos.services.remote.manager.error.RestIllegalStateException
import com.latinos.services.remote.manager.error.RestNetworkException
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class GlobalErrorMapperImpl @Inject constructor() : GlobalErrorMapper {
    override fun map(throwable: Throwable) =
        when (throwable) {
            is RestNetworkException -> GlobalErrorType.NETWORK_UNAVAILABLE
            is RestIllegalStateException, is CancellationException -> GlobalErrorType.SILENT
            else -> GlobalErrorType.GENERIC_ERROR
        }
}