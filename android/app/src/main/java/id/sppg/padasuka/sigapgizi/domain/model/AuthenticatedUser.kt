package id.sppg.padasuka.sigapgizi.domain.model

data class AuthenticatedUser(
    val id: String,
    val username: String,
    val displayName: String,
    val teamCode: String,
    val role: String,
    val isActive: Boolean,
)
