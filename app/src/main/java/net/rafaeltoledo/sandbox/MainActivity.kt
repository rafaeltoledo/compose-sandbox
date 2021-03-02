package net.rafaeltoledo.sandbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import net.rafaeltoledo.sandbox.ui.screen.repositories.RepositoriesScreen
import net.rafaeltoledo.sandbox.ui.screen.repositories.RepositoriesViewModel
import net.rafaeltoledo.sandbox.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

  private val viewModel: RepositoriesViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    WindowCompat.setDecorFitsSystemWindows(window, false)

    setContent {
      AppTheme {
        ProvideWindowInsets {
          SandboxApp(viewModel)
        }
      }
    }
  }
}

@Composable
fun SandboxApp(viewModel: RepositoriesViewModel) {
  val navController = rememberNavController()
  NavHost(navController, startDestination = "/") {
    composable("/") { RepositoriesScreen(navController = navController, viewModel = viewModel) }
  }
}
