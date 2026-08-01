package id.sppg.padasuka.sigapgizi.feature.locations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.sppg.padasuka.sigapgizi.domain.model.AuthenticatedUser

private val NavyPrimary = Color(0xFF0A2A5E)

@Composable
fun LocationListScreen(
    user: AuthenticatedUser,
    state: LocationListUiState,
    onSignOutClick: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F6F9))
                .padding(16.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(NavyPrimary, shape = androidx.compose.foundation.shape.RoundedCornerShape(20.dp))
                    .padding(20.dp),
        ) {
            Text(
                text = "Halo, ${user.displayName}",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Tim: ${user.teamCode}",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onSignOutClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.2f)),
            ) {
                Text(text = "Keluar (Sign Out)", color = Color.White, fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (state) {
            LocationListUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = NavyPrimary)
                }
            }
            is LocationListUiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = state.message, color = Color.Red)
                }
            }
            is LocationListUiState.Success -> {
                Text(
                    text = "Daftar Lokasi (${state.totalLocations} Lokasi - Total ${state.totalPortions} Porsi)",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyPrimary,
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.beneficiaries, key = { it.id }) { item ->
                        BeneficiaryCard(beneficiary = item)
                    }
                }
            }
        }
    }
}
