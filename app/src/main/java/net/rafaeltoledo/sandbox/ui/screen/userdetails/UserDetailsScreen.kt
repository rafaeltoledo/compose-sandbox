package net.rafaeltoledo.sandbox.ui.screen.userdetails

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun UserDetailsScreen(userId: Long, upPress: () -> Unit) {
  Scaffold(
    topBar = {
      TopAppBar(modifier = Modifier.statusBarsPadding()) {
        IconButton(
          onClick = { upPress.invoke() },
          modifier = Modifier.align(Alignment.CenterVertically)
        ) {
          Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = null,
          )
        }
        Text(
          "$userId"
        )
      }
    }
  ) {

  }
}
