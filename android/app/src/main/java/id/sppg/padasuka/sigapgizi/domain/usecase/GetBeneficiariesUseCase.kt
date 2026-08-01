package id.sppg.padasuka.sigapgizi.domain.usecase

import id.sppg.padasuka.sigapgizi.domain.model.Beneficiary
import id.sppg.padasuka.sigapgizi.domain.repository.BeneficiaryRepository
import javax.inject.Inject

class GetBeneficiariesUseCase
    @Inject
    constructor(
        private val repository: BeneficiaryRepository,
    ) {
        suspend operator fun invoke(): Result<List<Beneficiary>> = repository.getBeneficiaries()
    }
