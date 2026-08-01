package id.sppg.padasuka.sigapgizi.data.remote

import id.sppg.padasuka.sigapgizi.data.remote.dto.BeneficiaryDto
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class BeneficiaryRemoteDataSource
    @Inject
    constructor(
        private val client: SupabaseClient,
    ) {
        suspend fun getBeneficiaries(): List<BeneficiaryDto> =
            client.postgrest["beneficiaries"]
                .select()
                .decodeList<BeneficiaryDto>()
    }
