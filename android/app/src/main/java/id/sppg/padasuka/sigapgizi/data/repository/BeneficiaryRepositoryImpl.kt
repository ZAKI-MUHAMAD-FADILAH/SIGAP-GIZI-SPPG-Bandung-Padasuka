package id.sppg.padasuka.sigapgizi.data.repository

import id.sppg.padasuka.sigapgizi.data.mapper.toDomain
import id.sppg.padasuka.sigapgizi.data.remote.BeneficiaryRemoteDataSource
import id.sppg.padasuka.sigapgizi.domain.model.Beneficiary
import id.sppg.padasuka.sigapgizi.domain.repository.BeneficiaryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class BeneficiaryRepositoryImpl
    @Inject
    constructor(
        private val remoteDataSource: BeneficiaryRemoteDataSource,
    ) : BeneficiaryRepository {
        override suspend fun getBeneficiaries(): Result<List<Beneficiary>> =
            runCatching {
                remoteDataSource.getBeneficiaries().map { it.toDomain() }
            }
    }
