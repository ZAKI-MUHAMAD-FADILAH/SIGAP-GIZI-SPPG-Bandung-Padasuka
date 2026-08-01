package id.sppg.padasuka.sigapgizi.feature.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val NavyPrimary = Color(0xFF0A2A5E)
private val RedPrimary = Color(0xFFD62839)
private const val CARD_WIDTH_FRACTION = 0.9f
private const val CARD_ALPHA = 0.95f

@Composable
fun LoginScreen(
    state: LoginUiState,
    onEvent: (LoginUiEvent) -> Unit,
) {
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(NavyPrimary),
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            modifier =
                Modifier
                    .fillMaxWidth(CARD_WIDTH_FRACTION)
                    .padding(16.dp),
            shape = RoundedCornerShape(24.dp),
            color = Color.White.copy(alpha = CARD_ALPHA),
            shadowElevation = 8.dp,
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "SIGAP GIZI",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyPrimary,
                )
                Text(
                    text = "SPPG Bandung Padasuka",
                    fontSize = 14.sp,
                    color = Color.Gray,
                )

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = state.emailOrAccountInput,
                    onValueChange = { onEvent(LoginUiEvent.EmailOrAccountChanged(it)) },
                    label = { Text("Email atau akun") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors =
                        OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = NavyPrimary,
                            focusedLabelColor = NavyPrimary,
                        ),
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = state.passwordInput,
                    onValueChange = { onEvent(LoginUiEvent.PasswordChanged(it)) },
                    label = { Text("Kata sandi") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    colors =
                        OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = NavyPrimary,
                            focusedLabelColor = NavyPrimary,
                        ),
                )

                if (state.errorMessage != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = state.errorMessage,
                        color = RedPrimary,
                        fontSize = 12.sp,
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { onEvent(LoginUiEvent.Submit) },
                    enabled = !state.isLoading,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = NavyPrimary),
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp)
                    } else {
                        Text(text = "Masuk Aplikasi", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}
