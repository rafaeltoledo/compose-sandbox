package net.rafaeltoledo.sandbox.ui.screen.repositories

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RepositoriesScreen(navController: NavController, viewModel: RepositoriesViewModel = viewModel()) {
  val state = viewModel.state.collectAsState().value

  Scaffold(
    modifier = Modifier
      .statusBarsPadding()
  ) { paddingValues ->
    if (state.isLoading) {
      CircularProgressIndicator()
    } else {
      LazyColumn(content = {
        items(state.users) { user ->
          Text(user.displayName)
        }
      })
    }
  }
}
