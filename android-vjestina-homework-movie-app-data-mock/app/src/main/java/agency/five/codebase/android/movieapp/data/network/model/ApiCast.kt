package agency.five.codebase.android.movieapp.data.network.model

import agency.five.codebase.android.movieapp.data.network.BASE_IMAGE_URL
import agency.five.codebase.android.movieapp.model.Actor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiCast(
    @SerialName("character")
    val character: String,

    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("profile_path")
    val profile_path: String?
) {
    fun toActor() = Actor(
        id = id,
        name = name,
        character = character,
        imageUrl = "$BASE_IMAGE_URL/$profile_path"
    )
}