package id.sppg.padasuka.sigapgizi.data.remote

import id.sppg.padasuka.sigapgizi.data.remote.dto.ProfileDto
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ProfileRemoteDataSource
    @Inject
    constructor(
        private val client: SupabaseClient,
    ) {
        suspend fun getProfile(userId: String): ProfileDto =
            client.postgrest["profiles"]
                .select {
                    filter {
                        eq("id", userId)
                    }
                }.decodeSingle<ProfileDto>()
    }
