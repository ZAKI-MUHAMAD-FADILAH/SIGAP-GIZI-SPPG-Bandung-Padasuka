package id.sppg.padasuka.sigapgizi.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ProfileDto(
    @SerialName("id")
    val id: String,
    @SerialName("username")
    val username: String,
    @SerialName("display_name")
    val displayName: String,
    @SerialName("team_code")
    val teamCode: String,
    @SerialName("role")
    val role: String,
    @SerialName("is_active")
    val isActive: Boolean,
)
