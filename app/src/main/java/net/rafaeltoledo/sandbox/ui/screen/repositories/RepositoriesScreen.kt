package net.rafaeltoledo.sandbox.ui.screen.repositories

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun RepositoriesScreen(navController: NavController, viewModel: RepositoriesViewModel) {
  val state = viewModel.state.collectAsState()

  Scaffold(
    modifier = Modifier
      .statusBarsPadding()
  ) { paddingValues ->
    Text(
      text = "${state.value.isLoading} | ${state.value.users.size}",
    )
  }
}
