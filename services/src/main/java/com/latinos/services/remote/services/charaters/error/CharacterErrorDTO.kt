package com.latinos.services.remote.services.charaters.error

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

sealed class CharacterErrorDTO {
    object Generic : CharacterErrorDTO()
    object NotExits : CharacterErrorDTO()
}

@Keep
internal data class CharacterErrorResponse(
    @SerializedName("errorDetail") val errorDetail: List<CharacterErrorDetailResponse>,
)

@Keep
internal data class CharacterErrorDetailResponse(
    @SerializedName("errorCode") val errorCode: String,
    @SerializedName("errorTime") val errorTime: String,
    @SerializedName("message") val message: String?,
)