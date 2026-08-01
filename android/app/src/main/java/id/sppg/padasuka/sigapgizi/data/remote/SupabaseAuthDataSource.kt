package id.sppg.padasuka.sigapgizi.data.remote

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SupabaseAuthDataSource
    @Inject
    constructor(
        private val client: SupabaseClient,
    ) {
        fun observeUser(): Flow<UserInfo?> =
            client.auth.sessionStatus.map {
                client.auth.currentUserOrNull()
            }

        suspend fun signInWithEmail(
            email: String,
            pass: String,
        ): UserInfo {
            client.auth.signInWith(Email) {
                this.email = email
                this.password = pass
            }
            return checkNotNull(client.auth.currentUserOrNull()) {
                "USER_NULL_AFTER_SIGNIN"
            }
        }

        suspend fun signOut() {
            client.auth.signOut()
        }
    }
