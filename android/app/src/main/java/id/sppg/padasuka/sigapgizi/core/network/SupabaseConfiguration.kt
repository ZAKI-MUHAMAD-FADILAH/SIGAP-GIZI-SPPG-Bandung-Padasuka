package id.sppg.padasuka.sigapgizi.core.network

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SupabaseConfiguration
    @Inject
    constructor() {
        val url: String = "https://your-supabase-project.supabase.co"
        val publishableKey: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.dummy_anon_key"
    }
