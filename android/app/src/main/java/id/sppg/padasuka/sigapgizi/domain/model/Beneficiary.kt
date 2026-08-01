package id.sppg.padasuka.sigapgizi.domain.model

data class Beneficiary(
    val id: String,
    val code: String,
    val name: String,
    val category: BeneficiaryCategory,
    val teamCode: String,
    val smallPortions: Int,
    val largePortions: Int,
    val teacherPortions: Int,
    val pregnantPortions: Int,
    val breastfeedingPortions: Int,
    val toddlerPortions: Int,
    val totalPortions: Int,
)
