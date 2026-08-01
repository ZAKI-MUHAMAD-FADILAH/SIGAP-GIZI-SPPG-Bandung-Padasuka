package id.sppg.padasuka.sigapgizi.feature.locations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.sppg.padasuka.sigapgizi.domain.model.Beneficiary

private val NavyPrimary = Color(0xFF0A2A5E)

@Composable
fun BeneficiaryCard(
    beneficiary: Beneficiary,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = beneficiary.code,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyPrimary,
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = beneficiary.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Kategori: ${beneficiary.category.name}",
                    fontSize = 12.sp,
                    color = Color.Gray,
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${beneficiary.totalPortions}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyPrimary,
                )
                Text(
                    text = "Porsi",
                    fontSize = 12.sp,
                    color = Color.Gray,
                )
            }
        }
    }
}
