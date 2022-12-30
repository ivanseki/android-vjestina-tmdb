package agency.five.codebase.android.movieapp.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCreditsResponse(
    @SerialName("cast")
    val cast: List<ApiCast>,

    @SerialName("crew")
    val crew: List<ApiCrew>
)