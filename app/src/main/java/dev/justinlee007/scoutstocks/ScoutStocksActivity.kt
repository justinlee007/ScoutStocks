package dev.justinlee007.scoutstocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import dev.justinlee007.scoutstocks.ui.compose.ScoutStocksApp

@AndroidEntryPoint
class ScoutStocksActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScoutStocksApp()
        }
    }
}