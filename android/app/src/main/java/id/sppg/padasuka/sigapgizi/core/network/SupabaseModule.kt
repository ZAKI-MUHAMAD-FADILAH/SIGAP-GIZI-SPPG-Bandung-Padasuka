package id.sppg.padasuka.sigapgizi.core.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SupabaseModule {
    @Provides
    @Singleton
    fun provideSupabaseClient(configuration: SupabaseConfiguration): SupabaseClient =
        createSupabaseClient(
            supabaseUrl = configuration.url,
            supabaseKey = configuration.publishableKey,
        ) {
            install(Auth)
            install(Postgrest)
        }
}
