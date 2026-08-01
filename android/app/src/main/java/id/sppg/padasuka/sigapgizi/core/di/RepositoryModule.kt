package id.sppg.padasuka.sigapgizi.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.sppg.padasuka.sigapgizi.data.repository.AuthRepositoryImpl
import id.sppg.padasuka.sigapgizi.data.repository.BeneficiaryRepositoryImpl
import id.sppg.padasuka.sigapgizi.domain.repository.AuthRepository
import id.sppg.padasuka.sigapgizi.domain.repository.BeneficiaryRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindBeneficiaryRepository(impl: BeneficiaryRepositoryImpl): BeneficiaryRepository
}
