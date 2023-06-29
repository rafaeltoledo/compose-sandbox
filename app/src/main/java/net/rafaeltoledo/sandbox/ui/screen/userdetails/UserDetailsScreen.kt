package net.rafaeltoledo.sandbox.ui.screen.userdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsScreen(
  userId: Long,
  upPress: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Scaffold(
    modifier = modifier,
    topBar = {
      TopAppBar(
        modifier = Modifier.statusBarsPadding(),
        title = { Text("$userId") },
        navigationIcon = {
          IconButton(
            onClick = { upPress.invoke() },
          ) {
            Icon(
              imageVector = Icons.Rounded.ArrowBack,
              contentDescription = null,
            )
          }
        },
      )
    },
  ) {
    Box(modifier = Modifier.padding(it))
  }
}
