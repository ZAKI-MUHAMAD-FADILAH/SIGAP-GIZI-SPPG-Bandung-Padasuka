package id.sppg.padasuka.sigapgizi.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.sppg.padasuka.sigapgizi.feature.auth.LoginScreen
import id.sppg.padasuka.sigapgizi.feature.auth.LoginViewModel
import id.sppg.padasuka.sigapgizi.feature.auth.SessionUiState
import id.sppg.padasuka.sigapgizi.feature.auth.SessionViewModel
import id.sppg.padasuka.sigapgizi.feature.locations.LocationListScreen
import id.sppg.padasuka.sigapgizi.feature.locations.LocationListViewModel

@Composable
fun AppNavigation(sessionViewModel: SessionViewModel = hiltViewModel()) {
    val sessionState by sessionViewModel.sessionState.collectAsState()

    when (val state = sessionState) {
        SessionUiState.Checking -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        SessionUiState.Unauthenticated -> {
            val loginViewModel: LoginViewModel = hiltViewModel()
            val loginState by loginViewModel.uiState.collectAsState()
            LoginScreen(
                state = loginState,
                onEvent = loginViewModel::onEvent,
            )
        }
        is SessionUiState.Authenticated -> {
            val locationViewModel: LocationListViewModel = hiltViewModel()
            val locationState by locationViewModel.uiState.collectAsState()
            LocationListScreen(
                user = state.user,
                state = locationState,
                onSignOutClick = sessionViewModel::signOut,
            )
        }
        is SessionUiState.Inactive -> {
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(Color.White),
                contentAlignment = Alignment.Center,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Akun Anda (${state.user.username}) Non-Aktif.")
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = sessionViewModel::signOut) {
                        Text(text = "Keluar")
                    }
                }
            }
        }
        is SessionUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.message, color = Color.Red)
            }
        }
    }
}
