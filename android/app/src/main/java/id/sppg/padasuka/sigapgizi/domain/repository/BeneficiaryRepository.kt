package id.sppg.padasuka.sigapgizi.domain.repository

import id.sppg.padasuka.sigapgizi.domain.model.Beneficiary

interface BeneficiaryRepository {
    suspend fun getBeneficiaries(): Result<List<Beneficiary>>
}
