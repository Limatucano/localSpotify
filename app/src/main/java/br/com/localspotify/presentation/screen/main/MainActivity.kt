package br.com.localspotify.presentation.screen.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import br.com.localspotify.data.player.service.AudioService
import br.com.localspotify.presentation.navigation.NavHostGraph
import br.com.localspotify.presentation.screen.player.PlayerScreen
import br.com.localspotify.presentation.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private var isServiceRunning = false

    private fun startForegroundService() {
        if (!isServiceRunning) {
            val intent = Intent(this,AudioService::class.java)
            startForegroundService(intent)
            isServiceRunning = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { true }
        CoroutineScope(Dispatchers.Main).launch {
            delay(1800)
            splashScreen.setKeepOnScreenCondition { false }
        }
        enableEdgeToEdge()
        startForegroundService()
        setContent {
            val imeState = rememberPlayerState()

            LaunchedEffect(imeState.value) {
                if (imeState.value) {

                }
            }

            val navController = rememberNavController()
            AppTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .statusBarsPadding()
                ) {
                    NavHostGraph(
                        navController = navController
                    )
                    PlayerScreen(
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )
                }
            }
        }
    }
}
