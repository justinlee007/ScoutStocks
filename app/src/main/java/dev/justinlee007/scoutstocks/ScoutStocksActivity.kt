package dev.justinlee007.scoutstocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dev.justinlee007.scoutstocks.ui.compose.OverviewScreen
import dev.justinlee007.scoutstocks.ui.theme.ScoutStocksTheme

class ScoutStocksActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScoutStocksTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    OverviewScreen(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}