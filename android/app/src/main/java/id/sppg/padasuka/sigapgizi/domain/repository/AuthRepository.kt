package id.sppg.padasuka.sigapgizi.domain.repository

import id.sppg.padasuka.sigapgizi.domain.model.AuthenticatedUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun observeCurrentUser(): Flow<AuthenticatedUser?>

    suspend fun signIn(
        emailOrUsername: String,
        password: String,
    ): Result<AuthenticatedUser>

    suspend fun signOut(): Result<Unit>
}
