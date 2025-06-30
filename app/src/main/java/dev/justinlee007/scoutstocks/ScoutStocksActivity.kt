package dev.justinlee007.scoutstocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import dev.justinlee007.scoutstocks.ui.compose.OverviewScreen
import dev.justinlee007.scoutstocks.ui.theme.ScoutStocksTheme
import dev.justinlee007.scoutstocks.ui.viewmodel.OverviewViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ScoutStocksActivity : ComponentActivity() {

    private val overviewViewModel by viewModels<OverviewViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            Timber.d("Making API call")
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                overviewViewModel.refreshUiState()
            }
        }

        enableEdgeToEdge()
        setContent {
            ScoutStocksTheme {
                OverviewScreen()
            }
        }
    }
}