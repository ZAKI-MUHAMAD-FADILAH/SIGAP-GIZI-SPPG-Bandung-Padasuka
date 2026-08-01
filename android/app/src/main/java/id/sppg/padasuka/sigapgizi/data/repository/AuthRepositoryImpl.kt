package id.sppg.padasuka.sigapgizi.data.repository

import id.sppg.padasuka.sigapgizi.data.remote.ProfileRemoteDataSource
import id.sppg.padasuka.sigapgizi.data.remote.SupabaseAuthDataSource
import id.sppg.padasuka.sigapgizi.domain.model.AuthenticatedUser
import id.sppg.padasuka.sigapgizi.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthRepositoryImpl
    @Inject
    constructor(
        private val authDataSource: SupabaseAuthDataSource,
        private val profileDataSource: ProfileRemoteDataSource,
    ) : AuthRepository {
        override fun observeCurrentUser(): Flow<AuthenticatedUser?> =
            authDataSource.observeUser().map { user ->
                user?.let { fetchProfile(it.id) }
            }

        override suspend fun signIn(
            emailOrUsername: String,
            password: String,
        ): Result<AuthenticatedUser> =
            runCatching {
                val user = authDataSource.signInWithEmail(emailOrUsername, password)
                fetchProfile(user.id)
            }

        override suspend fun signOut(): Result<Unit> =
            runCatching {
                authDataSource.signOut()
            }

        private suspend fun fetchProfile(userId: String): AuthenticatedUser {
            val p = profileDataSource.getProfile(userId)
            return AuthenticatedUser(
                id = p.id,
                username = p.username,
                displayName = p.displayName,
                teamCode = p.teamCode,
                role = p.role,
                isActive = p.isActive,
            )
        }
    }
